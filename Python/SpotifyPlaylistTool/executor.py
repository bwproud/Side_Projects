from PyQt4 import QtGui
from PyQt4.QtGui import *
import sys
import design
import os
import DataManipulation
from blist._sortedlist import sortedset
from playlistCurationv2.DataManipulation import truncatePlusArtist


class ExampleApp(QtGui.QMainWindow, design.Ui_MainWindow):
	def __init__(self):
		super(self.__class__, self).__init__()
		self.setupUi(self)
		self.msg = QMessageBox()
		self.btnSo=QtGui.QPushButton('Songs')
		self.btnAr=QtGui.QPushButton('Artists')
		self.btnAl=QtGui.QPushButton('Albums')
		self.msg.setText("Choose Field to get length of")
		self.msg.setWindowTitle("Get Length")
		self.msg.addButton(self.btnAl, QtGui.QMessageBox.YesRole)
		self.msg.addButton(self.btnSo, QtGui.QMessageBox.AcceptRole)
		self.msg.addButton(self.btnAr, QtGui.QMessageBox.NoRole)
		self.al = DataManipulation.getAlbums()
		self.ar = DataManipulation.getArtists()
		self.so = DataManipulation.getSongs()
		self.di = DataManipulation.getSongCountPerArtist(self.ar)
		self.btnPrint.clicked.connect(self.bprintPlaylist)
		self.btnGetSongs.clicked.connect(self.bgetSongs)
		self.btnGetArtists.clicked.connect(self.bgetArtists)
		self.btnGetAlbums.clicked.connect(self.bgetAlbums)
		self.btnFindDups.clicked.connect(self.bfindDups)
		self.btnNumSongs.clicked.connect(self.bnumSongs)
		self.btnTopA.clicked.connect(self.btopArtists)
		self.btnSingles.clicked.connect(self.bsingles)	
		self.btnCreateSS.clicked.connect(self.bcreateSS)
		self.btnLen.clicked.connect(self.bgetLength)
	
	def bprintPlaylist(self):
		self.listWidget.clear()
		li = DataManipulation.test(DataManipulation.truncatePlusArtist(self.so), self.ar, DataManipulation.truncatePlusArtist(self.al))
		for i in li:
			self.listWidget.addItem(i)	
			
	def bgetSongs(self):
		self.listWidget.clear()
		li = self.so
		for i in li:
			self.listWidget.addItem(i)
			
	def bgetArtists(self):
		self.listWidget.clear()
		li = DataManipulation.truncateArtists(self.ar, sortedSet=True)
		for i in li:
			self.listWidget.addItem(i)
			
	def bgetAlbums(self):
		self.listWidget.clear()
		li = sortedset(self.al)
		for i in li:
			self.listWidget.addItem(i)
			
	def bfindDups(self):
		self.listWidget.clear()
		li = DataManipulation.findDupSongs(DataManipulation.truncatePlusArtist(self.so), self.ar)
		for i in li:
			self.listWidget.addItem(i)		
			
	def bnumSongs(self):
		maxsize=20
		numS = QtGui.QInputDialog.getInt(self, "Choose Song Limit",
        "Artists with more than this number of songs will be displayed:", maxsize)
		li = DataManipulation.numSongs(numS[0], self.di)
		self.listWidget.clear()
		for i in li:
			self.listWidget.addItem(i)
			
	def btopArtists(self):
		maxsize=50
		numS = QtGui.QInputDialog.getInt(self, "Choose Number of Artists",
        "Display this number of Artists:", maxsize)
		self.listWidget.clear()
		li = DataManipulation.topArtists(numS[0], self.di)
		for i in li:
			self.listWidget.addItem(i)	
			
	def bsingles(self):
		self.listWidget.clear()
		li = DataManipulation.singles(truncatePlusArtist(self.so), self.ar, self.di)
		for i in li:
			self.listWidget.addItem(i)	
			
	def bcreateSS(self):
		self.listWidget.clear()
		li = DataManipulation.prettyPrint(DataManipulation.truncatePlusArtist(self.so), DataManipulation.truncateArtists(self.ar, sortedSet=False), DataManipulation.truncatePlusArtist(self.al))
		for i in li:
			self.listWidget.addItem(i)
		#self.listWidget.addItem(DataManipulation.createArtistSpreadsheet())	
		
	def bgetLength(self, str=""):
		self.btnSo.clicked.connect(lambda: self.getLength(str="s"))
		self.btnAr.clicked.connect(lambda: self.getLength(str="ar"))
		self.btnAl.clicked.connect(lambda: self.getLength(str="al"))
		self.msg.exec_()
		
	def getLength(self, str):
		self.listWidget.clear()
		if str == "s": self.listWidget.addItem("%s"%len(self.so))
		elif str == "ar": self.listWidget.addItem("%s"%len(DataManipulation.truncateArtists(self.ar)))
		elif str == "al": self.listWidget.addItem("%s"%len(sortedset(self.al)))
		
	
					
		
def main():
	app = QtGui.QApplication(sys.argv)
	form = ExampleApp()
	form.show()
	app.exec_()

if __name__ == '__main__':
	main()