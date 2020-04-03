
public class Pawn extends PlayingFigure {

	private boolean isMoved;

	public Pawn(int i, int j, boolean isWhite) {
		super(i, j, isWhite);
		if (this.isWhite) {
			this.icon = WHITE_PAWN;
		} else {
			this.icon = BLACK_PAWN;
		}
		if ((this.isWhite && this.coordinateX == 6) || (!this.isWhite && this.coordinateX == 1)) {
			this.isMoved = false;
		} else {
			this.isMoved = true;
		}

	}

	@Override
	protected boolean isMovePossible(int x, int y) {
		return colorMove(this.isWhite, x, y) && super.isMovePossible(x, y);
	}

	private boolean colorMove(boolean isWhite, int x, int y) {
		int dist;
		if (isWhite) {
			dist = -1;
		} else {
			dist = 1;
		}
		
		if (!this.isMoved) {
			System.out.println();
			System.out.println("it's not moved \n" + x + " " + y + "\n " + this.coordinateX + " " + this.coordinateY);
			if ((x - this.coordinateX == dist || x - this.coordinateX == 2 * dist && !Board.board[x][y].getFigure().isAFigure) && y == this.coordinateY
					&& super.isMovePossible(x, y)) {
				System.out.println("move can be done1");
				
				return true;
			}
		} else {
			System.out.println("it is moved");
			if (x - this.coordinateX == dist && y == this.coordinateY && super.isMovePossible(x, y) && !Board.board[x][y].getFigure().isAFigure) {
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
		if (Board.board[x][y].getFigure().isAFigure && Board.board[x][y].getFigure().isWhite != this.isWhite) {
			System.out.println("they are different");
			if (x - this.coordinateX == dist && Math.abs(y - this.coordinateY) == 1) {
				System.out.println("can destroy");
				return true;
			}
		}
		return false;

	}

	

}
