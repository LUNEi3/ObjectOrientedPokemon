/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map.mapEntity;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.entity.Pokemon;
import com.mycompany.objectorientedpokemon.map.MapPanel;
import com.mycompany.objectorientedpokemon.map.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class MapPlayer extends MapEntity {
    private MapPanel mp;
    private KeyHandler keyH;
    public int screenX, screenY;
    
    public MapPlayer(MapPanel mp, KeyHandler keyH) {
        super(mp);
        this.mp = mp;
        this.keyH = keyH;
        
        screenX = GameConstants.SCREEN_WIDTH / 2 - (GameConstants.TILE_SIZE / 2);
        screenY = GameConstants.SCREEN_HEIGHT / 2 - (GameConstants.TILE_SIZE / 2);
        
        solidArea = new Rectangle(20, GameConstants.TILE_SIZE / 2, 40, 40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        
        setDefaultValues();
        getPlayerImage();
    }
    
    private void setDefaultValues() {
        worldX = GameConstants.TILE_SIZE * 15;
        worldY = GameConstants.TILE_SIZE * 35;
        speed = 10;
        direction = "right";
    }
    
    private void getPlayerImage() {
        up1 = setup("/player/up1");
        up2 = setup("/player/up2");
        right1 = setup("/player/right1");
        right2 = setup("/player/right2");
        left1 = setup("/player/left1");
        left2 = setup("/player/left2");
    } 
    
    public void update() {
        //System.out.println("player.update() is running...");
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }
            
            

            // RESET COLLISION
            collisionOn = false;
            mp.cChecker.checkTileCollision(this);
            
            // Check Event
            mp.eHandler.checkEvent();

            // Check if MapPlayer encounter MapMonster
            int monsterIndex = mp.cChecker.checkMonsterCollision(this, mp.monster);
            encounterMonster(monsterIndex);

            
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> {
                        worldY -= speed;
                        break;
                    }
                    case "down" -> {
                        worldY += speed;
                        break;
                    }
                    case "left" -> {
                        worldX -= speed;
                        break;
                    }
                    case "right" -> {
                        worldX += speed;
                        break;
                    }
                }
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
                    image = up1;
                } else {
                    image = up2;
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
        
        // Change player position when encounter the edge of map
        int drawX = screenX;
        int drawY = screenY;
        
        if (worldX < screenX) {
            drawX = worldX;
        }
        if (worldY < screenY) {
            drawY = worldY;
        }
        
        int rightOffset = GameConstants.SCREEN_WIDTH - screenX;
        int bottomOffset = GameConstants.SCREEN_HEIGHT - screenY;

        if (worldX > GameConstants.WORLD_WIDTH - rightOffset) {
            drawX = GameConstants.SCREEN_WIDTH - (GameConstants.WORLD_WIDTH - worldX);
        }
        if (worldY > GameConstants.WORLD_HEIGHT - bottomOffset) {
            drawY = GameConstants.SCREEN_HEIGHT - (GameConstants.WORLD_HEIGHT - worldY);
        }
        
        g2.drawImage(image, drawX, drawY, (int) (GameConstants.TILE_SIZE), (int) (GameConstants.TILE_SIZE), null);
    }

    private void encounterMonster(int index) {
        if (index != 999) {
            mp.gameM.stopMusic();
            mp.gameM.playSE(3);
            
            String name = mp.monster[index].p.name;
            String type = mp.monster[index].p.type;
            String text = "You encouter Monster " + name + " " + "type: " + type;
            JOptionPane.showMessageDialog(null, text, "Title", JOptionPane.PLAIN_MESSAGE);
            mp.monster[index] = null;
            mp.keyH.releaseAll();
            
            mp.gameM.stopSE();
            mp.gameM.playMusic(1);
        }

    }
}
