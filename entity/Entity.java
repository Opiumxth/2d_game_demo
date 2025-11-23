package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int worldX, worldY;
    public int speed;
    public String name;

    // Stores buffered images(sprites) to be rendered with ImageIO
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction; // Actual movement direction

    public int spriteCounter = 0; // Counter that controls the animation timing
    public int spriteNum = 1; // Counter that tracks which buffered sprites should be displayed

    public Rectangle solidArea; // This will be the collisionArea for any entity

    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false; 
    
    public abstract void update();
    public abstract void draw(Graphics2D g2);
}