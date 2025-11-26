package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Entity {
    GamePanel gp;
    public BufferedImage down1;
    public Enemy(GamePanel gp){
        this.gp = gp;

        setDefaultValues();   // ← Aquí inicializamos posición y velocidad
        getEnemyImage();      // ← Aquí cargamos la imagen
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 10; // Posición donde aparece el enemigo
        worldY = gp.tileSize * 10;
        speed = 1;
        direction = "down";
    }
    public void getEnemyImage(){
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/enemy/enemy_down_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        g2.drawImage(image, worldX - gp.player.worldX + gp.player.screenX,
                worldY - gp.player.worldY + gp.player.screenY,
                gp.tileSize, gp.tileSize, null);
    }
}