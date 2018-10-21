package com.company;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class TwoPlayerGame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    /**
     * The content paneL.
     */
    private JPanel contentPane;

    /**
     * The win combinations.
     */
    private int[][] winCombinations = new int[][]{
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal wins
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //vertical wins
            {0, 4, 8}, {2, 4, 6}             //diagonal wins
    };

    /**
     * The buttons.
     */
    private JButton buttons[] = new JButton[9];

    /**
     * The letter.
     */
    private String letter = "O";

    /**
     * The count.
     */
    private int count = 0;

    /**
     * The win.
     */
    private boolean win = false;

    /**
     * The lbl new label.
     */
    JLabel lblNewLabel = new JLabel("New label");

    private int scorec = 0;

    private int scorep = 0;

    /**
     * The scorePanel new label.
     */
    JLabel scorePanel = new JLabel("");

    /**
     * Html string variable.
     */
    String HTMLlabelStr = "";

    public TwoPlayerGame() {
        setTitle("Tic-Tac-Toe: Two Player Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(291, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setForeground(Color.WHITE);
        panel.setBackground(Color.GRAY);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(10, 11, 264, 264);
        contentPane.add(panel);


        panel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i <= 8; i++) {
            buttons[i] = new JButton("");
            panel.add(buttons[i]);
            buttons[i].setBackground(SystemColor.menu);
            buttons[i].addActionListener(this);
        }

		/*Below Panel*/
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(69, 154, 204));
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.setBounds(10, 286, 264, 130);
        contentPane.add(panel_1);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBackground(new Color(255, 94, 221));

        lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        lblNewLabel.setPreferredSize(new Dimension(250, 25));
        lblNewLabel.setMinimumSize(new Dimension(270, 30));
        lblNewLabel.setMaximumSize(new Dimension(270, 30));
        lblNewLabel.setText("Start The Game- Player X turn.");
        panel_1.add(lblNewLabel);

		/*Tool buttons*/
        JButton btnNewButton = new JButton("Exit");
        btnNewButton.setForeground(new Color(255, 94, 19));
        btnNewButton.setBackground(SystemColor.menu);
        btnNewButton.setToolTipText("Exit");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scorec = 0;
                scorep = 0;
                buttons = null;
                System.exit(0);
            }
        });
        panel_1.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Reset");
        btnNewButton_1.setForeground(new Color(18, 204, 147));
        btnNewButton_1.setBackground(SystemColor.menu);
        btnNewButton_1.setToolTipText("Reset");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                resetBoard();
            }
        });
        panel_1.add(btnNewButton_1);

		/*Score Panel*/
        scorePanel.setForeground(Color.WHITE);
        scorePanel.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.setFont(new Font("Tahoma", Font.BOLD, 30));
        scorePanel.setBackground(new Color(255, 102, 0));

        scorePanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        scorePanel.setPreferredSize(new Dimension(250, 58));
        scorePanel.setMinimumSize(new Dimension(250, 30));
        scorePanel.setMaximumSize(new Dimension(250, 30));
        updateScoreBoard(0, 0);
        panel_1.add(scorePanel);

		/*JMenu*/
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Updates the Score Board.
     */
    public void updateScoreBoard(int o, int x) {
        HTMLlabelStr = "<html><b color='green'>" + o + "</b> / <b color='red'>" + x + "</b></html>";
        scorePanel.setText(HTMLlabelStr);
    }

    /**
     * Resets the Score Board.
     */
    public void resetBoard() {
        scorec = 0;
        scorep = 0;
        updateScoreBoard(scorep, scorec);
    }

    /*For Actions performed on button click*/
    public void actionPerformed(ActionEvent a) {
        count++;

		/*Calculate whose turn it is*/
        if (count % 2 == 0) {
            lblNewLabel.setForeground(Color.RED);
            lblNewLabel.setText("Player " + letter + " turn.");
            letter = "O";
        } else {
            lblNewLabel.setForeground(Color.BLUE);
            lblNewLabel.setText("Player " + letter + " turn.");
            letter = "X";
        }

		/*Write the letter to the button and deactivate it*/
        JButton pressedButton = (JButton) a.getSource();
        pressedButton.setFont(new Font("Tahoma", Font.PLAIN, 65));
        pressedButton.setText(letter);
        pressedButton.setForeground(Color.WHITE);
        if (letter == "X") {
            pressedButton.setBackground(Color.RED);
        } else {
            pressedButton.setBackground(Color.BLUE);
        }
        pressedButton.setEnabled(false);


		/*Determine who won*/
        for (int i = 0; i <= 7; i++) {
            if (buttons[winCombinations[i][0]].getText().equals(buttons[winCombinations[i][1]].getText()) && buttons[winCombinations[i][1]].getText().equals(buttons[winCombinations[i][2]].getText()) && buttons[winCombinations[i][0]].getText() != "") {
                win = true;
            }
        }
        /*Message*/
        if (win == true) {
            if (letter.equalsIgnoreCase("X")) {
                scorec++;
            } else {
                scorep++;
            }
            updateScoreBoard(scorep, scorec);
            JOptionPane.showMessageDialog(null, letter + " wins the game!");
            reset();
        } else if (count == 9 && win == false) {
            JOptionPane.showMessageDialog(null, "Game over!");
            reset();
        }
    }

    /*Rest the Game*/
    public void reset() {
        letter = "O";
        win = false;
        count = 0;
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(SystemColor.menu);
            buttons[i].setForeground(Color.blue);
            lblNewLabel.setForeground(Color.white);
            lblNewLabel.setText("Start The Game- Player X turn.");
        }
    }
}
