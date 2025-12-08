/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectorientedpokemon.map;

import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.map.entity.Monster;

/**
 *
 * @author User
 */
public class AssetSetter {
    private MapPanel mp;
    
    public AssetSetter(MapPanel mp) {
        this.mp = mp;
    }
    
    public void setMonster() {
        mp.monster[0] = new Monster(mp);
        mp.monster[0].worldX = GameConstants.TILE_SIZE * 28;
        mp.monster[0].worldY = GameConstants.TILE_SIZE * 10;
    }
}
