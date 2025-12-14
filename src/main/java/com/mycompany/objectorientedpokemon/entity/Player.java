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
    private ArrayList<Pokemon> pokemons;
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
