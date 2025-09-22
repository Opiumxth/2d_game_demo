package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp; // Referecne to the main game panel
    KeyHandler keyH; // Reference to the key input handler

    // Initializes player settings and loads images
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    // Initial values for the player
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 3;
        direction = "down";
    }

    // Loads player sprites
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        // Checks if any movement key is being pressed
        if(keyH.upPressed == true || keyH.downPressed == true ||
           keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed){
                direction = "up";
                y -= speed;
            }
            else if(keyH.downPressed){
                direction = "down";
                y += speed;
            }
            else if(keyH.rightPressed){
                direction = "right";
                x += speed;
            }
            else if(keyH.leftPressed){
                direction = "left";
                x -= speed;
            }

            // This controls the animation frame switching
            spriteCounter ++;
            if(spriteCounter > 15){
                if(spriteNum == 1){
                    spriteNum = 2; // Switch to 2nd sprite
                }
                else if(spriteNum == 2){
                    spriteNum = 1; // Switches back
                }
                spriteCounter = 0; // Resets the counter
            }
        }
    }

    public void draw(Graphics2D g2){
//      g2.setColor(Color.blue);
//      g2.fillRect(x,y,gp.tileSize,gp.tileSize);

        BufferedImage image = null; // Prepares the image to be rendered

        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        // Draw the selected image at the player's position with the correct size
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}