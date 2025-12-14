/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map.mapEntity;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.entity.Pokemon;
import com.mycompany.objectorientedpokemon.map.MapPanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class MapMonster extends MapEntity {
    private BufferedImage image1, image2;
    public Pokemon p;
    
    public MapMonster(MapPanel mp) {
        super(mp);
        p = new Pokemon();
        direction = "down";
        speed = 1;
        solidArea = new Rectangle(20, GameConstants.TILE_SIZE, 40, 40);
        
        getImage();
    }
    private void getImage() {
        image1 = setup("/player/smoke1");
        image2 = setup("/player/smoke2");
    }
    
    public void draw(Graphics2D g2) {
        int screenX = worldX - mp.player.worldX + mp.player.screenX;
        int screenY = worldY - mp.player.worldY + mp.player.screenY;
        
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

        if (swapSprite) {
            g2.drawImage(image1, screenX, screenY, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
        } else {
            g2.drawImage(image2, screenX, screenY, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
        }

        
        spriteCounter++;
        if (spriteCounter > 10) {
            swapSprite = !swapSprite;
            spriteCounter = 0;
        }
    }
}
