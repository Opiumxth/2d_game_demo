package main;

import entity.Player;

import java.awt.*; // Importing java AWT (Abstract Window Toolkit), this provides GUI and graphics classes
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // This means 16 pixels
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // Here we have the final "pixel" size: 48 pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 16 * 48 = 768
    final int screenHeight = tileSize * maxScreenRow; // 12 * 48 = 576

    //Frames per second
    int FPS = 60;

    KeyHandler keyH = new KeyHandler(); // Read the keyboard input
    Thread gameThread;
    Player player = new Player(this, keyH);


    // Player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; // 4 pixels at a time


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // SEting the gamePanel size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // These allow multi buffering, in this case using two buffers
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Instantiate new game loop thread
        gameThread.start(); // Start the game loop
    }


//  @Override
//  public void run() {
//      double drawInterval = 1000000000/FPS;
//      double nextDrawTime = System.nanoTime() + drawInterval;
//
//      while (gameThread != null){
//          long currentTime = System.nanoTime();
//
//          // Update 1: Update information such as character positions
//          update();
//
//          // Draw the screen with the update information
//          repaint();
//
//          try {
//              double remainingTime = nextDrawTime - System.nanoTime();
//              remainingTime = remainingTime/1000000; // Converted to milliseconds
//              if(remainingTime < 0) {
//                  remainingTime = 0;
//              }
//              Thread.sleep((long) remainingTime);
//              nextDrawTime += drawInterval;
//          } catch (InterruptedException e) {
//              throw new RuntimeException(e);
//          }
//      }
//  }

    @Override
    public void run(){
        double drawInterval = 1000000000/FPS; // Time per frame in nanoseconds
        double delta = 0; // This tracks how many updates should the program run
        long lastTime = System.nanoTime(); // Last frame time
        long currentTime;
        long timer = 0; // Counts 1 second
        long drawCount = 0; // Counts frames per second

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval; // Accumulate time
            timer += (currentTime -lastTime); // Add to the 1 sec timer
            lastTime = currentTime;

            if (delta >= 1) {
                update(); // THis updates the game logic
                repaint(); // Rerender the game screen
                delta--;
                drawCount++;
            }

            // Thisr pints in the console the fps
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        // g2.dispose();
        Toolkit.getDefaultToolkit().sync();
    }
}