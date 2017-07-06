import functools
#node class to hold predecessor and distance
@functools.total_ordering
class Node:
	def __init__(self, index, distance = float("inf"), pred = None):
		self.index = index
		self.distance = distance
		self.pred = pred

	def __lt__(self, other):
		return self.distance<other.distance

	def __eq__(self, other):
		return self.distance==other.distance	

	def __cmp__(self, other):
		return cmp(self.distance, other.distance)	

def initSingleSource(startNode, csv):
	#instance variables(adjacency list, nodes, and graph)
	adjList=[0]
	nodes=[0]
	count=0
	dataframe = pd.read_csv("%s"%csv, header=None) 
	for row in dataframe.itertuples():
		col = []
		count+=1
		nodes.append(Node(count))
		for i in range(1, len(row)):
			if row[i]!=0:
				col.append((i,row[i]))
		adjList.append(col)

	nodes[int(startNode)].distance=0
	return [adjList, nodes]

def getShortestDistance(startNode, endNode, csv):
	adjList, nodes = initSingleSource(startNode, csv)
	if len(adjList[int(endNode)])==0:
		print("node unreachable from start node\ndistance: %s route: %s" %(float("inf"), []))
	else:
		distance, path = dijkstra(adjList, nodes[1:], startNode, endNode)
		return distance, path

def bellmanFord(adjList, nodes, start, end):
	path = []
	for i in range(len(nodes)-1):
		for node in nodes:
			for v in adjList[node.index]:
				if nodes[v[0]-1].distance > node.distance + v[1]:
					nodes[v[0]-1].distance = node.distance + v[1]
					nodes[v[0]-1].pred = node
	node = nodes[int(end)-1]				
	while node.index != int(start):
		path.insert(0, node.index)
		node = node.pred
	path.insert(0, node.index)
	return nodes[int(end)-1].distance, path			



def dijkstra(adjList, nodes, start, end):
	visited={}
	q=nodes[:]
	path = []
	heapq.heapify(q)
	while len(q)>0:
			node = heapq.heappop(q)
			if visited.get(node.index)==1:
				continue
			if node.index == int(end):
				if node.pred is None and node.index != int(start):
					return node.distance, path
				distance = node.distance
				while node.index != int(start):
					path.insert(0, node.index)
					node = node.pred
				path.insert(0, node.index)
				return distance, path
			visited[node.index]=1;
			for v in adjList[node.index]:
				if visited.get(nodes[v[0]-1].index)==1:
					continue
				if nodes[v[0]-1].distance > node.distance + v[1]:
					updatedNode = Node(v[0], node.distance + v[1], node)
					heapq.heappush(q, updatedNode)
					
def main():
	snode, enode, csv=sys.argv[1],sys.argv[2], sys.argv[3]
	distance, path = getShortestDistance(snode,enode,csv)
	print("distance: %s route: %s" %(distance, path))	

if __name__ == '__main__':
	import pandas as pd
	import sys
	import heapq
	import time
	main() 