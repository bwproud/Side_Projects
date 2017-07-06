#creates tables
import sqlite3
import pandas as pd
from datetime import datetime
dataframe = pd.read_csv("monkepo.csv")       
db = sqlite3.connect("monkepo.db")
cursor = db.cursor()

cursor.execute("""CREATE TABLE If Not exists Monkepo(mid INTEGER PRIMARY KEY,name TEXT, UNIQUE(name))""")
cursor.execute("""CREATE TABLE IF NOT EXISTS Appearance(mid INTEGER, latitude REAL, longitude REAL, datetime DATETIME2, PRIMARY KEY(mid, latitude, longitude, datetime))""")
cursor.execute("""CREATE TABLE IF NOT EXISTS Class(mid INTEGER, major INTEGER, type TEXT, PRIMARY KEY(mid, major))""")

#populates tables
li=[0]
for row in dataframe.itertuples():     
    date= row[7]
    if row[1] not in li:
        li.append(row[1])
        try:
            cursor.execute("""INSERT INTO Monkepo(name) VALUES(?)""", (row[1],))
        except:
            pass
    if "." in row[7]: date=row[7][:row[7].index(".")]    
    try:
        cursor.execute("""INSERT INTO Appearance VALUES(?, ?, ?,?)""",(li.index(row[1]),row[4], row[5], datetime.strptime(row[6]+" "+date, '%Y-%m-%d %H:%M:%S')))
    except:
        pass
    try:
        cursor.execute("""INSERT INTO CLASS VALUES(?,?,?)""", (li.index(row[1]), 1, row[2]))
    except:
        pass
    if row[3].lower()!="none":
        try:
            cursor.execute("""INSERT INTO CLASS VALUES(?,?,?)""", (li.index(row[1]), 0, row[3]))
        except:
            pass

db.commit()
db.close()

#finds the most common monkepo and puts the results into a dictionary
def mostCommon(db):
    dic={}
    cursor = db.cursor()
    cursor.execute("""select m.name from Monkepo m, Appearance a where m.mid=a.mid""")
    for appearance in cursor:
        dic[appearance[0]]=dic.get(appearance[0], 0)+1
    sort_appear = sorted(dic.items(), key=lambda x: x[1], reverse=True)
    return sort_appear
    
import pandas
db = sqlite3.connect("monkepo.db")
listOfTuples = mostCommon(db)
db.close() 
pandas.DataFrame(listOfTuples, columns=["Name", "#appearances"])

#finds the most common monkepo using sql GROUP BY
def mostCommon2(db):
    cursor = db.cursor()
    cursor.execute("""select m.name, count(*) from Monkepo m, Appearance a where m.mid=a.mid Group by m.mid Order by count(*) DESC""")
    li=[]
    for i in cursor: 
        li.append(i)
    return li    
    
import pandas
db = sqlite3.connect("monkepo.db")
listOfTuples = mostCommon2(db)
db.close()
pandas.DataFrame(listOfTuples, columns=["Name", "#appearances"])


#finds rarest and most common mokepo by type
def mostLeastClass(db):
    cursor = db.cursor()
    cursor.execute("""Select * From Class c group by c.type""")
    li=[]
    for classes in cursor.fetchall():
        cursor.execute("""SELECT c.type, m.name, count(*) FROM Appearance a, Monkepo m , Class c WHERE a.mid=m.mid and a.mid=c.mid and c.type=? Group by m.mid order by count(*) desc limit 1""",(classes[2],))
        most=cursor.fetchone()[1]
        cursor.execute("""SELECT c.type, m.name, count(*) FROM Appearance a, Monkepo m , Class c WHERE a.mid=m.mid and a.mid=c.mid and c.type=? Group by m.mid order by count(*) asc limit 1""",(classes[2],))
        rare=cursor.fetchone()[1]
        li.append((classes[2], most, rare))
    return li     
        
import sqlite3, pandas
db = sqlite3.connect("monkepo.db")
listOfTuples = mostLeastClass(db)
db.close() 
pandas.DataFrame(listOfTuples, columns=["Name", "Most Common", "Least Common"])

#finds the distance between 2 coordinates
def dist(latM, longM, latS, longS):
    import math
    return (latM-latS)**2+(longM-longS)**2 

#finds the supply station with the most monkepo sightings
def mostPopularStations(db):
    cursor = db.cursor()
    popStation={}
    stations=[]
    cursor.execute("""SELECT * FROM SupplyStations""")
    for row in cursor:
        stations.append(row)
        popStation[row]=0
    cursor.execute("""SELECT * FROM Appearance""")
    for sighting in cursor.fetchall():
        minDist=99
        cStation=None
        for station in stations:
            distance=dist(sighting[1],sighting[2] , station[1], station[2])
            if distance<minDist:
                minDist=distance
                cStation=station
        popStation[cStation]+=1
    li=[]
    sorted_stations = sorted(popStation.items(), key=lambda x: x[1], reverse=True)
    for i in sorted_stations:
        li.append((i[0][0], i[0][1], i[0][2], i[1]))
    return li

import sqlite3
import pandas as pd
dataframe = pd.read_csv("stations.csv")       
db = sqlite3.connect("monkepo.db")
cursor = db.cursor()

cursor.execute("""CREATE TABLE IF NOT EXISTS SupplyStations(sid INTEGER,latitude REAL, longitude REAL, PRIMARY KEY(sid, latitude, longitude))""")
for row in dataframe.itertuples(): 
    try:
        cursor.execute("""INSERT INTO SupplyStations VALUES(?,?,?)""", (row[1], row[2], row[3]))
    except:
        pass
    
db.commit()
%timeit -r 1 mostPopularStations(db) 
db.close()  