/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map.entity;

import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class Entity {
    public int worldX, worldY, speed;
    
    protected BufferedImage right1, right2, left1, left2;
    protected String direction;
    protected int spriteCounter;
    protected boolean swapSprite = false;
}
