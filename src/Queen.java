
public class Queen extends PlayingFigure {

	public Queen(int i, int j, boolean isWhite) {
		super(i, j, isWhite);
		if(this.isWhite){
			this.icon = WHITE_QUEEN;
		} else {
			this.icon = BLACK_QUEEN;
		}
	}

	@Override
	protected boolean isMovePossible(int x, int y) {
		return super.isMovePossible(x, y) && ((Math.abs(this.coordinateX - x) == Math.abs(this.coordinateY - y))
				|| (x == this.coordinateX || y == this.coordinateY));
	}

}
