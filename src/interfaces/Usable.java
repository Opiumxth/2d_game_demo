package interfaces;

import main.GamePanel;

public interface Usable {
    void use(GamePanel gp);
    String getUsageDescription();
}