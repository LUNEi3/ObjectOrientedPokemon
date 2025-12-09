/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.map.entity.Entity;
import java.awt.Rectangle;

/**
 *
 * @author User
 */
public class CollisionChecker {
    private MapPanel mp;
    
    public CollisionChecker(MapPanel mp) {
        this.mp = mp;
    }
    
    public void checkTileCollision(Entity en) {
        int enLeftX   = en.worldX + en.solidArea.x;
        int enRightX  = en.worldX + en.solidArea.x + en.solidArea.width;
        int enTopY    = en.worldY + en.solidArea.y;
        int enBottomY = en.worldY + en.solidArea.y + en.solidArea.height;

        int enLeftCol   = enLeftX / GameConstants.TILE_SIZE;
        int enRightCol  = enRightX / GameConstants.TILE_SIZE;
        int enTopRow    = enTopY / GameConstants.TILE_SIZE;
        int enBottomRow = enBottomY / GameConstants.TILE_SIZE;

        switch (en.direction) {
            case "up" -> {
                int futureRow = (enTopY - en.speed) / GameConstants.TILE_SIZE;
                if (isTileSolid(enLeftCol, futureRow) || isTileSolid(enRightCol, futureRow)) {
                    en.collisionOn = true;
                }
            }
            case "down" -> {
                int futureRow = (enBottomY + en.speed) / GameConstants.TILE_SIZE;
                if (isTileSolid(enLeftCol, futureRow) || isTileSolid(enRightCol, futureRow)) {
                    en.collisionOn = true;
                }
            }
            case "left" -> {
                int futureCol = (enLeftX - en.speed) / GameConstants.TILE_SIZE;
                if (isTileSolid(futureCol, enTopRow) || isTileSolid(futureCol, enBottomRow)) {
                    en.collisionOn = true;
                }
            }
            case "right" -> {
                int futureCol = (enRightX + en.speed) / GameConstants.TILE_SIZE;
                if (isTileSolid(futureCol, enTopRow) || isTileSolid(futureCol, enBottomRow)) {
                    en.collisionOn = true;
                }
            }
        }
    }

    private boolean isTileSolid(int col, int row) {
        // Check: Is the coordinate outside the map
        if (col < 0 || col >= GameConstants.MAX_WORLD_COL || 
            row < 0 || row >= GameConstants.MAX_WORLD_ROW) {
            return true;
        }
        int tileNum = mp.tileM.mapTileNum[col][row];

        return mp.tileM.tiles[tileNum].collision;
    }
    
    public int checkMonsterCollision(Entity player, Entity targets[]) {
        int index = 999 ;
        
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] != null) {
                
                player.solidArea.x = player.worldX + player.solidArea.x;
                player.solidArea.y = player.worldY + player.solidArea.y;

                targets[i].solidArea.x = targets[i].worldX + targets[i].solidArea.x;
                targets[i].solidArea.y = targets[i].worldY + targets[i].solidArea.y;
                
                if(player.solidArea.intersects(targets[i].solidArea)) {
                    player.collisionOn = true;
                    index = i;
                }
                
                player.solidArea.x = player.solidAreaDefaultX;
                player.solidArea.y = player.solidAreaDefaultY;

                targets[i].solidArea.x = targets[i].solidAreaDefaultX;
                targets[i].solidArea.y = targets[i].solidAreaDefaultY;
                
            }
        }
        
        return index;
    }
}
