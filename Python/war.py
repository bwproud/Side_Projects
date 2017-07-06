class War:
	def __init__(self, player, opponent):
		self.player=player
		self.opponent= opponent
		self.deck = deckShuffle(deckCreation())

	def dealCards(self):
		self.player.dealCards(self.deck)
		self.opponent.dealCards(self.deck, True)

	def playGame(self, sim="False", output=True):
		loser = 0
		count =1
		simulate = sim
		while len(self.player.cards)>0 and len(self.opponent.cards)>0 and loser!=3:
			if simulate != "simulate":
				simulate =raw_input()
			if output: print "------- START ROUND %s -------"% count
			loser = self.drawCards(output)
			pcard=ocard="cards"
			if len(self.player.cards) == 1: pcard = "card"
			if len(self.opponent.cards) == 1: ocard = "card"			
			if output: print "\nYou have %s %s" % (len(self.player.cards),pcard)
			if output: print "Computer has %s %s" % (len(self.opponent.cards),ocard)
			if output: print "------- END ROUND %s -------"% count
			if output: print "\n"
			count+=1

		print "Game lasted %s rounds" % (count-1)	
		if len(self.player.cards)<4:
				print "Computer won!"
		else:
			if loser == 3:
					if output: print "Computer ran out of cards." 
			print "You won!"	
		return count		

	def drawCards(self, output):
		playerCard = self.player.cards.pop(0)
		opponentCard = self.opponent.cards.pop(0)
		if output:	print "Results of Draw:\n   You:     %s\n   Computer:%s\n" % (playerCard[0], opponentCard[0])
		if playerCard[1]>opponentCard[1]:
			self.player.cards.append(opponentCard)
			self.player.cards.append(playerCard)
			if output: print "You won round!"
			return 0
		elif playerCard[1]<opponentCard[1]:	
			self.opponent.cards.append(playerCard)
			self.opponent.cards.append(opponentCard)
			if output: print "Computer won round!"
			return 1
		else:	
			if output: print "War!"
			if len(self.player.cards)<4:
				if output: print "You ran out of cards."
				return 3
			if len(self.opponent.cards)<4:
				if output: print "Computer ran out of cards."
				return 3	
			warCards=[]
			warCards.append(playerCard)
			warCards.append(opponentCard)
			if output: print "You and Computer draw 3 cards each and put them facedown"
			for i in range(3):
				warCards.append(self.player.cards.pop(0))
				warCards.append(self.opponent.cards.pop(0))	
			result = self.drawCards(output)
			if result:
				if output: print "Computer won war!\nHidden cards won by Computer:"
				for i in range(2,len(warCards)):
					if output: print "   %s"%warCards[i][0]
				self.opponent.cards+=warCards
				return 1
			else:
				if output: print "You won war!\nHidden cards won by You:"
				for i in range(2,len(warCards)):
					if output: print "   %s"%warCards[i][0]
				self.player.cards+=warCards
				return 0



class Player:
	def __init__(self):
		self.cards =[]

	def dealCards(self, deck, opponent=False):
		for i in range(len(deck)):
			if not opponent:
				if i%2==0:
					self.cards.append(deck[i])
			else:
				if i%2!=0:
					self.cards.append(deck[i])

	def getCards(self):
		print len(self.cards)									


def deckCreation():
	deck=[]
	for i in range(2,15):
		j=i
		if i == 11: j = "Jack"
		if i == 12: j = "Queen"
		if i == 13: j = "King"
		if i == 14: j = "Ace"
		deck.append(["%s of spades"% j,i])
		deck.append(["%s of clubs"% j,i])
		deck.append(["%s of hearts"% j,i])
		deck.append(["%s of diamonds"% j,i])
	return deck

def deckShuffle(deck):
	import random
	for j in range(3):
		for i in range(len(deck)):
			rand= random.randint(0,51)
			deck[i],deck[rand]=deck[rand], deck[i]
	return deck			

def averageLegnth(limit):
	count=0
	mi=999999
	ma=0
	for i in range(limit):
		game = War(Player(), Player())
		game.dealCards()
		turns=game.playGame("simulate", False)
		print""
		if turns<mi: mi= turns
		if turns>ma: ma = turns
		count+=turns
	ave = float(count)/limit	
	print "Out of %s games:\nThe average game legnth was %s rounds" % (limit, ave)
	print "The shortest game was %s rounds" % mi
	print "The longest game was %s rounds" % ma
			
def main():
	#averageLegnth(100000)
	game = War(Player(), Player())
	game.dealCards()
	game.playGame()

if __name__ == '__main__':
	main()
