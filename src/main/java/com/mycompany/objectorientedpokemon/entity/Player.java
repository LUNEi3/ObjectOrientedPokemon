/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.entity;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Player {
    private String name = "Guest";
    public ArrayList<Pokemon> myParty;
    
    public Player() {
        myParty = new ArrayList<>();
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Pokemon getPokemon() {
        return this.myParty.get(0);
    }
    public void addPokemon(Pokemon p) {
        myParty.add(p);
    }
}
