package object;

import interfaces.Collectable;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_SpecialKey extends SuperObject implements Collectable {

    public OBJ_SpecialKey(){
        name = "SpecialKey";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/specialkey.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // ============================================
    // IMPLEMENTACIÓN DE COLLECTABLE
    // ============================================
    @Override
    public void collect() {
        System.out.println("¡Llave especial recogida! Ahora puedes abrir el cofre.");
    }

    @Override
    public boolean canBeCollected() {
        return true;  // Siempre se puede recoger
    }
}