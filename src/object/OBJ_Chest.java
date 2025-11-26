package object;

import entity.Player;
import interfaces.Interactive;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject implements Interactive {
    public boolean opened = false;

    public OBJ_Chest(){
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

    public void open(){
        opened = true;
        collision = false;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/openchest.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public boolean isOpened() {
        return opened;
    }

    // ============================================
    // IMPLEMENTACIÓN DE INTERACTIVE
    // ============================================
    @Override
    public void interact(Player player) {
        if(opened) {
            System.out.println("El cofre ya está abierto.");
            return;
        }

        if(player.specialKey > 0) {
            open();
            player.specialKey--;
            System.out.println("¡Cofre abierto con la llave especial! ¡Has ganado!");
        } else {
            System.out.println("Necesitas una llave especial para abrir este cofre.");
        }
    }

    @Override
    public boolean isInteractable() {
        return !opened;  // Solo es interactuable si NO está abierto
    }
}