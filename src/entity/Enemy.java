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
        setDefaultValues();
        getEnemyImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 10;
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

    // ============================================
    // IMPLEMENTACIÓN DE move() DE MOVABLE
    // ============================================
    @Override
    public void move() {
        // Lógica simple de movimiento para el enemigo
        // Por ahora el enemigo no se mueve, pero aquí puedes agregar IA
        // Ejemplo: movimiento aleatorio o perseguir al jugador

        /* Ejemplo de movimiento básico:
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if(!collisionOn) {
            switch(direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
        */
    }

    @Override
    public void update() {
        // Aquí puedes agregar lógica de IA
        // Por ejemplo: cambiar dirección aleatoriamente
        // move(); // Descomentar cuando quieras que el enemigo se mueva
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