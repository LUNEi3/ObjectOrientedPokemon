/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.objectorientedpokemon;

import com.mycompany.objectorientedpokemon.map.GamePanel;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author pariwat.nira
 */
public class ObjectOrientedPokemon {
    private static Dimension preferredSize = new Dimension(1280, 720);

    public static void main(String[] args) {
        JFrame main = new JFrame();
        main.setResizable(false);
        // main.setPreferredSize(preferredSize);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        
        GamePanel map = new GamePanel();
        main.add(map);
        main.pack();
        
        main.setVisible(true);
    }
}
