package ui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import figures.EmptyFigure;
import model.Board;
import model.PlayingFigure;

public class ChessSquare extends JButton {

	private static final int SQUARE_SIDE = 35;
	//TODO: change to private + getters and setters
	public int row;
	public int col;

	public ChessSquare(int start, int end, int row, int col) {
		this.row = row;
		this.col = col;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentAreaFilled(false);
		setOpaque(true);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setBounds(start, end, SQUARE_SIDE, SQUARE_SIDE);
	}
}
