package figures;

import model.PlayingFigure;

public class Queen extends PlayingFigure {

	public Queen(int initialX, int initialY, boolean isWhite) {
		super(initialX, initialY, isWhite);
		if(this.isWhite){
			this.icon = WHITE_QUEEN;
		} else {
			this.icon = BLACK_QUEEN;
		}
	}

	@Override
	public boolean isMovePossible(int destinationX, int destinationY) {
		return super.isMovePossible(destinationX, destinationY) && ((Math.abs(this.row - destinationX) == Math.abs(this.column - destinationY))
				|| (destinationX == this.row || destinationY == this.column));
	}

}
