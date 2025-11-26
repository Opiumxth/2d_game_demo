package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_SpecialKey extends SuperObject {

    public OBJ_SpecialKey(){
        name = "SpecialKey";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/specialkey.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}