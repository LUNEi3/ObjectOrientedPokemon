/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map.mapEntity;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.map.MapPanel;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Entity {
    protected MapPanel mp;
    protected BufferedImage right1, right2, left1, left2;
    public Rectangle solidArea = new Rectangle(0, 0, 40, 40);
    public String direction;
    public int worldX, worldY, speed;
    public int solidAreaDefaultX = solidArea.x, solidAreaDefaultY = solidArea.y;
    protected int spriteCounter;
    protected boolean swapSprite = false;
    public boolean collisionOn = false;
    
    public Entity(MapPanel mp) {
        this.mp = mp;
    }
    
    protected BufferedImage setup(String imagePath) {
        BufferedImage image = null;
         try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         
         return image;
    }
}
