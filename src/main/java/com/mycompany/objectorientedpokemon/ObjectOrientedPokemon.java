/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.objectorientedpokemon;

import com.mycompany.objectorientedpokemon.map.MapPanel;
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
        
        MapPanel map = new MapPanel();
        main.add(map);
        main.pack();
        
//        System.out.println(main.getSize());
//        System.out.println(map.getSize());
        
        main.setVisible(true);
    }
}
