package object;

import interfaces.Collectable;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject implements Collectable {

    public OBJ_Key(){
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // ============================================
    // IMPLEMENTACIÓN DE COLLECTABLE
    // ============================================
    @Override
    public void collect() {
        System.out.println("¡Llave normal recogida!");
    }

    @Override
    public boolean canBeCollected() {
        return true;  // Siempre se puede recoger
    }
}