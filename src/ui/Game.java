package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import figures.EmptyFigure;
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

	private ActionListener listener;
	private boolean isFirstClickForTurn;
	public static JPanel contentPane;
	public static JLabel lblCheck;
	public static JLabel lblTurn;
	public Board board;

	// just for test
	private int currRow;
	private int currCol;
	private int destRow;
	private int destCol;
	private PlayingFigure currFigure;
	private ChessSquare[][] visualBoard;

	Game() {
		board = new Board();
		visualBoard = new ChessSquare[BOARD_WIDTH][BOARD_HEIGTH];
		isFirstClickForTurn = true;
		listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChessSquare currSquare = (ChessSquare) e.getSource();
				if (isFirstClickForTurn) {
					currRow = currSquare.row;
					currCol = currSquare.col;
					currFigure = board.getAt(currRow, currCol);
				} else {
					destRow = currSquare.row;
					destCol = currSquare.col;
					if(board.moveFigureTo(currFigure, destRow, destCol)) {
						updateVisualBoard(currFigure, destRow, destCol);
					}
				}
				isFirstClickForTurn = !isFirstClickForTurn;
			}
		};
		setGeneralDetails();
		setupUserInterface();
	}

	public static void newInstance() {
		Game instance = new Game();
		instance.setVisible(true);
	}

	// TODO: use this method and switch flag on every move
	private void isWhiteTurn() {
		if (isFirstClickForTurn) {
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
		// TODO: separate bounds from square setup
		int start = 35;
		int end = 25;
		for (int row = 0; row < BOARD_HEIGTH; row++) {
			for (int col = 0; col < BOARD_WIDTH; col++) {
				ChessSquare square = new ChessSquare(start, end, row, col);
				if (isCurrentSquareWhite) {
					square.setBackground(Color.WHITE);
				} else {
					square.setBackground(Color.GRAY);
				}
				isCurrentSquareWhite = !isCurrentSquareWhite;
				PlayingFigure currFigure = board.getAt(row, col);
				square.setIcon(new ImageIcon(currFigure.icon));
				square.addActionListener(listener);
				contentPane.add(square);
				visualBoard[row][col] = square;
				start += 34;
			}
			isCurrentSquareWhite = !isCurrentSquareWhite;
			end += 34;
			start = 35;
		}
	}
	
	private void updateVisualBoard(PlayingFigure currFigure, int destRow, int destCol) {
		int currRow = currFigure.row;
		int currCol = currFigure.column;
		ImageIcon icon = new ImageIcon(currFigure.icon);
		ImageIcon emptyIcon = new ImageIcon(new EmptyFigure(0, 0, true).icon);
		ChessSquare currSquare = visualBoard[currRow][currCol];
		ChessSquare destSquare = visualBoard[destRow][destCol];
		currSquare.setIcon(emptyIcon);
		destSquare.setIcon(icon);
	}

}
