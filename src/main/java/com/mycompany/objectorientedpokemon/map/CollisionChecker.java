/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.map.entity.Entity;

/**
 *
 * @author User
 */
public class CollisionChecker {
    private MapPanel mp;
    
    public CollisionChecker(MapPanel mp) {
        this.mp = mp;
        
    }
    
    public void CheckTile(Entity en) {
        int enLeftX = en.worldX + en.solidArea.x;
        int enRightX = en.worldX + en.solidArea.x + en.solidArea.width;
        int enTopY = en.worldY + en.solidArea.y;
        int enBottomY = en.worldY + en.solidArea.y + en.solidArea.height;
        
        int enLeftCol = enLeftX / GameConstants.TILE_SIZE;
        int enRightCol = enRightX / GameConstants.TILE_SIZE;
        int enTopRow = enTopY / GameConstants.TILE_SIZE;
        int enBottomRow = enBottomY / GameConstants.TILE_SIZE;
        
        int tile1, tile2;
        
        switch (en.direction) {
            case "up" -> {
                enTopRow = (enTopY - en.speed) / GameConstants.TILE_SIZE;
                tile1 = mp.tileM.mapTileNum[enLeftCol][enTopRow];
                tile2 = mp.tileM.mapTileNum[enRightCol][enTopRow];
                if (mp.tileM.tiles[tile1].collision || mp.tileM.tiles[tile2].collision) {
                    en.collisionOn = true;
                }
            }
            case "down" -> {
                enBottomRow = (enBottomY - en.speed) / GameConstants.TILE_SIZE;
                tile1 = mp.tileM.mapTileNum[enLeftCol][enBottomRow];
                tile2 = mp.tileM.mapTileNum[enRightCol][enBottomRow];
                if (mp.tileM.tiles[tile1].collision || mp.tileM.tiles[tile2].collision) {
                    en.collisionOn = true;
                }
            }
            case "left" -> {
                enLeftCol = (enLeftX - en.speed) / GameConstants.TILE_SIZE;
                tile1 = mp.tileM.mapTileNum[enLeftCol][enTopRow];
                tile2 = mp.tileM.mapTileNum[enLeftCol][enBottomRow];
                if (mp.tileM.tiles[tile1].collision || mp.tileM.tiles[tile2].collision) {
                    en.collisionOn = true;
                }
            }
            case "right" -> {
                enRightCol = (enRightX - en.speed) / GameConstants.TILE_SIZE;
                tile1 = mp.tileM.mapTileNum[enRightCol][enTopRow];
                tile2 = mp.tileM.mapTileNum[enRightCol][enBottomRow];
                if (mp.tileM.tiles[tile1].collision || mp.tileM.tiles[tile2].collision) {
                    en.collisionOn = true;
                }
            }
        }
    }
}
