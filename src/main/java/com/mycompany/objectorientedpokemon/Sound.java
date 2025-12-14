package com.mycompany.objectorientedpokemon;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author pariwat.nira
 */
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    
    public Sound() {
        soundURL[0] = getClass().getResource("/sounds/toothless_dancing.wav");
        soundURL[1] = getClass().getResource("/sounds/menuBGM60.wav");
        soundURL[2] = getClass().getResource("/sounds/menuBGM30.wav");
        soundURL[3] = getClass().getResource("/sounds/encounter.wav");
        soundURL[4] = getClass().getResource("/sounds/btnClick.wav");
        soundURL[5] = getClass().getResource("/sounds/gameOver.wav");
    }
    
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
            // OPTIONAL: Lower the volume (Games are usually too loud!)
             FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
             gainControl.setValue(-30.0f); // Reduce volume by 20 decibels
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void play() {
        if (clip != null) {
            clip.start();
        }
    }
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
