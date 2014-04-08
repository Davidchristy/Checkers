package model;

import model.Board.Tile;

public class GamePlay {
	static Board board = new Board();
	public boolean clickedOn = false;
	private Tile selectedTile = null;

	public void movePiece(Tile tile, int newX, int newY) {
		if(Math.abs((tile.getX()-newX))>=2){
			int takenX = (tile.getX()+newX)/2;
			int takenY = (tile.getY()+newY)/2;
			Tile temp = board.board[takenX][takenY];
			temp.piece=false;
			temp.red=false;
			temp.black=false;
			temp.queen=false;
		}

		board.movepiece(tile.getX(), tile.getY(), newX, newY);

		System.out.println(board.toString());
	}

	public Board getBoard() {
		return board;
	}

	public void clickedTile(int X, int Y) {
		if (!inBounds(X, Y)) {
			return;
		}
		if (clickedOn) {
			if (board.getTile(X, Y).isPlayable) {
				movePiece(selectedTile, X, Y);
				selectedTile = null;
			}
			resetPlayable();
		} else {
			selectedTile = board.getTile(X, Y);
			setPlayable(board.getTile(X, Y), X, Y);
			

		}
		clickedOn = !clickedOn;
	}

	private void setPlayable(Tile piece, int x, int y) {
		if (!inBounds(x, y)) {
			return;
		}

		if (piece.black) {
			if (inBounds(x + 1, y + 1))
				if (!board.getTile(x + 1, y + 1).piece)
					board.getTile(x + 1, y + 1).isPlayable = true;
			if (inBounds(x + 1, y - 1))
				if (!board.getTile(x + 1, y - 1).piece)
					board.getTile(x + 1, y - 1).isPlayable = true;
			if (inBounds(x + 2, y + 2) && (inBounds(x + 1, y + 1)))
				if (board.getTile(x + 1, y + 1).red) {
					if (!board.getTile(x + 2, y + 2).piece) {
						board.getTile(x + 1, y + 1).possibleTaken = true;
					board.getTile(x + 2, y + 2).isPlayable = true;
					doubleJump(piece, x + 2, y + 2);
					}
				}
			if (inBounds(x + 1, y - 1))
				if (board.getTile(x + 1, y - 1).red) {
					if (!board.getTile(x + 2, y - 2).piece) {
						board.getTile(x + 1, y + 1).possibleTaken = true;
					board.getTile(x + 2, y - 2).isPlayable = true;
					doubleJump(piece, x + 2, y - 2);
					}
				}
		} else if (piece.red) {
			if (inBounds(x - 1, y + 1))
				if (!board.getTile(x - 1, y + 1).piece)
					board.getTile(x - 1, y + 1).isPlayable = true;
			if (inBounds(x - 1, y - 1))
				if (!board.getTile(x - 1, y - 1).piece)
					board.getTile(x - 1, y - 1).isPlayable = true;
			if (inBounds(x - 1, y + 1) && inBounds(x - 2, y + 2))
				if (board.getTile(x - 1, y + 1).black) {
					if (!board.getTile(x - 2, y + 2).piece) {
						board.getTile(x - 1, y + 1).possibleTaken = true;
					board.getTile(x - 2, y + 2).isPlayable = true;
					doubleJump(piece, x - 2, y + 2);
					}
				}
			if (inBounds(x - 1, y - 1) && inBounds(x - 2, y - 2))
				if (board.getTile(x - 1, y - 1).black) {
					if (!board.getTile(x - 2, y - 2).piece) {
						board.getTile(x - 1, y - 1).possibleTaken = true;
					board.getTile(x - 2, y - 2).isPlayable = true;
					doubleJump(piece, x - 2, y - 2);
					}
				}
		}
		else{
			clickedOn = !clickedOn;
		}

	}

	private void doubleJump(Tile piece, int x, int y) {
		if (piece.black) {
			if (inBounds(x + 2, y + 2) && (inBounds(x + 1, y + 1)))
				if (board.getTile(x + 1, y + 1).red) {
					if (!board.getTile(x + 2, y + 2).piece) {
						board.getTile(x + 2, y + 2).isPlayable = true;
						doubleJump(piece, x + 2, y + 2);
					}
				}
			if (inBounds(x + 1, y - 1) && inBounds(x + 2, y - 2))
				if (board.getTile(x + 1, y - 1).red) {
					if (!board.getTile(x + 2, y - 2).piece) {

						board.getTile(x + 2, y - 2).isPlayable = true;
						doubleJump(piece, x + 2, y - 2);
					}
				}
		} else if (piece.red) {
			if (inBounds(x - 1, y + 1) && inBounds(x - 2, y + 2))
				if (board.getTile(x - 1, y + 1).black) {
					if (!board.getTile(x - 2, y + 2).piece) {
						board.getTile(x - 2, y + 2).isPlayable = true;
						doubleJump(piece, x - 2, y + 2);
					}
				}
			if (inBounds(x - 1, y - 1) && inBounds(x - 2, y - 2))
				if (board.getTile(x - 1, y - 1).black) {
					if (!board.getTile(x - 2, y - 2).piece) {
						board.getTile(x - 2, y - 2).isPlayable = true;
						doubleJump(piece, x - 2, y - 2);
					}
				}
		}

	}

	private boolean inBounds(int i, int j) {
		return (i > -1 && i < 8 && j > -1 && j < 8);
	}

	private void resetPlayable() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.getTile(i, j).isPlayable = false;
			}
		}
	}
}
