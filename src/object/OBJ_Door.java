package object;

import entity.Player;
import interfaces.Interactive;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject implements Interactive {

    public OBJ_Door(){
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

    // ============================================
    // IMPLEMENTACIÓN DE INTERACTIVE
    // ============================================
    @Override
    public void interact(Player player) {
        if(player.hasKey > 0) {
            player.hasKey--;
            System.out.println("¡Puerta abierta con una llave! Llaves restantes: " + player.hasKey);
            // La puerta se elimina del juego (se maneja en pickUpObject)
        } else {
            System.out.println("Necesitas una llave para abrir esta puerta.");
        }
    }

    @Override
    public boolean isInteractable() {
        return true;  // La puerta siempre está disponible para interactuar
    }
}