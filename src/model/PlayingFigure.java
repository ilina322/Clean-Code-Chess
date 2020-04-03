package model;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class PlayingFigure {

	public static final String BLACK_PAWN = "src\\resources\\pawn_black.png";
	public static final String BLACK_ROOK = "src\\resources\\rook_black.png";
	public static final String BLACK_KNIGHT = "src\\resources\\knight_black.png";
	public static final String BLACK_BISHOP = "src\\resources\\bishop_black.png";
	public static final String BLACK_QUEEN = "src\\resources\\queen_black.png";
	public static final String BLACK_KING = "src\\resources\\king_black.png";
	public static final String WHITE_PAWN = "src\\resources\\pawn_white.png";
	public static final String WHITE_ROOK = "src\\resources\\rook_white.png";
	public static final String WHITE_KNIGHT = "src\\resources\\knight_white.png";
	public static final String WHITE_BISHOP = "src\\resources\\bishop_white.png";
	public static final String WHITE_QUEEN = "src\\resources\\queen_white.png";
	public static final String WHITE_KING = "src\\resources\\king_white.png";
	public static final String EMPTY = "empty";

	public int coordinateX;
	public int coordinateY;
	public boolean isDead;
	public boolean isWhite;
	public boolean isAFigure;
	public String icon;

	public PlayingFigure(int initialX, int initialY, boolean isWhite) {
		if ((initialX >= 0 || initialX < 8) && (initialY >= 0 || initialY < 8)) {
			this.coordinateX = initialX;
			this.coordinateY = initialY;
			this.isWhite = isWhite;
			this.icon = EMPTY;
			this.isAFigure = true;
		}
	}

	//TODO: move to board
	public void border(int x, int y) {
		Board.board[x][y].setBorder(new LineBorder(new Color(50, 205, 50), 2));
	}

	public void move(int destinationX, int destinationY) {
		if (isMovePossible(destinationX, destinationY) && !this.isDead) {
			this.coordinateX = destinationX;
			this.coordinateY = destinationY;
		}
	}

	//what is the purpose?
	public void possibleMoves() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (isMovePossible(i, j) && !Board.board[i][j].isFigWhite() == this.isWhite) {
					border(i, j);
				}
			}
		}
	}

	public boolean isMovePossible(int x, int y) {
		return (x >= 0 || x < 8) && (y >= 0 || y < 8) && !checkObstaclesOnPath(x, y);
	}

	private boolean checkObstaclesOnPath(int x, int y) {
		return checkHorizontalObstacles(x, y) || checkVerticalObstacles(x, y) || checkDiagonalObstacles(x, y);

	}

	private boolean checkHorizontalObstacles(int x, int y) {
		if (this.coordinateY == y) {
			if (this.coordinateX > x) {
				for (int i = this.coordinateX - 1; i > x; i--) {
					if (!Board.board[i][y].getFigure().icon.equals(EMPTY)) {
						return true;
					}
				}
			} else {
				for (int i = this.coordinateX + 1; i < x; i++) {
					if (!Board.board[i][y].getFigure().icon.equals(EMPTY)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean checkVerticalObstacles(int x, int y) {
		if (this.coordinateX == x) {
			if (this.coordinateY > y) {
				for (int i = this.coordinateY - 1; i > y; i--) {
					if (!Board.board[x][i].getFigure().icon.equals(EMPTY)) {
						return true;
					}
				}
			} else {
				for (int i = this.coordinateY + 1; i < y; i++) {
					if (!Board.board[x][i].getFigure().icon.equals(EMPTY)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	
	private boolean checkDiagonalObstacles(int x, int y) {

		for (int i = 1; i < Math.abs(this.coordinateX - x); i++) {
			if (x + y == this.coordinateX + this.coordinateY) {
				if (i < x + y) {
					if (this.coordinateX < x) {
						if (this.coordinateX + i < 8 && this.coordinateY - i > -1) {
							if (!Board.board[this.coordinateX + i][this.coordinateY - i].getFigure().icon
									.equals(EMPTY)) {
								return true;
							}
						}
					} else if (this.coordinateX - i > -1 && this.coordinateY + i < 8) {
						if (!Board.board[this.coordinateX - i][this.coordinateY + i].getFigure().icon.equals(EMPTY)) {
							return true;
						}
					}
				}

			}
			if (x - y == this.coordinateX - this.coordinateY) {
				if (x > this.coordinateX) {
					if (this.coordinateX + i < 8 && this.coordinateY + i < 8) {
						if (!Board.board[this.coordinateX + i][this.coordinateY + i].getFigure().icon.equals(EMPTY)) {
							return true;
						}
						
					}
				} else {
					if (this.coordinateX - i > -1 && this.coordinateY - i > -1) {
						if (!Board.board[this.coordinateX - i][this.coordinateY - i].getFigure().icon.equals(EMPTY)) {
							return true;
						}
					}
					
				}
			}
		}
		return false;
	}

}
