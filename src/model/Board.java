package model;

public class Board {

	Tile[][] board = new Tile[8][8];

	public Board() {
		Tile tempTile = null;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (j == 0) {
					tempTile = new Tile(0, i);
					board[0][i] = tempTile;
					tempTile.piece = true;
					tempTile.black = true;
				} else if (j == 1) {
					tempTile = new Tile(1, i);
					board[1][i] = tempTile;
					tempTile.piece = true;
					tempTile.black = true;
				} else if (j == 6) {
					tempTile = new Tile(6, i);
					board[6][i] = tempTile;
					tempTile.piece = true;
					tempTile.red = true;
				} else if (j == 7) {
					tempTile = new Tile(7, i);
					board[7][i] = tempTile;
					tempTile.piece = true;
					tempTile.red = true;
				} else {
					tempTile = new Tile(j, i);
					board[j][i] = tempTile;
				}
			}
		}
	}

	public String toString() {
		String output = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				output += board[i][j] + " ";
			}
			output += "\n";
		}
		return output;
	}
	
	public Tile getTile(int x, int y){
		return board[x][y];
	}
	
	public void movepiece(int oldX, int oldY,int newX, int newY){
		board[newX][newY].black = board[oldX][oldY].black;
		board[newX][newY].piece = board[oldX][oldY].piece;
		board[newX][newY].queen = board[oldX][oldY].queen;
		board[newX][newY].red = board[oldX][oldY].red;
		
		board[oldX][oldY] = new Tile(oldX, oldY);
	}

	public class Tile {
		private int X = 0;
		private int Y = 0;
		public boolean piece = false;
		public boolean queen = false;
		public boolean black = false;
		public boolean red = false;
		public boolean isPlayable = false;
		public boolean possibleTaken = false;

		Tile(int X, int Y) {
			this.X = X;
			this.Y = Y;
			
		}

		public String toString() {
			if (this.piece) {
				return "[x]";
			}
			if (this.queen) {
				return "[Q]";
			}
			return "[ ]";
		}
	

		/**
		 * @return the x
		 */
		public int getX() {
			return X;
		}

		/**
		 * @param x
		 *            the x to set
		 */
		public void setX(int x) {
			X = x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return Y;
		}

		/**
		 * @param y
		 *            the y to set
		 */
		public void setY(int y) {
			Y = y;
		}
	}
	
	

	public void movePiece(int oldX, int oldY, int newX, int newY) {
	board[newX][newY] = board[oldX][oldY];
	board[oldX][oldY] = new Tile(oldX, oldY);
	}
}
