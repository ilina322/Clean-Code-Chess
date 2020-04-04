package figures;
import model.Board;
import model.PlayingFigure;

public class Pawn extends PlayingFigure {

	private boolean isMoved;

	public Pawn(int initialX, int initialY, boolean isWhite) {
		super(initialX, initialY, isWhite);
		if (this.isWhite) {
			this.icon = WHITE_PAWN;
		} else {
			this.icon = BLACK_PAWN;
		}
		if ((this.isWhite && this.row == 6) || (!this.isWhite && this.row == 1)) {
			this.isMoved = false;
		} else {
			this.isMoved = true;
		}

	}

	@Override
	public boolean isMovePossible(int destinationX, int destinationY) {
		return colorMove(this.isWhite, destinationX, destinationY) && super.isMovePossible(destinationX, destinationY);
	}

	private boolean colorMove(boolean isWhite, int x, int y) {
		int dist;
		if (isWhite) {
			dist = -1;
		} else {
			dist = 1;
		}
		
		//TODO: fix this
		
		if (!this.isMoved) {
			System.out.println();
			System.out.println("it's not moved \n" + x + " " + y + "\n " + this.row + " " + this.column);
			if ((x - this.row == dist || x - this.row == 2 * dist && !Board.board[x][y].isAFigure) && y == this.column
					&& super.isMovePossible(x, y)) {
				System.out.println("move can be done1");
				
				return true;
			}
		} else {
			System.out.println("it is moved");
			if (x - this.row == dist && y == this.column && super.isMovePossible(x, y) && !Board.board[x][y].isAFigure) {
				System.out.println("move can be done2");
				return true;
			}
		}
		return canDestroy(x, y, dist);

	}
	
	@Override
	public void move(int x, int y){
		super.move(x, y);
		this.isMoved = true;
	}

	private boolean canDestroy(int x, int y, int dist) {
		if (Board.board[x][y].isAFigure && Board.board[x][y].isWhite != this.isWhite) {
			System.out.println("they are different");
			if (x - this.row == dist && Math.abs(y - this.column) == 1) {
				System.out.println("can destroy");
				return true;
			}
		}
		return false;

	}

	

}
