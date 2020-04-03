
public class King extends PlayingFigure {

	public King(int i, int j, boolean isWhite) {
		super(i, j, isWhite);
		if(this.isWhite){
			this.icon = WHITE_KING;
		} else {
			this.icon = BLACK_KING;
		}
	}

	@Override
	protected boolean isMovePossible(int x, int y) {
		return super.isMovePossible(x, y)
				&& (Math.abs(x - this.coordinateX) <= 1 &&  Math.abs(y - this.coordinateY) <= 1 );
	}
}
