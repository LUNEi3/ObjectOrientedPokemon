/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map;

import com.mycompany.objectorientedpokemon.GameConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author User
 */
public class UiMap {
    private MapPanel mp;
    private Graphics2D g2;
    private Font arial_40, arial_80B;
    
    public UiMap(MapPanel mp) {
        this.mp = mp;
        
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }
    
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        
        if (mp.gameStateOn) {
            ///
        } 
        if (!mp.gameStateOn) {
            drawPauseScreen();
        }
        
        drawPlayerName();
    }
    
    private void drawPlayerName() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        g2.drawString(mp.gameM.player.getName(), 15, 80);
    }

    private void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = GameConstants.SCREEN_HEIGHT / 2;
        
        g2.drawString(text, x, y);
    }
    
    private int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return GameConstants.SCREEN_WIDTH / 2 - length / 2;
    }
}
