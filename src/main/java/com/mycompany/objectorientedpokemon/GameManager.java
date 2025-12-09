/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon;

import com.mycompany.objectorientedpokemon.GameConstants;
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
    
    public JPanel mainContainer;
    private CardLayout cardLayout;
    
    private MenuPanel menu;
    private NewGamePanel newGame;
    private PickUpPanel pickUp;
    private MapPanel map;
    
    public GameManager() {  
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        mainContainer.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        
        menu = new MenuPanel(this);
        newGame = new NewGamePanel(this);
        pickUp = new PickUpPanel(this);
        map = new MapPanel();
        
        mainContainer.add(menu, "MENU");
        mainContainer.add(newGame, "NEW_GAME");
        mainContainer.add(pickUp, "PICK_UP");
        mainContainer.add(map, "MAP");
    }
    
    public JPanel getPanel() {
        return mainContainer;
    }
    
    public void showMenu() {
        cardLayout.show(mainContainer, "MENU");
    }
    
    public void showNewGame() {
        cardLayout.show(mainContainer, "NEW_GAME");
    }
    
    public void showPickUp() {
        cardLayout.show(mainContainer, "PICK_UP");
    }
    
    public void showMap()
    {
        cardLayout.show(mainContainer, "MAP");
        
        map.requestFocusInWindow();
    }    
}
