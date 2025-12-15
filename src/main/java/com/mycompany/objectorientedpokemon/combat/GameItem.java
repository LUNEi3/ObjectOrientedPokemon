package com.mycompany.objectorientedpokemon.combat;

public class GameItem {
    public String name;     
    public int healAmount;  
    public int quantity;     

    public GameItem(String name, int healAmount, int quantity) {
        this.name = name;
        this.healAmount = healAmount;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " (Heal " + healAmount + ") - Piece: " + quantity;
    }
}