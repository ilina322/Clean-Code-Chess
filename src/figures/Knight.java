package figures;

import model.PlayingFigure;

public class Knight extends PlayingFigure {

	public Knight(int initialX, int initialY, boolean isWhite) {
		super(initialX, initialY, isWhite);
		if (this.isWhite) {
			this.icon = WHITE_KNIGHT;
		} else {
			this.icon = BLACK_KNIGHT;
		}
	}

	@Override
	public boolean isMovePossible(int destinationX, int destinationY) {
		System.out.println();
		System.out.println("it's not moved " + destinationX + " " + destinationY + "\n " + this.coordinateX + " "+ this.coordinateY);
		return (destinationX >= 0 || destinationX < 8) && (destinationY >= 0 || destinationY < 8)
				&& ((Math.abs(this.coordinateX - destinationX) == 1 && Math.abs(this.coordinateY - destinationY) == 2)
						|| (Math.abs(this.coordinateX - destinationX) == 2 && Math.abs(this.coordinateY - destinationY) == 1));
	}

}
