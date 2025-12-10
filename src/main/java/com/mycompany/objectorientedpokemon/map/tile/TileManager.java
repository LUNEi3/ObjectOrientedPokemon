/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map.tile;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.map.MapPanel;
import com.mycompany.objectorientedpokemon.map.Util;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class TileManager {
    
    private MapPanel mp;
    public Tile[] tiles;
    public int mapTileNum[][];
    
    public TileManager(MapPanel mp) {
        this.mp = mp;
        tiles = new Tile[200];
        mapTileNum = new int[GameConstants.MAX_WORLD_COL][GameConstants.MAX_WORLD_ROW];
        getTileImage();
        loadMap("/maps/MapMain.txt");
    }
    
    private void getTileImage() {
        // Loop through all possible numbers (0 to 99)
        for (int i = 0; i < tiles.length; i++) {
            String fileName = "/tiles/" + i + ".png";

            // CHECK: Does this file actually exist?
            // getResourceAsStream returns 'null' if the file is missing.
            if (getClass().getResourceAsStream(fileName) != null) {

                // It exists! Load it.
                setup(i, String.valueOf(i), true);

            } else {
                // File doesn't exist (like 3.png). Skip it.
                // tiles[i] will remain 'null'.
            }
        }

        // MANUAL OVERRIDES (Set collision to false for walkable blocks)
        // You must check if they exist first to avoid NullPointerExceptions
        if (tiles[70] != null) tiles[70].collision = false; // Grass
        if (tiles[80] != null) tiles[80].collision = false; // Sand/Path?
        // ... etc
    }
    
    private void setup(int index, String path, boolean collision) {
        Util util = new Util();
        
        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + path + ".png"));
            
            // Optimize Image scale
            // tiles[index].image = util.scaleImage(tiles[index].image, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
            
            tiles[index].collision = collision;
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath) {
        try {
            InputStream input = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            int col = 0, row = 0;
            while (col < GameConstants.MAX_WORLD_COL && row < GameConstants.MAX_WORLD_ROW) {
                String line = reader.readLine();
                
                while (col < GameConstants.MAX_WORLD_COL) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == GameConstants.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2) {
        int worldCol = 0, worldRow = 0;
      
        while(worldCol < GameConstants.MAX_WORLD_COL && worldRow < GameConstants.MAX_WORLD_ROW) {
            
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * GameConstants.TILE_SIZE;
            int worldY = worldRow * GameConstants.TILE_SIZE;
            int screenX = worldX - mp.player.worldX + mp.player.screenX;
            int screenY = worldY - mp.player.worldY + mp.player.screenY;
            
            
            // Render only things on map
            if (mp.player.worldX < mp.player.screenX) {
                screenX = worldX;
            }
            if (mp.player.worldY < mp.player.screenY) {
                screenY = worldY;
            }

            int rightOffset = GameConstants.SCREEN_WIDTH - mp.player.screenX;
            int bottomOffset = GameConstants.SCREEN_HEIGHT - mp.player.screenY;

            if (mp.player.worldX > GameConstants.WORLD_WIDTH - rightOffset) {
                screenX = GameConstants.SCREEN_WIDTH - (GameConstants.WORLD_WIDTH - worldX);
            }
            if (mp.player.worldY > GameConstants.WORLD_HEIGHT - bottomOffset) {
                screenY = GameConstants.SCREEN_HEIGHT - (GameConstants.WORLD_HEIGHT - worldY);
            }
            
            
            // Render if tile on the player screen
            if (screenX + GameConstants.TILE_SIZE > 0 &&
            screenX < GameConstants.SCREEN_WIDTH &&
            screenY + GameConstants.TILE_SIZE > 0 &&
            screenY < GameConstants.SCREEN_HEIGHT) {

            g2.drawImage(tiles[tileNum].image, screenX, screenY, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
        }
            worldCol ++;
            
            if(worldCol >= GameConstants.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow ++;
            }
        }
    }
}
