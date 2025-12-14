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
    
    private Random rand = new Random();
    
    // Harded Fix
    public Pokemon(String name, int maxHp, int atk, int def, String type, String imagePath) {
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
        this.formImages = new ArrayList<>();
        this.names = new ArrayList<>();
        this.type = type;
        randomType();
        setup();
    }

    public Pokemon(){
        this("");
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
            } else {
                System.out.println("Fail to load image: " + imagePath);
            }
        }
    }
    
    private void setup() {
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
        
        if (this.name != null) {
            loadImages();
         } else {
            System.out.println("Name is null!!!");
        }
        
        
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
        
    }
}
