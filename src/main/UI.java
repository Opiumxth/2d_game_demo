package main;

import object.OBJ_Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp; // Reference to the main game panel
    Font arial_40; // Font for the ui text
    BufferedImage keyImage; // Key icon image

    // Constructor, this initializes the ui elements
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    // Draws ui elements on screen, used in GamePanel class
    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 74, 65);
    }
}