/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map.tile;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.map.MapPanel;
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
    private Tile[] tiles;
    private int mapTileNum[][];
    
    public TileManager(MapPanel mp) {
        this.mp = mp;
        tiles = new Tile[10];
        mapTileNum = new int[GameConstants.MAX_WORLD_COL][GameConstants.MAX_WORLD_ROW];
        getTileImage();
        loadMap("/maps/worldMap1.txt");
    }
    
    private void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.jpeg"));
            
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wooden.png"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadMap(String filePath) {
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
            
            g2.drawImage(tiles[tileNum].image, screenX, screenY, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
            worldCol ++;
            
            if(worldCol >= GameConstants.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow ++;
            }
        }
    }
}
