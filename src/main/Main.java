package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame(); // Creating the window were everything will be happening
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // This allows the user to close the window with X
        window.setResizable(false);
        window.setTitle("Game");

        // The game executes here
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // Adding the game into the window
        window.pack(); // This adjusts the window size to fit the gamePanel

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}