package model;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;

import figures.Bishop;
import figures.King;
import figures.Knight;
import figures.Pawn;
import figures.Queen;
import figures.Rook;

import javax.swing.JLabel;
import java.awt.Font;

public class Board extends JFrame {

	public static JPanel contentPane;

	public static ChessSquare[][] board = new ChessSquare[8][8];
	public static JLabel lblCheck = new JLabel("");
	public static JLabel lblTurn = new JLabel("It's whites turn.");


	public static void createNewBoard() {
		Board board = new Board();
		board.setVisible(true);
	}

	public Board() {
		onCreateBoard();
	}

	//TODO: split in separate methods
	private void onCreateBoard() {
		setGeneralDetails();

		//what is flag about?
		boolean isSquareWhite = false;
		//pop-up bounds
		int bound1 = 35;
		int bound2 = 25;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (row == 0) {
					getFigures(row, col, false);
				} else if (row == 1) {
					board[row][col] = new ChessSquare(new Pawn(row, col, false));
				} else if (row == 6) {
					board[row][col] = new ChessSquare(new Pawn(row, col, true));
				} else if (row == 7) {
					getFigures(row, col, true);
				} else {
					board[row][col] = new ChessSquare(row, col);
				}

				board[row][col].setBorder(new LineBorder(new Color(0, 0, 0)));
				board[row][col].setContentAreaFilled(false);
				if (isSquareWhite) {
					isSquareWhite = false;
					board[row][col].setBackground(Color.WHITE);
				} else {
					isSquareWhite = true;
					board[row][col].setBackground(Color.GRAY);
				}
				if (col == board.length - 1) {
					if (isSquareWhite) {
						isSquareWhite = false;
					} else {
						isSquareWhite = true;
					}
				}
				board[row][col].setOpaque(true);
				board[row][col].setHorizontalTextPosition(SwingConstants.CENTER);
				board[row][col].setBounds(bound1, bound2, 35, 35);
				board[row][col].setIcon(new ImageIcon(board[row][col].getFigure().icon));
				contentPane.add(board[row][col]);
				board[row][col].clickListener();
				bound1 += 34;
			}
			bound2 += 34;
			bound1 = 35;
		}
	}

	private void setGeneralDetails() {
		setTitle("CHESS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCheck.setBounds(314, 57, 86, 22);
		contentPane.add(lblCheck);
		
		
		lblTurn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTurn.setBounds(314, 24, 93, 14);
		contentPane.add(lblTurn);
	}

	//what does it do? why name starts with get?
	private void getFigures(int i, int j, boolean flag) {
		switch (j) {
		case 0:
		case 7:
			board[i][j] = new ChessSquare(new Rook(i, j, flag));
			break;
		case 1:
		case 6:
			board[i][j] = new ChessSquare(new Knight(i, j, flag));
			break;
		case 2:
		case 5:
			board[i][j] = new ChessSquare(new Bishop(i, j, flag));
			break;
		case 3:
			board[i][j] = new ChessSquare(new Queen(i, j, flag));
			break;
		case 4:
			board[i][j] = new ChessSquare(new King(i, j, flag));
			break;
		default:
			board[i][j] = new ChessSquare(i, j);
		}
	}

	public static PlayingFigure getPlayingFigure(int x, int y) {
		if (x < 8 && x >= 0 && y < 8 && y >= 0) {
			return board[x][y].getFigure();
		}
		return null;
	}

	public static int[] isCheckActive() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (getPlayingFigure(i, j).icon.equals(PlayingFigure.BLACK_KING)) {
					if (isKingThreatened(i, j, false)) {
						return new int[] { 1, i, j };
					}
				}
				if (getPlayingFigure(i, j).icon.equals(PlayingFigure.WHITE_KING)) {
					if (isKingThreatened(i, j, true)) {
						return new int[] { -1, i, j };
					}
				}

			}
		}
		return new int[] { 0, -1, -1 };
	}

	private static boolean isKingThreatened(int x, int y, boolean isWhite) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (getPlayingFigure(i,j).isAFigure && getPlayingFigure(i, j).isWhite != isWhite) {
					if (getPlayingFigure(i, j).isMovePossible(x, y)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//not used TODO: check if game can be played without it
	private boolean areThereMoves(int x, int y){
		boolean [] moves = {false, false, false, false, false, false, false, false};
		boolean flag = false;
		if (getPlayingFigure(x, y).isMovePossible(x + 1, y)) {
			moves[0] = isKingThreatened(x + 1, y, false);
		}
		if (getPlayingFigure(x, y).isMovePossible(x + 1, y + 1)) {
			moves[1] = isKingThreatened(x + 1, y + 1, false);
		}
		if (getPlayingFigure(x, y).isMovePossible(x, y + 1)) {
			moves[2] = isKingThreatened(x, y + 1, false);
		}
		if (getPlayingFigure(x, y).isMovePossible(x - 1, y)) {
			moves[3] = isKingThreatened(x - 1, y, false);
		}
		if (getPlayingFigure(x, y).isMovePossible(x - 1, y - 1)) {
			moves[4] = isKingThreatened(x - 1, y - 1, false);
		}
		if (getPlayingFigure(x, y).isMovePossible(x, y - 1)) {
			moves[5] = isKingThreatened(x, y - 1, false);
		}
		if (getPlayingFigure(x, y).isMovePossible(x + 1, y - 1)) {
			moves[6] = isKingThreatened(x + 1, y - 1, false);
		}
		if (getPlayingFigure(x, y).isMovePossible(x - 1, y + 1)) {
			moves[7] = isKingThreatened(x - 1, y + 1, false);
		}
		for (boolean b : moves) {
			if (b == true){
				return b;
			}
		}
		return flag;
	}



	

}
