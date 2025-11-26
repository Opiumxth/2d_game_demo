package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import main.GamePanel;

public class OBJ_Boots extends SuperObject {

    public OBJ_Boots(){
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    // Método para usar las botas
    public void use(GamePanel gp) {
        gp.player.speed += 2; // aplica efecto
        gp.ui.showMessage("Velocidad aumentada!");
    }
}