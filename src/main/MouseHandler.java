package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import entity.Entity;
import object.SuperObject;   // ← IMPORTANTE

public class MouseHandler implements MouseListener {

    GamePanel gp;

    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        // ============================
        // 1. CLIC EN ICONO MOCHILA
        // ============================
        int size = gp.tileSize;
        int bx = gp.screenWidth - size - 20;
        int by = 20;

        if(mouseX >= bx && mouseX <= bx + size &&
                mouseY >= by && mouseY <= by + size) {

            gp.keyH.inventoryPressed = !gp.keyH.inventoryPressed;
            return;
        }

        // ============================
        // 2. CLIC EN INVENTARIO
        // ============================
        if (gp.keyH.inventoryPressed) {

            int boxW = gp.tileSize * 6;
            int boxH = gp.tileSize * 6;

            int invX = (gp.screenWidth / 2) - (boxW / 2);
            int invY = (gp.screenHeight / 2) - (boxH / 2);

            // Posición del ícono de las botas dentro del inventario
            int bootsX = invX + gp.tileSize;
            int bootsY = invY + gp.tileSize * 2;

            int iconSize = gp.tileSize;

            // Detectar clic en el ícono de botas
            if(mouseX >= bootsX && mouseX <= bootsX + iconSize &&
                    mouseY >= bootsY && mouseY <= bootsY + iconSize) {

                // BUSCAR BOTAS EN EL INVENTARIO
                for(int i = 0; i < gp.player.inventory.size(); i++) {

                    SuperObject item = gp.player.inventory.getItems().get(i);

                    if(item != null && item.name.equals("Boots")) {
                        gp.player.equipBoots();
                        return;
                    }
                }

                gp.ui.showMessage("No tienes botas en el inventario.");
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}