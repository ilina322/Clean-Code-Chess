package figures;

import model.PlayingFigure;

public class King extends PlayingFigure {

	public King(int initialX, int initialY, boolean isWhite) {
		super(initialX, initialY, isWhite);
		if(this.isWhite){
			this.icon = WHITE_KING;
		} else {
			this.icon = BLACK_KING;
		}
	}

	@Override
	public boolean isMovePossible(int destinationX, int destinationY) {
		return super.isMovePossible(destinationX, destinationY)
				&& (Math.abs(destinationX - this.coordinateX) <= 1 &&  Math.abs(destinationY - this.coordinateY) <= 1 );
	}
}
