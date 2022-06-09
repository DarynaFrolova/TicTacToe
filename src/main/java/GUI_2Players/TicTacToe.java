package GUI_2Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {

    private String currentPlayer;
    private final JButton[][] board;
    private boolean hasWinner;

    JFrame frame = new JFrame("Tic Tac Toe");
    JPanel mainPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    public TicTacToe() {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        mainPanel.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new GridLayout(3, 3));
        board = new JButton[3][3];
        hasWinner = false;
        currentPlayer = "X";
        initializeBoard();
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                btn.setFont(new Font(Font.SERIF, Font.BOLD, 90));
                if (btn.getText().equals("0")) {
                    btn.setForeground(Color.BLUE);
                } else {
                    btn.setForeground(Color.BLACK);
                }
                board[i][j] = btn;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton) e.getSource()).getText().equals("") && !hasWinner) {
                            if (btn.getText().equals("0")) {
                                btn.setForeground(Color.BLUE);
                            } else {
                                btn.setForeground(Color.BLACK);
                            }
                            btn.setText(currentPlayer);
                            if (btn.getText().equals("0")) {
                                btn.setForeground(Color.BLUE);
                            } else {
                                btn.setForeground(Color.BLACK);
                            }
                            hasWinner();
                            togglePlayer();
                        }
                    }
                });
                buttonsPanel.add(btn);
            }
        }
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(newGame, BorderLayout.NORTH);
    }

    private void resetBoard() {
        currentPlayer = "X";
        hasWinner = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
            }
        }
    }

    private void togglePlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "0";
        } else {
            currentPlayer = "X";
        }
    }

    private void hasWinner() {
        for (int i = 0; i < 3; i++) {
            int d = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getText().equals(currentPlayer)) {
                    d++;
                }
            }
            if (d == 3) {
                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won!");
                hasWinner = true;
                break;
            }
        }
        for (int j = 0; j < 3; j++) {
            int d = 0;
            for (int i = 0; i < 3; i++) {
                if (board[i][j].getText().equals(currentPlayer)) {
                    d++;
                }
            }
            if (d == 3) {
                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won!");
                hasWinner = true;
                break;
            }
        }
        int d = 0;
        for (int i = 0, j = 0; i < 3; i++, j++) { // Главная диагональ
            if (board[i][j].getText().equals(currentPlayer)) {
                d++;
            }
            if (d == 3) {
                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won!");
                hasWinner = true;
                break;
            }
        }
        d = 0;
        for (int i = 0, j = 3 - 1; i < 3; i++, j--) { // Побочная диагональ
            if (board[i][j].getText().equals(currentPlayer)) {
                d++;
            }
            if (d == 3) {
                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won!");
                hasWinner = true;
                break;
            }
        }
        d = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board[i][j].getText().isBlank()) {
                    d++;
                }
            }
            if (d == 9 && !hasWinner) {
                JOptionPane.showMessageDialog(null, "Draw!");
                break;
            }
        }
    }
}