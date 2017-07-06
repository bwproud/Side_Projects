class Node:
	def __init__(self, val, left=None, right=None):
		self.val=val
		self.left=left
		self.right=right

q=[(0.06668889629876625, 'q'), (0.4334778259419807, 'j'), (0.06668889629876625, 'x'), (1.7005668556185394, 'b'), (1.06702234078026, 'v'), (0.16672224074691563, 'z'), (2.13404468156052, 'g'), (1.9339779926642215, 'p'), (5.2017339113037675, 'r'), (2.5675225075025008, 'u'), (1.2337445815271757, 'k'), (1.4671557185728576, 'f'), (2.334111370456819, 'm'), (7.135711903967989, 'n'), (7.369123041013672, 'o'), (3.334444814938313, 'd'), (6.102034011337112, 'h'), (7.769256418806268, 'i'), (6.535511837279093, 's'), (9.90330110036679, 't'), (11.803934644881627, 'e'), (8.836278759586529, 'a'), (2.400800266755585, 'w'), (3.334444814938313, 'l'), (2.100700233411137, 'y'), (3.0010003334444817, 'c')]
def buildHuffmanTree(li):
	q=[]
	for i in li:
		q.append((i[0], Node(i[1])))
	import heapq
	heapq.heapify(q)
	for i in range(25):
		l = heapq.heappop(q)
		r = heapq.heappop(q)
		n = (float(l[0])+float(r[0]), "%s%s"%(l[1],r[1]))
		newNode =(n[0], Node(n[1], l[1], r[1]))
		heapq.heappush(q, newNode)
	return q

def traverseTree(root, str,li):
	if(root.left is None or root.right is None):
		print "encoding %s: %s" %(root.val, str)
		li[ord(root.val)-97]=str
		return
	traverseTree(root.left, str+"0", li)
	traverseTree(root.right, str+"1", li)	

def savings(perc, encoding):
	total=0
	for i in range(len(perc)):
		numOccurences = 2999*perc[i]
		numBits = len(encoding[i])
		total+=(numOccurences*numBits)
	print total	

def bitString(str, encoding):
	bitstring = ""
	char=0
	for i in str:
		num = ord(i)
		if num >96 and num < 123:
			char+=1
			num-=97
			bitstring+=encoding[num]
	print char*7		
	print bitstring	
	print len(bitstring)	

root = buildHuffmanTree(q)
li=[0]*26
traverseTree(root[0][1], "", li)
s=[0.08836278759586529, 0.017005668556185394, 0.030010003334444816, 0.03334444814938313, 0.11803934644881627, 0.014671557185728577, 0.0213404468156052, 0.061020340113371124, 0.07769256418806268, 0.004334778259419807, 0.012337445815271757, 0.03334444814938313, 0.02334111370456819, 0.0713571190396799, 0.07369123041013671, 0.019339779926642216, 0.0006668889629876625, 0.05201733911303768, 0.06535511837279093, 0.09903301100366789, 0.02567522507502501, 0.0106702234078026, 0.02400800266755585, 0.0006668889629876625, 0.02100700233411137, 0.0016672224074691564]
savings(s, li)
toEncode="It will probably not surprise you to learn that when Joel James wins an Atlantic Coast Conference championship, he does not take small souvenirs."
toEncode=str.lower(toEncode)
bitString(toEncode, li)
