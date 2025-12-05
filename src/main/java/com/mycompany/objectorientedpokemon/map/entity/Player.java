/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map.entity;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.map.MapPanel;
import com.mycompany.objectorientedpokemon.map.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Player extends Entity {
    private MapPanel mp;
    private KeyHandler keyH;
    
    public final int screenX, screenY;
    
    public Player(MapPanel mp, KeyHandler keyH) {
        this.mp = mp;
        this.keyH = keyH;
        
        screenX = GameConstants.SCREEN_WIDTH / 2 - (GameConstants.TILE_SIZE / 2);
        screenY = GameConstants.SCREEN_HEIGHT / 2 - (GameConstants.TILE_SIZE / 2);
        
        setDefaultValues();
        getPlayerImage();
    }
    
    private void setDefaultValues() {
        worldX = GameConstants.TILE_SIZE * 2;
        worldY = GameConstants.TILE_SIZE * 2;
        speed = 4;
        direction = "right";
    }
    
    private void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    public void update() {
        //System.out.println("player.update() is running...");
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;
            }

            spriteCounter ++;
            if (spriteCounter > 10) {
                swapSprite = !swapSprite;
                spriteCounter = 0;
            }
        }
    }
    
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        //System.out.println("player.draw() is running...");
        switch(direction) {
            case "up" -> {
                if (swapSprite) {
                    image = right2;
                } else {
                    image = left1;
                }
            }
                
            case "down" -> {
                if (swapSprite) {
                    image = right1;
                } else {
                    image = left2;
                }
            }
                
            case "left" -> {
                if (swapSprite) {
                    image = left1;
                } else {
                    image = left2;
                }
            }
                
            case "right" -> {
                if (swapSprite) {
                    image = right1;
                } else {
                    image = right2;
                }
            }   
        }
        
        g2.drawImage(image, screenX, screenY, (int) (GameConstants.TILE_SIZE), (int) (GameConstants.TILE_SIZE), null);
    }
}
