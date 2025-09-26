package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    // Stores buffered images(sprites) to be rendered with ImageIO
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction; // Actual movement direction

    public int spriteCounter = 0; // Counter that controls the animation timing
    public int spriteNum = 1; // Counter that tracks which buffered sprites should be displayed
}