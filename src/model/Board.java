package model;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;

import figures.Bishop;
import figures.EmptyFigure;
import figures.King;
import figures.Knight;
import figures.Pawn;
import figures.Queen;
import figures.Rook;
import ui.ChessSquare;

import javax.swing.JLabel;
import java.awt.Font;

public class Board{


	private static final int BOARD_HEIGTH = 8;

	private static final int BOARD_WIDTH = 8;

	public static PlayingFigure[][] board;
	
	public Board() {
		board = new PlayingFigure[BOARD_WIDTH][BOARD_HEIGTH];
		onCreateBoard();
	}

	private void onCreateBoard() {
		setupFigures();
		//setupUserInterface();
	}
	
	public PlayingFigure getAt(int row, int col) {
		if (row < BOARD_WIDTH && row >= 0 && col < BOARD_HEIGTH && col >= 0) {
			return board[row][col];
		}
		return null;
	}

	private void setupFigures() {
		for (int row = 0; row < BOARD_HEIGTH; row++) {
			for (int col = 0; col < BOARD_WIDTH; col++) {
				//here starts putting the pawns
				if (row == 0) {
					//put BLACK figures
					putFigures(row, col, false);
				} else if (row == 1) {
					board[row][col] = new Pawn(row, col, false); //black pawns
				} else if (row == 6) {
					board[row][col] = new Pawn(row, col, true); //white pawns
					//here ends putting pawns, starts putting other WHITE figures
				} else if (row == 7) {
					putFigures(row, col, true);
					//other figures done, fill empty squares
				} else {
					board[row][col] = new EmptyFigure(row, col, true);
				}
			}
		}
	}



	//put figures that are not pawns
	private void putFigures(int i, int j, boolean flag) {
		switch (j) {
		case 0:
		case 7:
			board[i][j] = new Rook(i, j, flag);
			break;
		case 1:
		case 6:
			board[i][j] = new Knight(i, j, flag);
			break;
		case 2:
		case 5:
			board[i][j] = new Bishop(i, j, flag);
			break;
		case 3:
			board[i][j] = new Queen(i, j, flag);
			break;
		case 4:
			board[i][j] = new King(i, j, flag);
			break;
		default:
			board[i][j] = new EmptyFigure(i, j, flag);
		}
	}
	
	public static int[] isCheckActive() {
		for (int row = 0; row < BOARD_HEIGTH; row++) {
			for (int col = 0; col < BOARD_WIDTH; col++) {
				if (board[row][col].icon.equals(PlayingFigure.BLACK_KING)) {
					if (isKingThreatened(row, col, false)) {
						return new int[] { 1, row, col };
					}
				}
				if (board[row][col].icon.equals(PlayingFigure.WHITE_KING)) {
					if (isKingThreatened(row, col, true)) {
						return new int[] { -1, row, col };
					}
				}

			}
		}
		return new int[] { 0, -1, -1 };
	}

	private static boolean isKingThreatened(int x, int y, boolean isWhite) {
		for (int row = 0; row < BOARD_HEIGTH; row++) {
			for (int col = 0; col < BOARD_WIDTH; col++) {
				if (board[row][col].isAFigure && board[row][col].isWhite != isWhite) {
					if (board[row][col].isMovePossible(x, y)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	
	public boolean moveFigureTo(PlayingFigure figure, int destRow, int destCol) {
		System.out.println(figure + " moves to " + destRow + " " + destCol + " is legal: " + figure.isMovePossible(destRow, destCol));
		int currRow = figure.row;
		int currCol = figure.column;
		if(figure.isMovePossible(destRow, destCol)) {
			board[destRow][destCol] = figure;
			board[currRow][currCol] = new EmptyFigure(currRow, currCol, true);
			return true;
		}
		return false;
		
	}

}
