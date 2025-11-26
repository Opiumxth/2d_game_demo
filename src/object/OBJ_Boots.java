package object;

import interfaces.Collectable;
import interfaces.Usable;

import javax.imageio.ImageIO;
import java.io.IOException;
import main.GamePanel;

public class OBJ_Boots extends SuperObject implements Collectable, Usable {

    public OBJ_Boots(){
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // ============================================
    // IMPLEMENTACIÓN DE COLLECTABLE
    // ============================================
    @Override
    public void collect() {
        System.out.println("¡Botas recogidas y añadidas al inventario!");
    }

    @Override
    public boolean canBeCollected() {
        return true;  // Siempre se pueden recoger
    }

    // ============================================
    // IMPLEMENTACIÓN DE USABLE
    // ============================================
    @Override
    public void use(GamePanel gp) {
        gp.player.equipBoots();
    }

    @Override
    public String getUsageDescription() {
        return "Aumenta la velocidad del jugador en 2 puntos";
    }
}