import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class Board extends JFrame {

	public static JPanel contentPane;

	public static ChessSquare[][] board = new ChessSquare[8][8];
	public static JLabel check = new JLabel("");
	public static JLabel turn = new JLabel("It's whites turn.");


	public static void createNewBoard() {
		Board board = new Board();
		board.setVisible(true);
	}

	public Board() {
		init();
	}

	private void init() {
		getGeneralDetails();

		boolean flag = false;
		int bound1 = 35;
		int bound2 = 25;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (i == 0) {
					getFigures(i, j, false);
				} else if (i == 1) {
					board[i][j] = new ChessSquare(new Pawn(i, j, false));
				} else if (i == 6) {
					board[i][j] = new ChessSquare(new Pawn(i, j, true));
				} else if (i == 7) {
					getFigures(i, j, true);
				} else {
					board[i][j] = new ChessSquare(i, j);
				}

				board[i][j].setBorder(new LineBorder(new Color(0, 0, 0)));
				board[i][j].setContentAreaFilled(false);
				if (flag) {
					flag = false;
					board[i][j].setBackground(Color.WHITE);
				} else {
					flag = true;
					board[i][j].setBackground(Color.GRAY);
				}
				if (j == board.length - 1) {
					if (flag) {
						flag = false;
					} else {
						flag = true;
					}
				}
				board[i][j].setOpaque(true);
				board[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
				board[i][j].setBounds(bound1, bound2, 35, 35);
				board[i][j].setIcon(new ImageIcon(board[i][j].getFigure().icon));
				contentPane.add(board[i][j]);
				board[i][j].clickListener();
				bound1 += 34;
			}
			bound2 += 34;
			bound1 = 35;
		}
	}

	private void getGeneralDetails() {
		setTitle("CHESS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		check.setHorizontalAlignment(SwingConstants.CENTER);
		check.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check.setBounds(314, 57, 86, 22);
		contentPane.add(check);
		
		
		turn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		turn.setBounds(314, 24, 93, 14);
		contentPane.add(turn);
	}

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

//	private boolean mate() {
//		if (isCheckActive()[0] == 1) {
//			int x = isCheckActive()[1];
//			int y = isCheckActive()[2];
//			boolean move = areThereMoves(x,y);
//			
//			
//			
//		}
//	}
	
	
	
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
