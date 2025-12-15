package com.mycompany.objectorientedpokemon.combat;

import java.util.ArrayList;
import java.util.List;

public class SkillDatabase {

    // --- FIRE TYPE ---
    public static List<Skill> getFireSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Ember", 20, "Fire"));
        skills.add(new Skill("Flame Wheel", 25, "Fire"));
        skills.add(new Skill("Heat Wave", 30, "Fire"));
        skills.add(new Skill("Fire Spin", 15, "Fire"));
        skills.add(new Skill("Flame Charge", 30, "Fire"));
        return skills;
    }
    public static List<Skill> getFireUltimates() {
        List<Skill> ults = new ArrayList<>();
        ults.add(new Skill("Fire Blast", 85, "Fire"));
        ults.add(new Skill("Blast Burn", 100, "Fire"));
        return ults;
    }

    // --- WATER TYPE ---
    public static List<Skill> getWaterSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Water Gun", 20, "Water"));
        skills.add(new Skill("Bubble Beam", 35, "Water"));
        skills.add(new Skill("Aqua Jet", 25, "Water"));
        skills.add(new Skill("Water Pulse", 40, "Water"));
        skills.add(new Skill("Chilling Water", 30, "Water"));
        return skills;
    }
    public static List<Skill> getWaterUltimates() {
        List<Skill> ults = new ArrayList<>();
        ults.add(new Skill("Hydro Pump", 85, "Water"));
        ults.add(new Skill("Origin Pulse", 90, "Water"));
        return ults;
    }

    // --- LEAF TYPE ---
    public static List<Skill> getLeafSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Razor Leaf", 35, "Leaf"));
        skills.add(new Skill("Vine Whip", 25, "Leaf"));
        skills.add(new Skill("Mega Drain", 25, "Leaf"));
        skills.add(new Skill("Needle Arm", 40, "Leaf"));
        skills.add(new Skill("Leaf Tornado", 45, "Leaf"));
        return skills;
    }
    public static List<Skill> getLeafUltimates() {
        List<Skill> ults = new ArrayList<>();
        ults.add(new Skill("Solar Beam", 90, "Leaf"));
        ults.add(new Skill("Leaf Storm", 95, "Leaf"));
        return ults;
    }

    // --- ROCK TYPE ---
    public static List<Skill> getRockSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Mud Shot", 35, "Rock"));
        skills.add(new Skill("Mud Slap", 10, "Rock"));
        skills.add(new Skill("Bulldoze", 40, "Rock"));
        skills.add(new Skill("Dig", 20, "Rock"));
        skills.add(new Skill("Mud Bomb", 45, "Rock"));
        return skills;
    }
    public static List<Skill> getRockUltimates() {
        List<Skill> ults = new ArrayList<>();
        ults.add(new Skill("Earthquake", 80, "Rock"));
        ults.add(new Skill("Precipice Blades", 95, "Rock"));
        return ults;
    }

    // --- ELECTRIC TYPE ---
    public static List<Skill> getElectricSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Thundershock", 20, "Electric"));
        skills.add(new Skill("Spark", 45, "Electric"));
        skills.add(new Skill("Volt Switch", 50, "Electric"));
        skills.add(new Skill("Electro Ball", 40, "Electric"));
        skills.add(new Skill("Charge Beam", 30, "Electric"));
        return skills;
    }
    public static List<Skill> getElectricUltimates() {
        List<Skill> ults = new ArrayList<>();
        ults.add(new Skill("Thunder", 85, "Electric"));
        ults.add(new Skill("Zap Cannon", 90, "Electric"));
        return ults;
    }
}