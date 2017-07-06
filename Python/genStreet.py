def genStreet(right):
	l=[]
	r=[]
	left=right*2
	ls=rs=0
	import random
	while(left>0 or right>0):
		lran = random.randint(0,int(left/2)+1)
		rran= random.randint(0,int(right/2)+1)
		lstr = '-'*lran
		rstr = '-'*rran
		ls= ls+1 if(lran) else ls
		rs = rs+1 if(rran) else rs
		l.append(lstr)
		r.append(rstr)
		left-=lran
		right-=rran
	k=len(max(l))+2
	j=len(max(r))+2
	length=max(len(l), len(r))
	for i in range(length):
		space = " "*(k-len(l[i])-len(str(len(l[i]))) )
		rspace = " "*(j-len(r[i]))
		print("%s%s%s%s%s%s%s"%(len(l[i]),space,l[i],"|  |  |",r[i], rspace, len(r[i])))
	bigger = "left side" if(ls>rs) else "na, equal size" if (ls==rs) else "right side"
	l=[len(i) for i in l if len(i)!=0]
	r=[len(i) for i in r if len(i)!=0]
	difference=abs(ls-rs)
	shift=int(difference/2)+1
	print("the bigger side is the %s, the difference is %s, and the number of culdesacs to shift is %s" % (bigger, difference, shift))
	lsize=count(l)
	rsize=count(r)
	print("%s %s %s"%(lsize, rsize, lsize+rsize))
	print()
	i=0
	while i < shift:
		if ls>rs:
			l=[j-1 for j in l]
			r=[j+1 for j in r]
			for k in l:
				if k == 0:
					i+=1
		else:
			l=[j+1 for j in l]
			r=[j-1 for j in r]
			for k in r:
				if k == 0:
					i+=1
		lsize=count(l)
		rsize=count(r)
		print("%s %s %s"%(lsize, rsize, lsize+rsize))
		print()

def genStreetv2(l, r):
	import math
	ls = len(l)
	rs= len(r)
	bigger = "left side" if(ls>rs) else "na, equal size" if (ls==rs) else "right side"
	difference=abs(ls-rs)
	shift=math.ceil(difference/2)
	print("the bigger side is the %s, the difference is %s, and the number of culdesacs to shift is %s" % (bigger, difference, shift))
	lsize=count(l)
	rsize=count(r)
	print("%s %s %s"%(lsize, rsize, lsize+rsize))
	print()
	i=0
	while i < shift:
		if ls>rs:
			l=[j-1 for j in l]
			r=[j+1 for j in r]
			for k in l:
				if k == 0:
					i+=1
		else:
			l=[j+1 for j in l]
			r=[j-1 for j in r]
			for k in r:
				if k == 0:
					i+=1
		lsize=count(l)
		rsize=count(r)
		print("%s %s %s"%(lsize, rsize, lsize+rsize))
		print()

def genStreetv3(l, r):
	ls = len(l)
	rs= len(r)
	bigger = "left side" if(ls>rs) else "na, equal size" if (ls==rs) else "right side"
	difference=abs(ls-rs)
	shift=int(difference/2)+1
	print("the bigger side is the %s, the difference is %s, and the number of culdesacs to shift is %s" % (bigger, difference, shift))
	lsize=count(l)
	rsize=count(r)
	print("%s %s %s"%(lsize, rsize, lsize+rsize))
	print()
	last=99999
	current=99999
	while last>=current:
		last=current
		if ls>rs:
			l=[j-.1 for j in l]
			r=[j+.1 for j in r]
		else:
			l=[j+.1 for j in l]
			r=[j-.1 for j in r]
		lsize=count(l)
		rsize=count(r)
		current = lsize+rsize
		print("%s %s %s"%(lsize, rsize, lsize+rsize))
		print()
	print ("%s %s" % (last, current))		


def count(li):
	i=0
	for j in li:
		i+=abs(j)
	return i

