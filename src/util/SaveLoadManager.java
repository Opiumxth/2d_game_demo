package util;

import main.GamePanel;
import object.SuperObject;
import object.OBJ_Chest;
import object.OBJ_Key;
import object.OBJ_SpecialKey;
import object.OBJ_Boots;
import object.OBJ_Door;

import java.io.*;

public class SaveLoadManager {

    private static final String SAVE_FILE = "savegame.dat";

    // ============================================
    // GUARDAR PARTIDA
    // ============================================
    public static void saveGame(GamePanel gp) throws IOException {
        GameState state = new GameState();

        // Guardar datos del jugador
        state.playerWorldX = gp.player.worldX;
        state.playerWorldY = gp.player.worldY;
        state.playerSpeed = gp.player.speed;
        state.playerDirection = gp.player.direction;
        state.hasKey = gp.player.hasKey;
        state.specialKey = gp.player.specialKey;
        state.bootsEquipped = gp.player.bootsEquipped;

        // Guardar inventario (solo nombres)
        for(SuperObject item : gp.player.inventory.getItems()) {
            if(item != null) {
                state.inventoryItems.add(item.name);
            }
        }

        // Guardar estado de objetos del mundo
        state.objectsExist = new boolean[gp.obj.length];
        for(int i = 0; i < gp.obj.length; i++) {
            state.objectsExist[i] = (gp.obj[i] != null);
        }

        // Guardar estado del cofre
        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] instanceof OBJ_Chest) {
                state.chestOpened = ((OBJ_Chest)gp.obj[i]).isOpened();
                break;
            }
        }

        // Escribir al archivo usando ObjectOutputStream
        try(ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(state);
            System.out.println("[SISTEMA] Partida guardada en: " + SAVE_FILE);
        }
    }

    // ============================================
    // CARGAR PARTIDA
    // ============================================
    public static void loadGame(GamePanel gp) throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(SAVE_FILE))) {

            GameState state = (GameState) ois.readObject();

            // Restaurar datos del jugador
            gp.player.worldX = state.playerWorldX;
            gp.player.worldY = state.playerWorldY;
            gp.player.speed = state.playerSpeed;
            gp.player.direction = state.playerDirection;
            gp.player.hasKey = state.hasKey;
            gp.player.specialKey = state.specialKey;
            gp.player.bootsEquipped = state.bootsEquipped;

            // Restaurar inventario
            gp.player.inventory.getItems().clear();
            for(String itemName : state.inventoryItems) {
                SuperObject item = createObjectByName(itemName);
                if(item != null) {
                    gp.player.inventory.add(item);
                }
            }

            // Restaurar objetos del mundo
            gp.aSetter.setObject();  // Primero recrear todos
            for(int i = 0; i < gp.obj.length; i++) {
                if(!state.objectsExist[i]) {
                    gp.obj[i] = null;  // Eliminar los que ya fueron recogidos
                }
            }

            // Restaurar estado del cofre
            if(state.chestOpened) {
                for(int i = 0; i < gp.obj.length; i++) {
                    if(gp.obj[i] instanceof OBJ_Chest) {
                        ((OBJ_Chest)gp.obj[i]).open();
                        break;
                    }
                }
            }

            System.out.println("[SISTEMA] Partida cargada desde: " + SAVE_FILE);
        }
    }

    // ============================================
    // RECREAR OBJETOS POR NOMBRE
    // ============================================
    private static SuperObject createObjectByName(String name) {
        switch(name) {
            case "Key":
                return new OBJ_Key();
            case "SpecialKey":
                return new OBJ_SpecialKey();
            case "Boots":
                return new OBJ_Boots();
            case "Door":
                return new OBJ_Door();
            case "Chest":
                return new OBJ_Chest();
            default:
                return null;
        }
    }

    // ============================================
    // VERIFICAR SI EXISTE GUARDADO
    // ============================================
    public static boolean saveFileExists() {
        File file = new File(SAVE_FILE);
        return file.exists();
    }
}