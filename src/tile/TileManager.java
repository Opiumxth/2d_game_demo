package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp; // Reference to the main game panel
    public Tile[] tile; // Array of different tiles
    public int mapTileNum[][]; // This is a 2d array storing which tile goes at each map position

    // THis constructor receives the game panel where tiles will be managed
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10]; // This means it's up to 10 different types of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // THis is the whole world map
        getTileImage(); // Loafs the tile image
        loadMap("/maps/world01.txt"); // Loads the map layout from a text file
    }

    // Loads the image for each type of tile
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    // Load the map layout from a text file and store it in mapTileNum
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            // Go through each row and column of the map file
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine(); // Read one line of the map file
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" "); // Split line into numbers
                    int num = Integer.parseInt(numbers[col]); // Convert string to int
                    mapTileNum[col][row] = num; // Store tile number in the map array
                    col++;
                }

                // If we reach the end of the row, reset column and move to next row
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Draw the tiles on the screen (only the ones visible to the player)
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        // Loop through the entire world map
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize <  gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;
            // If we reach the end of a row, reset col and move to next row
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}