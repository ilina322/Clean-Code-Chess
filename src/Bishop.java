
public class Bishop extends PlayingFigure {

	public Bishop(int i, int j, boolean isWhite) {
		super(i, j, isWhite);
		if(this.isWhite){
			this.icon = WHITE_BISHOP;
		} else {
			this.icon = BLACK_BISHOP;
		}
	}

	@Override
	protected boolean isMovePossible(int x, int y) {
		return super.isMovePossible(x, y) && (Math.abs(this.coordinateX - x) == Math.abs(this.coordinateY - y));

	}

}
