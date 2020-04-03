package figures;

import model.PlayingFigure;

public class EmptyFigure extends PlayingFigure {

	public EmptyFigure(int initialX, int initialY, boolean isWhite) {
		super(initialX, initialY, isWhite);
		this.isAFigure = false;
		this.icon = EMPTY;
	}
	
	@Override 
	public void move(int destinationX, int destinationY){
		
	}

}
