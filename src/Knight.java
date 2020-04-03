
public class Knight extends PlayingFigure {

	public Knight(int i, int j, boolean isWhite) {
		super(i, j, isWhite);
		if (this.isWhite) {
			this.icon = WHITE_KNIGHT;
		} else {
			this.icon = BLACK_KNIGHT;
		}
	}

	@Override
	protected boolean isMovePossible(int x, int y) {
		System.out.println();
		System.out.println("it's not moved " + x + " " + y + "\n " + this.coordinateX + " "+ this.coordinateY);
		return (x >= 0 || x < 8) && (y >= 0 || y < 8)
				&& ((Math.abs(this.coordinateX - x) == 1 && Math.abs(this.coordinateY - y) == 2)
						|| (Math.abs(this.coordinateX - x) == 2 && Math.abs(this.coordinateY - y) == 1));
	}

}
