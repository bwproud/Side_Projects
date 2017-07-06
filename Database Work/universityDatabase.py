#table creation
import sqlite3
import pandas as pd
dataframe = pd.read_csv("a3.csv")       
db = sqlite3.connect("a3.db")
cursor = db.cursor()

cursor.execute("""DROP TABLE IF EXISTS Student""")
cursor.execute("""DROP TABLE IF EXISTS Class""")
cursor.execute("""DROP TABLE IF EXISTS Professor""")
cursor.execute("""DROP TABLE IF EXISTS CoursesTaken""")

cursor.execute("""CREATE TABLE IF NOT EXISTS Student(SID INTEGER, 
                                                    FirstName TEXT,
                                                    LastName TEXT, 
                                                    Primary Key(SID))""")


cursor.execute("""CREATE TABLE IF NOT EXISTS Class(CID INTEGER, 
                                                        Name TEXT, 
                                                        Season TEXT,
                                                        Year TEXT,
                                                        Section INTEGER,
                                                        PID INTEGER,
                                                        Exam DATE,
                                                        Primary Key(CID),
                                                        UNIQUE(Name, Season, Year, Section, PID, Exam),
                                                        Foreign Key(PID) References Professor(PID))""")

cursor.execute("""CREATE TABLE IF NOT EXISTS Professor(PID INTEGER, 
                                                    FirstName TEXT,
                                                    LastName TEXT, 
                                                    Primary Key(PID))""")

cursor.execute("""CREATE TABLE IF NOT EXISTS CoursesTaken(SID INTEGER, 
                                                    CID Integer,
                                                    Primary Key(SID, CID),
                                                    Foreign Key(SID) References Student(SID)
                                                    Foreign Key(CID) References Class(CID))""") 

#table population
students=[]
professors=[]
classes=[]
for row in dataframe.itertuples():
    st="%s %s"%(row[1], row[2])
    pro="%s %s"%(row[6], row[7])
    cl="%s %s %s %s %s"%(row[3], row[4], row[5], pro,row[8])
    if st not in students:
        students.append(st)
        try:
            cursor.execute("""INSERT INTO Student(SID, FirstName, LastName) VALUES(?,?, ?)""",(students.index(st),row[1], row[2]))
        except:
            pass
    if pro not in professors:
        professors.append(pro)
        try:
            cursor.execute("""INSERT INTO Professor(PID, FirstName, LastName) VALUES(?,?, ?)""",(professors.index(pro),row[6], row[7]))
        except:
            pass
    if cl not in classes:
        classes.append(cl)
        try:
            cursor.execute("""INSERT INTO Class(CID, Name, Season, Year, Section, PID, Exam) VALUES(?,?,?,?,?,?,?)""",(classes.index(cl),row[3], row[4][:1], row[4][1:], row[5], professors.index(pro),row[8]))
        except:
            pass    
    try:
        cursor.execute("""INSERT INTO CoursesTaken(SID, CID) VALUES(?,?)""",(students.index(st), classes.index(cl)))
    except:
        pass
        
        
db.commit()
db.close()

#Which students (by first and last name) have taken both COMP 401 and COMP 426 with Ketan Mayer-Patel?
db = sqlite3.connect("a3.db")
cursor = db.cursor()
cursor.execute("""Select s.FirstName, s.LastName 
                    From Student s, CoursesTaken ct, Class c, CoursesTaken ct1, Class c1, Professor p
                    Where s.SID=ct.SID AND ct.CID=c.CID AND c.Name LIKE '%COMP 426%' AND c.PID=p.PID AND
                    s.SID=ct1.SID AND ct1.CID=c1.CID AND c1.Name LIKE '%COMP 401%' AND c1.PID=p.PID AND 
                    p.FirstName='Ketan' AND p.LastName='Mayer-Patel'""")
pd.DataFrame(cursor.fetchall())

#How many students have ever had multiple exams on the same date?
db = sqlite3.connect("a3.db")
cursor = db.cursor()
cursor.execute("""Select COUNT(*) FROM (Select * From Student s, Class c, CoursesTaken ct Where s.SID=ct.SID AND ct.CID=c.CID Group By s.SID, c.Exam Having count(*)>1)""")
pd.DataFrame(cursor.fetchall())

db = sqlite3.connect("a3.db")
cursor = db.cursor()
cursor.execute("""Select COUNT(s.sid) 
                    From Student s, CoursesTaken ct, Class c 
                    Where s.SID=ct.SID AND ct.CID=c.CID AND c.Name LIKE '%COMP 426%'""")
pd.DataFrame(cursor.fetchall())

db = sqlite3.connect("a3.db")
cursor = db.cursor()
cursor.execute("""Select p.FirstName, p.LastName 
                    from Professor p 
                    Where p.PID NOT IN (Select p1.PID 
                                            From Professor p1, Class c1 
                                            Where c1.PID=p1.PID AND c1.Semester LIKE 'S%')""")
pd.DataFrame(cursor.fetchall())

db = sqlite3.connect("a3.db")
cursor = db.cursor()
cursor.execute("""Select * from Class c Where c.Semester = 'F15'""")
pd.DataFrame(cursor.fetchall())