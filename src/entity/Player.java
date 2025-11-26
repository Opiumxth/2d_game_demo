package entity;

import main.GamePanel;
import main.KeyHandler;

import object.OBJ_Chest;
import object.SuperObject;
import util.Inventory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;
    public int specialKey = 0;

    public Inventory<SuperObject> inventory = new Inventory<>();
    public boolean bootsEquipped = false;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 30;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

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

    // ============================================
    // IMPLEMENTACIÓN DE move() DE MOVABLE
    // ============================================
    @Override
    public void move() {
        // Check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        int objIndex = gp.cChecker.checkObject(this, true);
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

            // Llamar al método move() de la interfaz
            move();

            // This controls the animation frame switching
            spriteCounter ++;
            if(spriteCounter > 15){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

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
                    inventory.add(gp.obj[i]);
                    gp.obj[i] = null;
                    gp.ui.showMessage("Botas añadidas al inventario");
                    break;
                case "Chest":
                    OBJ_Chest chest = (OBJ_Chest) gp.obj[i];
                    if(chest.opened) {
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

    public void equipBoots() {
        if(!bootsEquipped){
            speed += 2;
            bootsEquipped = true;
            gp.ui.showMessage("¡Botas equipadas!");
        } else {
            speed -= 2;
            bootsEquipped = false;
            gp.ui.showMessage("Botas desequipadas");
        }
    }

    @Override
    public void draw(Graphics2D g2){
        BufferedImage image = null;

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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}