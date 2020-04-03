package figures;

import model.PlayingFigure;

public class Bishop extends PlayingFigure {

	public Bishop(int initialX, int initialY, boolean isWhite) {
		super(initialX, initialY, isWhite);
		if(this.isWhite){
			this.icon = WHITE_BISHOP;
		} else {
			this.icon = BLACK_BISHOP;
		}
	}

	@Override
	public boolean isMovePossible(int destinationX, int destinationY) {
		return super.isMovePossible(destinationX, destinationY) && (Math.abs(this.coordinateX - destinationX) == Math.abs(this.coordinateY - destinationY));

	}

}
