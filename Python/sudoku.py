class Sudoku:
        def __init__(self):
                self.branches=0

        def solveSudoku(self, board):
                print "Unsolved Board:"
                self.printBoard(board)
                i, j = self.findNext(board, 0, -1)
                c=1
                while c<10:
                        b = self.solve(board, i, j, c)
                        if b != None:
                                return board
                        c+=1
                        self.branches+=1
                return board

        def solve(self, board, i, j, val):
                if not self.checkCell(board, i, j, val):
                        return None
                board[i][j]=val
                if self.isValidSudoku(board):
                        return board
                x, y = self.findNext(board, i, j)
                c=1
                while c<10:
                        b = self.solve(board, x, y, c)
                        if b != None:
                                return b
                        c+=1
                        self.branches+=1
                board[i][j]="."        

        def findNext(self, board, a, b):
                for j in range(b+1, len(board[a])):
                        if board[a][j] == ".":
                                return a, j

                for i in range(a+1,len(board)):
                        for j in range(0, len(board[i])):
                                if board[i][j] == ".":
                                        return i, j
                return a,b

        def checkCell(self, board, i, j, val):
                for k in range(len(board)):
                        if val == board[i][k] or val == board[k][j]:
                            return False

                i=(i//3)*3
                j=(j//3)*3
                if not self.checkSquare(board, i, j, val):
                        return False
                return True

    	def isValidSudoku(self, board):
                rows=[[0]*10 for i in range(10)]
                cols=[[0]*10 for i in range(10)]
                squares=[[0]*10 for i in range(10)]
                for i in range(0, 9):
                        for j in range(0,9):
                                col = board[i][j]
                                if col == ".":
                                        return False
                                col=int(col)
                                square = (j//3)+((i//3)*3)
                                if rows[i][col] or cols[j][col] or squares[square][col]:
                                        return False
                                rows[i][col]+=1
                                cols[j][col]+=1
                                squares[square][col]+=1
                return True

        def checkSquare(self, board, a, b, val):
                for i in range(0, 3):
                        for j in range(0, 3):
                                if board[a+i][b+j] == val:
                                        return False
                return True

        def printBoard(self, board):
            print "---------------------"
            rowC=0
            for i in range(9):
                row = "|"
                colC=0
                for j in range(9):
                    if(j!=8):
                        row+=("%s "%board[i][j])
                    else:
                        row+=("%s"%board[i][j])
                    colC+=1
                    if colC%3==0:
                        row+="|"
                print row
                rowC+=1
                if(rowC%3==0):
                    print "---------------------"

        def printBranches(self):
            print "Number of branches required: %s" % self.branches            

def main():
    #OFFICE PUZZLE
	board=[[".", 1, ".", ".", ".", 9, ".", ".", 3],
		   [".", ".", 8, ".", ".", ".", ".", 7, "."],
		   [2, ".", ".", ".", 5, ".", 4, ".", "."],
		   [".", ".", ".", ".", ".", ".", ".", ".", 5],
		   [".", ".", 9, ".", ".", 7, ".", 6, "."],
		   [".", ".", ".", 2, ".", ".", 1, ".", "."],
		   [".", 5, ".", ".", 1, ".", ".", ".", 2],
		   [".", ".", 6, ".", ".", ".", 8, ".", "."],
		   [".", ".", ".", 3, ".", ".", ".", 9, "."]]
        ## VERY HARD PUZZLE
        # board=[[".", ".", ".", 4, ".", 9, ".", 2, "."],
        #    [3, ".", 2, ".", ".", ".", 7, 1, "."],
        #    [4, ".", ".", ".", 2, ".", ".", ".", "."],
        #    [".", ".", ".", ".", 8, 1, ".", ".", "."],
        #    [1, ".", 8, ".", ".", ".", 2, ".", 3],
        #    [".", ".", ".", 2, 6, ".", ".", ".", "."],
        #    [".", ".", ".", ".", 4, ".", ".", ".", 6],
        #    [".", 3, 5, ".", ".", ".", 4, ".", 1],
        #    [".", 8, ".", 9, ".", 5, ".", ".", "."]]
        # # HARDEST PUZZLE IN THE WORLD number 1
        # board=[[8, ".", ".", ".", ".", ".", ".", ".", "."],
        #    [".", ".", 3, 6, ".", ".", ".", ".", "."],
        #    [".", 7, ".", ".", 9, ".", 2, ".", "."],
        #    [".", 5, ".", ".", ".", 7, ".", ".", "."],
        #    [".", ".", ".", ".", 4, 5, 7, ".", "."],
        #    [".", ".", ".", 1, ".", ".", ".", 3, "."],
        #    [".", ".", 1, ".", ".", ".", ".", 6, 8],
        #    [".", ".", 8, 5, ".", ".", ".", 1, "."],
        #    [".", 9, ".", ".", ".", ".", 4, ".", "."]]
        ## HARDEST PUZZLE IN THE WORLD number 2
        # board=[[".", ".", 5, 3, ".", ".", ".", ".", "."],
        #    [8, ".", ".", ".", ".", ".", ".", 2, "."],
        #    [".", 7, ".", ".", 1, ".", 5, ".", "."],
        #    [4, ".", ".", ".", ".", 5, 3, ".", "."],
        #    [".", 1, ".", ".", 7, ".", ".", ".", 6],
        #    [".", ".", 3, 2, ".", ".", ".", 8, "."],
        #    [".", 6, ".", 5, ".", ".", ".", ".", 9],
        #    [".", ".", 4, ".", ".", ".", ".", 3, "."],
        #    [".", ".", ".", ".", ".", 9, 7, ".", "."]]
        #Easy Puzzle
        board=[[".", ".", 9, 7, 4, 8, ".", ".", "."],
           [7, ".", ".", ".", ".", ".", ".", ".", "."],
           [".", 2, ".", 1, ".", 9, ".", ".", "."],
           [".", ".", 7, ".", ".", ".", 2, 4, "."],
           [".", 6, 4, ".", 1, ".", 5, 9, "."],
           [".", 9, 8, ".", ".", ".", 3, ".", "."],
           [".", ".", ".", 8, ".", 3, ".", 2, "."],
           [".", ".", ".", ".", ".", ".", ".", ".", 6],
           [".", ".", ".", 2, 7, 5, 9, ".", "."]]   
	sudoku= Sudoku()
	sudoku.solveSudoku(board)
	print "\nSolved Board:"
	sudoku.printBoard(board)
	sudoku.printBranches()

def run(method):
    import time
    start = time.time()
    method()
    elapsed = (time.time()-start)
    print ("%s ran in %f seconds" % (method.__name__, elapsed))    

if __name__ == '__main__':
	run(main) 
