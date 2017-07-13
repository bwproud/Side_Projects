def lev(m, n, logging=False):
	if len(m) == 0:
		return n
	if len(n) == 0:
		return m
	matrix=[[0 for i in range(len(n)+1)] for j in range(len(m)+1)]
	for i in range(len(m)+1):
		matrix[i][0]=i
	for j in range(len(n)+1):
		matrix[0][j]=j
	if logging: printTable(matrix,m,n)
	for i in range(1, len(m)+1):
		for j in range(1, len(n)+1):
			cost = 0 if m[i-1]==n[j-1] else 1
			matrix[i][j]=min(matrix[i-1][j]+1,matrix[i][j-1]+1, matrix[i-1][j-1]+cost)
		if logging: printTable(matrix, m, n)
	return matrix[len(m)][len(n)]

def printTable(matrix, m, n):
	s="    "
	for i in range(len(n)):
		s+=n[i]+" "
	print(s)
	for i in range(len(matrix)):
		if i == 0:
			s=' ['
		else:
			s=m[i-1]+'['
		for j in range(len(matrix[0])):
			s+=str(matrix[i][j])+" "
		s+=']'
		print(s)
	print("")

def findClosest(target, list):
	min=9999999999
	st=""
	for i in list:
		dist = lev(str(i), target)
		if  dist < min:
			min = dist
			st=i
	return "closest match found is %s"%st		

if __name__ == '__main__':
	import sys
	if len(sys.argv) == 2: 
		print(lev("GAMBOL", "GUMBO", sys.argv[1]))
	elif len(sys.argv) == 3: 
		print(lev(str(sys.argv[1]), str(sys.argv[2])))	
	elif len(sys.argv) == 4: 
		print(lev(str(sys.argv[1]), str(sys.argv[2]), sys.argv[3]))
	else:
		print(lev("GAMBOL", "GUMBO"))	
	print findClosest("Brando", ["earl", "chuck", "Brennan", "jon"])
	

		