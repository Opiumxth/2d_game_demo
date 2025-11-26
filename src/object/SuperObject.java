package object;

import interfaces.Drawable;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject implements Drawable, java.io.Serializable {
    private static final long serialVersionUID = 1L;
    public transient BufferedImage image;  // NO serializa la imagen
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    // ============================================
    // IMPLEMENTACIÓN DE draw() DE DRAWABLE
    // ============================================
    @Override
    public void draw(Graphics2D g2) {
        // Este metodo nunca se llama directamente porque usamos draw(g2, gp)
        // Pero lo implementamos para cumplir con la interfaz
    }

    // Metodo sobrecargado que usamos en el juego
    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize <  gp.player.worldY + gp.player.screenY){
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}