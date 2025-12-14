/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.entity;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Pokemon {
    public String name, type;
    public int hp, maxHp, atk, def;
    public BufferedImage image;
    public ArrayList<BufferedImage> formImages;
    public ArrayList<String> names;
    
    public int form;
    private Random rand = new Random();
    
    // Harded Fix
    public Pokemon(String name, int maxHp, int atk, int def, String type, String imagePath) {
        this.form = 1;
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.atk = atk;
        this.def = def;
        this.type = type;
        
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                this.image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public Pokemon(String type) {
        this.form = 1;
        this.formImages = new ArrayList<>();
        this.names = new ArrayList<>();
        this.type = type;
        randomType();
        setup();
    }

    public Pokemon(){
        this("");
        this.form = 1;
    }
    
    public void evolution() {
        
        // Can't evole if reach max evolution form
        if (this.form >= names.size()) {
            // System.out.println("This Pokemon can't evolution anymore. " + this.form);
            return;
        }
        
        // Default evolution multiplier
        double min = 1.3;
        double max = 1.7;
        
        // In case max evolution at 2, more evolution multiplier
        if (names.size() == 2) {
            min = 1.8;
            max = 2.5;
            // System.out.println("More multiplier " + min + " " +max);
        }
        this.maxHp = (int) (this.maxHp * (min + (rand.nextDouble() * (max - min))));
        this.atk = (int) (this.atk * (min + (rand.nextDouble() * (max - min))));
        this.def = (int) (this.def * (min + (rand.nextDouble() * (max - min))));
        form += 1;
        
        // Update name and image
        this.name = this.names.get(form - 1);
        this.image = this.formImages.get(form - 1);
    }
    
    private void randomType() {
        if (this.type.equals("")) {
            int x = rand.nextInt(5);

            switch (x) {
                case 0:
                    this.type = "fire";
                    break;
                
                case 1:
                    this.type = "water";
                    break;

                case 2:
                    this.type = "leaf";
                    break;

                case 3:
                    this.type = "electric";
                    break;

                case 4:
                    this.type = "rock";
                    break;
            
                default:
                    break;
            }
        }
    }
    
    private void loadImages() {
        String path = "/pokemon/" + this.type + "/" + this.name;
        
        for (int i = 0; i < 3 ; i++) {
            String imagePath = path + "/" + (i + 1) + ".png";
            if (getClass().getResourceAsStream(imagePath) != null) {
                try {
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream(imagePath));
                    formImages.add(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void setup() {
        
        // Load Base Name
        String path = "/pokemon/" + this.type + "/base" + ".txt";
        ArrayList<String> arr = new ArrayList<>();
        try {
            
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            int i = 0;

            while ((line = br.readLine()) != null) {
                arr.add(line);
                i++;
            }
            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Set Base Name
        this.name = arr.get(rand.nextInt(arr.size()));
        
        
        // Load all names of Base Pokemon
        path = "/pokemon/" + this.type + "/" + this.name + "/name.txt";
        arr = new ArrayList<>();
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            int i = 0;

            while ((line = br.readLine()) != null) {
                arr.add(line);
                i++;
            }
            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        // Adding name of all Pokemon Forms to names[]
        for (String x: arr) {
            this.names.add(x);
        }
        
        
        // Load Images
        if (this.name != null) {
            loadImages();
         } else {
            System.out.println("Name is null!!!");
        }
        
        
        // Random Status based on Type
        switch (this.type) {
            case "fire" -> {
                this.maxHp = rand.nextInt(39, 50);
                this.atk = rand.nextInt(38, 74);
                this.def = rand.nextInt(32, 53);
            }
            case "electric" -> {
                this.maxHp = rand.nextInt(30, 55);
                this.atk = rand.nextInt(40, 68);
                this.def = rand.nextInt(27, 55);
            }
            case "leaf" -> {
                this.maxHp = rand.nextInt(45, 60);
                this.atk = rand.nextInt(49, 65);
                this.def = rand.nextInt(34, 54);
            }
            case "rock" -> {
                this.maxHp = rand.nextInt(42, 52);
                this.atk = rand.nextInt(42, 60);
                this.def = rand.nextInt(50, 80);
            }
            case "water" -> {
                this.maxHp = rand.nextInt(48, 77);
                this.atk = rand.nextInt(35, 63);
                this.def = rand.nextInt(36, 59);
            }
        }
    }
}
