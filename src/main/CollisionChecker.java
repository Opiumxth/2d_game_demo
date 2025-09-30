package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    // Checki f the entity will collide with a tile
    public void checkTile(Entity entity){
        // Hitbox of the entity in world coords
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // Converts the world coords (pixels) into tile grid coords, so it's easier to determine which tiles the entity's edges are on
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        // Tiles we'll check for collision
        int tileNum1, tileNum2;

        // Checking directions for collision
        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize; // This predicts the entity's top row after moving up
                // Getting the two tiles above the entity, in this case left and right corners
                tileNum1 = gp.tileM.mapTileNum [entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum [entityRightCol][entityTopRow];
                // If any of these corners are solid (collision == true) then it turns collision on
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            // In the next three cases occurs almost the same thing and logic
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum [entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum [entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum [entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum [entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum [entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum [entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}