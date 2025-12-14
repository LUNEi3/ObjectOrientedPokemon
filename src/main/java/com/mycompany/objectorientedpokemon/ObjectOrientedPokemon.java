/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.objectorientedpokemon;

import com.mycompany.objectorientedpokemon.entity.Pokemon;
import com.mycompany.objectorientedpokemon.map.MapPanel;
import com.mycompany.objectorientedpokemon.menu.*;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author pariwat.nira
 */
public class ObjectOrientedPokemon {

    public static void main(String[] args) {
        // SYSTEM
        GameManager manager = new GameManager();
        MainFrame main = new MainFrame();
        
        // SETUP
        main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main.setTitle("Obeject-Oriented-Pokemon (Working...)");
  
        // HANDLE
        main.add(manager.getPanel());
        main.pack();
        main.setVisible(true);

//        Pokemon p = new Pokemon("electric");
//        System.out.println(p.name + " " +p.type);
//        System.out.println("ATK:" + p.atk + " HP:" + p.maxHp + " DEF:" + p.def);
//        p.evolution();
//        System.out.println(p.form);
//        System.out.println(p.name + " " +p.type);
//        System.out.println("ATK:" + p.atk + " HP:" + p.maxHp + " DEF:" + p.def);
//        p.evolution();
//        System.out.println(p.form);
//        System.out.println(p.name + " " +p.type);
//        System.out.println("ATK:" + p.atk + " HP:" + p.maxHp + " DEF:" + p.def);
    }
}
