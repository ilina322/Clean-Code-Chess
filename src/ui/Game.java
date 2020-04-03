package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Board;
import model.PlayingFigure;

public class Game extends JFrame {

	private static final String FONT_TAHOMA = "Tahoma";
	private static final String CHESS = "CHESS";
	private static final String EMPTY_TEXT = "";
	private static final String WHITE_TURN = "It's whites turn.";
	private static final String BLACK_TURN = "It's blacks turn.";
	private static final int BOARD_HEIGTH = 8;
	private static final int BOARD_WIDTH = 8;

	public static JPanel contentPane;
	public static JLabel lblCheck;
	public static JLabel lblTurn;
	private static boolean turnWhite;
	public Board board;

	Game() {
		board = new Board();
		turnWhite = true;
		setGeneralDetails();
		setupUserInterface();
	}

	public static void newInstance() {
		Game instance = new Game();
		instance.setVisible(true);
	}

	//TODO: use this method and switch flag on every move
	private void isWhiteTurn() {
		if (turnWhite) {
			lblTurn.setText(WHITE_TURN);
		} else {
			lblTurn.setText(BLACK_TURN);
		}

	}

	private void setGeneralDetails() {
		setTitle(CHESS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCheck = new JLabel(EMPTY_TEXT);
		lblTurn = new JLabel(WHITE_TURN);
		lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck.setFont(new Font(FONT_TAHOMA, Font.PLAIN, 14));
		lblCheck.setBounds(314, 57, 86, 22);
		contentPane.add(lblCheck);

		lblTurn.setFont(new Font(FONT_TAHOMA, Font.PLAIN, 13));
		lblTurn.setBounds(314, 24, 93, 14);
		contentPane.add(lblTurn);
	}
	
	private void setupUserInterface() {
	boolean isCurrentSquareWhite = false;
	//TODO: what do bounds do? 
	int bound1 = 35;
	int bound2 = 25;
	for (int row = 0; row < BOARD_HEIGTH; row++) {
		for (int col = 0; col < BOARD_WIDTH; col++) {
			ChessSquare square = new ChessSquare(bound1, bound2);
			if (isCurrentSquareWhite) {
				isCurrentSquareWhite = false;
				square.setBackground(Color.WHITE);
			} else {
				isCurrentSquareWhite = true;
				square.setBackground(Color.GRAY);
			}
			if (col == BOARD_WIDTH - 1) {
				if (isCurrentSquareWhite) {
					isCurrentSquareWhite = false;
				} else {
					isCurrentSquareWhite = true;
				}
			}
			square.setIcon(new ImageIcon(board.getAt(row, col).icon));
			//TODO: set on click listener to squares
			//board[row][col].clickListener();
			contentPane.add(square);
			bound1 += 34;
		}
		bound2 += 34;
		bound1 = 35;
	}
}

}
