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

	private static boolean clicked = false;
	private static int xA;
	private static int yA;
	private static int xB;
	private static int yB;
	private static PlayingFigure figureA;
	private static PlayingFigure figureB;

	

	public ChessSquare(int start, int end) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentAreaFilled(false);
		setOpaque(true);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setBounds(start, end, 35, 35);
	}

//	public void clickListener() {
//		this.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (turnWhite) {
//					getNextTurn(true);
//					
//				} else {
//					getNextTurn(false);
//				}
//			}
//
//			private void getNextTurn(boolean isWhite) {
//				isWhiteTurn();
//				if (figure.isAFigure) {
//					figure.possibleMoves();
//					if (getFigure().isWhite == isWhite) {
//						clicked = true;
//						xA = getFigure().coordinateX;
//						yA = getFigure().coordinateY;
//						figureA = getFigure();
//					} else {
//						moveFigure();
//						figure.possibleMoves();
//					}
//				} else {
//					moveFigure();
//					for(int i = 0; i<8; i++){
//						for (int j=0; j<8;j++){
//							Board.board[i][j].setBorder(new LineBorder(new Color(0, 0, 0)));
//						}
//					}
//					clicked = false;
//					
//				}
//
//			}
//			
//			//TODO: this should not be here
//			private void moveFigure() {
//				if (clicked) {
//					int a = turnWhite ? -1 : 1;
//					xB = getFigure().coordinateX;
//					yB = getFigure().coordinateY;
//					figureB = getFigure();
//					if (Board.getPlayingFigure(xA, yA).isMovePossible(xB, yB)) {
//						clicked = false;
//						Board.board[xA][yA].getFigure().move(xB, yB);
//						Board.board[xB][yB].setFigure(Board.board[xA][yA].getFigure());
//						Board.board[xB][yB].setIcon(new ImageIcon(Board.board[xB][yB].getFigure().icon));
//						Board.board[xA][yA].setFigure(new EmptyFigure(xA, yA, false));
//						Board.board[xA][yA].setIcon(new ImageIcon(PlayingFigure.EMPTY));
//
//						if (Board.isCheckActive()[0] == a) {
//							reverseMove();
//						}
//						if (Board.isCheckActive()[0] == 1 || Board.isCheckActive()[0] == -1) {
//							Board.lblCheck.setText("CHECK!");
//							Board.lblCheck.setVisible(true);
//							Board.contentPane.add(Board.lblCheck);
//						}
//						turnWhite = !turnWhite;
//					}
//				}
//			}
//
//		});
//	}
//
//	private void reverseMove() {
//
//		Board.board[xA][yA].setFigure(new EmptyFigure(xA, yA, false));
//		Board.board[xA][yA].setIcon(new ImageIcon(PlayingFigure.EMPTY));
//
//		Board.board[xB][yB].setFigure(new EmptyFigure(xB, yB, false));
//		Board.board[xB][yB].setIcon(new ImageIcon(PlayingFigure.EMPTY));
//
//		figureA.coordinateX = xA;
//		figureA.coordinateY = yA;
//		Board.board[xA][yA].setFigure(figureA);
//		Board.board[xA][yA].setIcon(new ImageIcon(figureA.icon));
//
//		figureB.coordinateX = xB;
//		figureB.coordinateY = yB;
//		Board.board[xB][yB].setFigure(figureB);
//		Board.board[xB][yB].setIcon(new ImageIcon(figureB.icon));
//		turnWhite = !turnWhite;
//
//	}

	public void setFigure(PlayingFigure figure) {
		this.figure = figure;

	}

	public PlayingFigure getFigure() {
		return this.figure;
	}

}
