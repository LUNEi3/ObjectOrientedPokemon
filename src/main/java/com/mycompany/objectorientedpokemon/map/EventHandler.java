/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map;

import com.mycompany.objectorientedpokemon.GameConstants;
import java.awt.Rectangle;

/**
 *
 * @author User
 */
public class EventHandler {
    private MapPanel mp;
    private Rectangle eventRect;
    private int eventRectDefaultX, eventRectDefaultY;
    
    public EventHandler(MapPanel mp) {
        this.mp = mp;
        
        eventRect = new Rectangle();
        eventRect.x = 23; 
        eventRect.y = 23;
        eventRect.width = GameConstants.TILE_SIZE * 4;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }
    
    public void checkEvent() {
        if (hit(14, 39, "any")) {
            teleport("/maps/map1.txt", 15, 2);
        }
        if (hit(14, 0, "any")) {
            teleport("/maps/worldMap1.txt", 15, 38);
        }
    }
    
    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;
        
        mp.player.solidArea.x = mp.player.worldX + mp.player.solidArea.x;
        mp.player.solidArea.y = mp.player.worldY + mp.player.solidArea.y;
        
        eventRect.x = col * GameConstants.TILE_SIZE + eventRect.x;
        eventRect.y = row * GameConstants.TILE_SIZE + eventRect.y;
        
        if (mp.player.solidArea.intersects(eventRect)) {
            if (mp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        
        mp.player.solidArea.x = mp.player.solidAreaDefaultX;
        mp.player.solidArea.y = mp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        
        return hit;
    }
    
    public void teleport(String mapPath, int col, int row) {
        // 1. Load the new map
        mp.tileM.loadMap(mapPath);
        System.out.println(mapPath);
        
        // 2. Move Player
        mp.player.worldX = GameConstants.TILE_SIZE * col;
        mp.player.worldY = GameConstants.TILE_SIZE * row;
        
        // 3. Reset Monsters (Optional: Clear old monsters, spawn new ones)
        // You might need to add a clearMonsters() method to AssetSetter
        for(int i = 0; i < mp.monster.length; i++) {
            mp.monster[i] = null;
        }
        mp.aSetter.setMonster(2); 
    }
}
