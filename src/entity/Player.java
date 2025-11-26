package entity;

import main.GamePanel;
import main.KeyHandler;

import object.OBJ_Chest;
import object.SuperObject;
import util.Inventory;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp; // Referecne to the main game panel
    KeyHandler keyH; // Reference to the key input handler

    // Player's position
    public final int screenX;
    public final int screenY;

    public int hasKey = 0;
    public int specialKey = 0;

    // Initializes player settings and loads images
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        // This centers the player on the screen
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(); // Instantiating the collision rectangle
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 30;

        setDefaultValues();
        getPlayerImage();
    }

    // Initial values for the player
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
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
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.rightPressed){
                direction = "right";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }

            // CHeck tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this); // Even tho we're in the PLayer class, we can pass "this" as an Entity argument cause Player inherits from Entity

            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            // "If collision is false, Player can move"
            if (collisionOn == false){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
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

    public Inventory<SuperObject> inventory = new Inventory<>();

    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Keys: " + hasKey);
                    break;
                case "SpecialKey":
                    specialKey++;
                    gp.obj[i] = null;
                    System.out.println("Obtuviste la llave especial! Ya puedes abrir el cofre");
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("Keys: " + hasKey);
                    break;
                case "Boots":
                    inventory.add(gp.obj[i]);   // Guarda las botas en inventario
                    gp.obj[i] = null;           // Desaparecen del mapa
                    gp.ui.showMessage("Botas añadidas al inventario");
                    break;
                case "Chest":
                    OBJ_Chest chest = (OBJ_Chest) gp.obj[i];
                    if(chest.opened) {
                        // ✅ Ya está abierto → No hacer nada
                        break;
                    }
                    if(gp.player.specialKey > 0) {
                        chest.open();
                        gp.player.specialKey--;
                        gp.ui.showMessage("¡Has ganado!");
                    }
                    else {
                        gp.ui.showMessage("Necesitas una llave especial");
                    }
                    break;
            }
        }
    }

    public boolean bootsEquipped = false;

    public void equipBoots() {
        if(!bootsEquipped){
            speed += 2;       // activar velocidad
            bootsEquipped = true;
            gp.ui.showMessage("¡Botas equipadas!");
        } else {
            speed -= 2;       // desactivar velocidad (si quieres permitir quitar)
            bootsEquipped = false;
            gp.ui.showMessage("Botas desequipadas");
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}