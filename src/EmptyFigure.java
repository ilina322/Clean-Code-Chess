
public class EmptyFigure extends PlayingFigure {

	public EmptyFigure(int i, int j, boolean isWhite) {
		super(i, j, isWhite);
		this.isAFigure = false;
		this.icon = EMPTY;
	}
	
	@Override 
	public void move(int x, int y){
		
	}

}
