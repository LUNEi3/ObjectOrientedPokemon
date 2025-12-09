/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.map.mapEntity.Monster;
import java.util.Random;

/**
 *
 * @author User
 */
public class AssetSetter {
    private MapPanel mp;
    private Random random = new Random();
    
    public AssetSetter(MapPanel mp) {
        this.mp = mp;
    }
    
    public void setMonster(int numMonster) {
        for (int i = 0; i < numMonster; i++) {
            mp.monster[i] = new Monster(mp);
            int[] coords = getXYMonsterSpawn();
            
            if (coords != null) {
                mp.monster[i].worldX = coords[0];
                mp.monster[i].worldY = coords[1];
            } else {
                System.out.println("Could not find spawn spot for Monster " + i);
                mp.monster[i] = null;
            }
        }
    }
    
    private int[] getXYMonsterSpawn() {
        int attempt = 0;
        int maxAttempts = 100;
        int minDistance = 5;
        
        while (attempt < maxAttempts) {
            int col = random.nextInt(GameConstants.MAX_WORLD_COL);
            int row = random.nextInt(GameConstants.MAX_WORLD_ROW);
            
            if (mp.tileM.mapTileNum[col][row] == 0) {
                boolean tooClose = false;
                
                for (Monster m: mp.monster) {
                    if (m != null) {
                        int mCol = m.worldX / GameConstants.TILE_SIZE;
                        int mRow = m.worldY / GameConstants.TILE_SIZE;
                        
                        int distance = Math.abs(col - mCol) + Math.abs(row - mRow);
                        
                        if (distance < minDistance) {
                            tooClose = true;
                            break;
                        }
                    }
                }
                if (!tooClose) {
                    int[] coords = new int[2];
                    coords[0] = col * GameConstants.TILE_SIZE;
                    coords[1] = row * GameConstants.TILE_SIZE;
                    return coords;
                }
            }
        }
        
        return null;
    }
}
