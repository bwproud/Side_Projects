def quicksort(a, p, r):
	if p<r:
		q=partition(a,p,r)
		quicksort(a,p,q-1)
		quicksort(a,q+1,r)

def partition(a, p, r):
	key=a[r]
	i=p-1
	for j in range(p,r):
		if a[j]<=key:
			i+=1
			a[i],a[j]=a[j],a[i]
	a[i+1],a[r]=a[r],a[i+1]
	return i+1

def countingSort(a):
	k=max(a)
	b=[0]*len(a)
	c=[0]*(k+1)
	for i in range(len(a)):
		c[a[i]]+=1
	for i in range(1, len(c)):
		c[i]+=c[i-1]
	for i in range(len(a)-1,-1,-1):
		b[c[a[i]]-1]=a[i]
		c[a[i]]-=1
	return b			

def randList(num):
	import random
	li=[]
	for i in range(num):
		li.append(int(random.random()*1000))
	return li	

def time(function, args1=None, args2=None, args3=None):
	from timeit import default_timer as timer
	start = timer()
	function(args1)
	end = timer()
	print(end - start) 

def main():
	li = randList(100)
	time(countingSort,li)

if __name__ == '__main__':
	import sys
	main() 				