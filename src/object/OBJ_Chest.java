package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
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
}