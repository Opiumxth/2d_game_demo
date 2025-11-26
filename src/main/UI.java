package main;

import java.awt.BasicStroke;
import object.OBJ_Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_SpecialKey;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class UI {
    GamePanel gp; // Reference to the main game panel
    Font arial_40; // Font for the ui text
    BufferedImage keyImage; // Key icon image
    BufferedImage specialKeyImage;
    BufferedImage backpackIcon;
    String message = "";
    boolean messageOn = false;
    public int messageCounter = 0;

    // Constructor, this initializes the ui elements
    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/res/font/pressstart2p.ttf");
            arial_40 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);
        } catch (FontFormatException | IOException e) {
            arial_40 = new Font("Arial", Font.BOLD, 40);
        }
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
        OBJ_SpecialKey specialkey = new OBJ_SpecialKey();
        specialKeyImage = specialkey.image;
        try {
            backpackIcon = ImageIO.read(
                    getClass().getResourceAsStream("/objects/backpack.png")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Draws ui elements on screen, used in GamePanel class
    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 60, 60);
        g2.drawImage(specialKeyImage, gp.tileSize/2, gp.tileSize + 22, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.specialKey, 60, gp.tileSize + 63);
        int size = gp.tileSize;
        g2.drawImage(backpackIcon, gp.screenWidth - size - 20, 20, size, size, null);
        if (gp.keyH.inventoryPressed) {
            drawInventoryMenu(g2);
        }
        if (messageOn) {
            int textLength = (int) g2.getFontMetrics().getStringBounds(message, g2).getWidth();
            int x = gp.screenWidth / 2 - textLength / 2;
            int y = gp.screenHeight / 2;
            g2.setColor(Color.YELLOW);
            g2.setFont(arial_40);
            g2.drawString(message, x, y);
            messageCounter++;
            if (messageCounter > 120) { // 2 segundos
                messageOn = false;
                messageCounter = 0;
            }
        }
    }

    public void drawInventoryMenu(Graphics2D g2) {
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 5;

        int x = (gp.screenWidth - width) / 2;
        int y = (gp.screenHeight - height) / 2;

        // Fondo oscuro semi-transparente
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(x, y, width, height, 20, 20);

        // Marco blanco
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 20, 20);

        // Título
        g2.setFont(g2.getFont().deriveFont(32f));
        g2.drawString("Inventario", x + 20, y + 40);

        // Dibujar los ítems
        int itemX = x + 25;
        int itemY = y + 80;
        int iconSize = 24; // tamaño pequeño

        g2.setFont(g2.getFont().deriveFont(18f));

        for (Object obj : gp.player.inventory.getItems()) {
            if (obj instanceof object.SuperObject) {

                object.SuperObject item = (object.SuperObject) obj;

                // Dibujar icono
                g2.drawImage(
                        item.image,
                        itemX,
                        itemY - iconSize + 12,   // ajuste visual
                        iconSize,
                        iconSize,
                        null
                );

                // Dibujar nombre
                g2.drawString(item.name, itemX + 35, itemY);

                itemY += 35; // espacio entre filas
            }

        }
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
        messageCounter = 0; // reiniciar contador
    }
}