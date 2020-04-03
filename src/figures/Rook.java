package figures;

import model.PlayingFigure;

public class Rook extends PlayingFigure {

	public Rook(int initialX, int initialY, boolean isWhite) {
		super(initialX, initialY, isWhite);
		if(this.isWhite){
			this.icon = WHITE_ROOK;
		} else {
			this.icon = BLACK_ROOK;
		}
	}

	@Override
	public boolean isMovePossible(int destinationX, int destinationY) {
		return super.isMovePossible(destinationX, destinationY) && (destinationX == this.coordinateX || destinationY == this.coordinateY);
	}
}
