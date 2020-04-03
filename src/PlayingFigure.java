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

	int coordinateX;
	int coordinateY;
	boolean isDead;
	boolean isWhite;
	boolean isAFigure;
	String icon;

	public PlayingFigure(int i, int j, boolean isWhite) {
		if ((i >= 0 || i < 8) && (j >= 0 || j < 8)) {
			this.coordinateX = i;
			this.coordinateY = j;
			this.isWhite = isWhite;
			this.icon = EMPTY;
			this.isAFigure = true;
		}
	}

	public void border(int x, int y) {
		Board.board[x][y].setBorder(new LineBorder(new Color(50, 205, 50), 2));

	}

	public void move(int x, int y) {
		if (isMovePossible(x, y) && !this.isDead) {
			this.coordinateX = x;
			this.coordinateY = y;
		}
	}

	public void possibleMoves() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (isMovePossible(i, j) && !Board.board[i][j].isFigWhite() == this.isWhite) {
					border(i, j);
				}
			}
		}
	}

	protected boolean isMovePossible(int x, int y) {
		return (x >= 0 || x < 8) && (y >= 0 || y < 8) && !isSomethingInTheWay(x, y);
	}

	private boolean isSomethingInTheWay(int x, int y) {
		return checkHorizontal(x, y) || checkVertical(x, y) || checkDiagonals(x, y);

	}

	private boolean checkHorizontal(int x, int y) {
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

	private boolean checkVertical(int x, int y) {
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

	
	private boolean checkDiagonals(int x, int y) {

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
