/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class Util {
    public BufferedImage scaleImage(BufferedImage origin, int width, int height) {
        BufferedImage scaleImage = new BufferedImage(width, height, origin.getType());
        Graphics2D g2 = scaleImage.createGraphics();
        g2.drawImage(origin, 0, 0, width, height, null);
        g2.dispose();
        
        return scaleImage;
    }
}
