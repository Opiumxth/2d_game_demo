package util;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    // ============================================
    // DATOS DEL JUGADOR
    // ============================================
    public int playerWorldX;
    public int playerWorldY;
    public int playerSpeed;
    public String playerDirection;
    public int hasKey;
    public int specialKey;
    public boolean bootsEquipped;

    // ============================================
    // INVENTARIO
    // ============================================
    public ArrayList<String> inventoryItems;  // Nombres de los objetos

    // ============================================
    // ESTADO DE LOS OBJETOS DEL MUNDO
    // ============================================
    public boolean[] objectsExist;  // true = el objeto aún existe en el mundo

    // ============================================
    // ESTADO DEL COFRE
    // ============================================
    public boolean chestOpened;

    // Constructor
    public GameState() {
        inventoryItems = new ArrayList<>();
    }
}