package com.mycompany.objectorientedpokemon.combat;

public class Skill {
    public String name;
    public int damage;
    public String type; // เก็บธาตุ (Fire, Water, etc.)

    public Skill(String name, int damage, String type) {
        this.name = name;
        this.damage = damage;
        this.type = type;
    }
}