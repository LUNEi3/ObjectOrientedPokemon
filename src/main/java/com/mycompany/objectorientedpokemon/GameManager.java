/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.entity.Player;
import com.mycompany.objectorientedpokemon.map.MapPanel;
import com.mycompany.objectorientedpokemon.menu.MenuPanel;
import com.mycompany.objectorientedpokemon.menu.NewGamePanel;
import com.mycompany.objectorientedpokemon.menu.PickUpPanel;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class GameManager {
    
    public Player player;
    public JPanel mainContainer;
    public Sound music = new Sound();
    public Sound se = new Sound();
    private CardLayout cardLayout;
    
    private MenuPanel menu;
    private NewGamePanel newGame;
    private PickUpPanel pickUp;
    private MapPanel map;
    
    public GameManager() {  
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        mainContainer.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        
        player = new Player();
        menu = new MenuPanel(this);
        newGame = new NewGamePanel(this);
        pickUp = new PickUpPanel(this);
        map = new MapPanel(this);
        
        mainContainer.add(menu, "MENU");
        mainContainer.add(newGame, "NEW_GAME");
        mainContainer.add(pickUp, "PICK_UP");
        mainContainer.add(map, "MAP");
        
        playMusic(2);
    }
    
    public JPanel getPanel() {
        return mainContainer;
    }
    
    public void showMenu() {
        stopMusic();
        playSE(4);
        
        cardLayout.show(mainContainer, "MENU");
        
        playMusic(2);
    }
    
    public void showNewGame() {
        playSE(4);
        cardLayout.show(mainContainer, "NEW_GAME");
    }
    
    public void showPickUp() {
        playSE(4);
        cardLayout.show(mainContainer, "PICK_UP");
    }
    
    public void showMap() {
        stopMusic();
        playSE(4);
        cardLayout.show(mainContainer, "MAP");
        
        map.setupGame();
        playMusic(1);
    }    
    
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
    public void stopSE() {
        se.stop();
    }
}
