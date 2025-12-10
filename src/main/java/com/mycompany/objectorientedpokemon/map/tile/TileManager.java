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
        tiles = new Tile[30];
        mapTileNum = new int[GameConstants.MAX_WORLD_COL][GameConstants.MAX_WORLD_ROW];
        getTileImage();
        loadMap("/maps/worldMap2.txt");
    }
    
    private void getTileImage() {
//        setup(0, "grass", false);
//        setup(1, "wall", true);
//        setup(2, "water", true);
//        setup(3, "tree", true);
//        setup(4, "wooden", false);
            setup(0, "0", false);
            setup(1, "1", true);
            setup(2, "2", true);
            setup(3, "3", true);
            setup(4, "4", true);
            setup(5, "5", true);
            setup(6, "6", true);
            setup(7, "7", true);
            setup(8, "8", true);
            setup(9, "9", true);
            setup(10, "10", true);
            setup(11, "11", true);
            setup(12, "12", true);
            setup(13, "13", true);
            setup(14, "14", true);
            setup(15, "15", true);
            setup(16, "16", true);
            setup(17, "17", true);
            setup(18, "18", true);
            setup(19, "19", true);
            setup(20, "20", true);
            setup(21, "21", true);
            setup(22, "22", true);
            setup(23, "23", true);
            setup(24, "24", true);
            setup(25, "25", true);
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
