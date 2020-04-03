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

	private PlayingFigure figure;	

	public ChessSquare(int start, int end) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentAreaFilled(false);
		setOpaque(true);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setBounds(start, end, 35, 35);
	}

	public void setFigure(PlayingFigure figure) {
		this.figure = figure;

	}

	public PlayingFigure getFigure() {
		return this.figure;
	}

}
