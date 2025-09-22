package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // This allows the use rto close the window with X
        window.setResizable(false);
        window.setTitle("Game");

        // The game executes here
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // Adding the game into the window
        window.pack(); // This adjusts the window size to fit the gamePanle

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}