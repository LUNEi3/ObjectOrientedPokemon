/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.objectorientedpokemon.combat;
import com.mycompany.objectorientedpokemon.GameConstants;
import com.mycompany.objectorientedpokemon.GameManager;
import com.mycompany.objectorientedpokemon.entity.Pokemon;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.imageio.ImageIO;
/**
 *
 * @author thana
 */
public class BattlePanel extends javax.swing.JPanel {
    private Pokemon myPokemon;
    private Pokemon enemy;
    private GameManager gameM;
    
    private java.util.List<GameItem> myBag = new java.util.ArrayList<>();
    private Random rand = new Random();
    private javax.swing.JTextArea txtLog;
    private javax.swing.JScrollPane scrollLog;
    private java.awt.image.BufferedImage backgroundImage;
    
    public BattlePanel(GameManager gameM) { 
        initComponents();
        this.gameM = gameM;
        
        // --- Add Items to Bag ---
        myBag.add(new GameItem("Potion", 20, 5));
        myBag.add(new GameItem("Super Potion", 50, 2));
        myBag.add(new GameItem("Poke Ball", 0, 5));      
        myBag.add(new GameItem("Ultra Ball", 0, 2));    
        myBag.add(new GameItem("Master Ball", 0, 1));   
        myBag.add(new GameItem("Rare Candy", 0, 1));   
        // ---------------------

        // --- Create Narrator Box ---
        txtLog = new javax.swing.JTextArea();
        txtLog.setEditable(false); 
        txtLog.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 16));
        
        scrollLog = new javax.swing.JScrollPane(txtLog); 
        
        scrollLog.setBounds(50, 550, 750, 120); 
        
        this.add(scrollLog); 
        // ---------------------------------------
        
        this.setLayout(null); 
        
        try {
            int w = GameConstants.SCREEN_WIDTH;
            int h = GameConstants.SCREEN_HEIGHT;
            this.setPreferredSize(new java.awt.Dimension(w, h));
        } catch (Exception e) {
            this.setPreferredSize(new java.awt.Dimension(1280, 720)); 
        }
        
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/images/BG_main.png"));
            // logo = ImageIO.read(getClass().getResourceAsStream("/images/logo_pokemon.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // --- Adjust Positions ---
        lblEnemy.setBounds(800, 50, 350, 350); 
        lblPlayer.setBounds(150, 200, 350, 350); 
        
        jPanel1.setBounds(850, 550, 380, 120);
        pnlSkills.setBounds(850, 550, 380, 120);
        
        pnlSkills.setVisible(false); 
    }
    
     @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        
        // Draw image first (so it is behind the buttons)
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 1280, 720, null);
            // g.drawImage(logo, 0, 0, 188 * 3, 105 * 3, this);
        }
    }
    
    public void startBattle(Pokemon p1, Pokemon p2) {
        this.myPokemon = p1;
        this.enemy = p2;
        
        // Setup Party
        // System.out.println(gameM.player.myParty);
        int i = 0;
        while (myPokemon.hp == 0 && i < gameM.player.myParty.size()) {
            myPokemon = gameM.player.myParty.get(i);
            i++;
        }
        
        setButtonsEnabled(true);
        txtLog.removeAll();
        txtLog.setText("Battle Start!\n------------------------------------------------\nWhat will " + (myPokemon != null ? myPokemon.name : "you") + " do?");
        updateGUI(); 
    }

    private void updateGUI() {
        if (myPokemon != null && enemy != null) {
            
            // Update Labels
            lblPlayer.setText(myPokemon.name + " HP: " + myPokemon.hp + "/" + myPokemon.maxHp);
            lblEnemy.setText(enemy.name + " HP: " + enemy.hp + "/" + enemy.maxHp);
            
            int iconSize = 300; 
            
            // Update Images
            if (myPokemon.image != null) {
                lblPlayer.setIcon(getScaledIcon(myPokemon.image, iconSize, iconSize));
                lblPlayer.setHorizontalTextPosition(javax.swing.JLabel.CENTER);
                lblPlayer.setVerticalTextPosition(javax.swing.JLabel.BOTTOM);
            }
            
            if (enemy.image != null) {
                lblEnemy.setIcon(getScaledIcon(enemy.image, iconSize, iconSize));
                lblEnemy.setHorizontalTextPosition(javax.swing.JLabel.CENTER);
                lblEnemy.setVerticalTextPosition(javax.swing.JLabel.BOTTOM);
            }
        
            jButton3.setText("Bag");
        }
    }

    private javax.swing.ImageIcon getScaledIcon(javax.swing.ImageIcon srcIcon, int w, int h) {
        if (srcIcon == null) return null;
        
        int originalW = srcIcon.getIconWidth();
        int originalH = srcIcon.getIconHeight();
        
        double ratio = Math.min((double)w / originalW, (double)h / originalH);
        
        int newW = (int)(originalW * ratio);
        int newH = (int)(originalH * ratio);
        
        java.awt.Image img = srcIcon.getImage();
        java.awt.Image newImg = img.getScaledInstance(newW, newH, java.awt.Image.SCALE_SMOOTH);
        
        return new javax.swing.ImageIcon(newImg);
    }

    private void performAttack(int baseDamage, String skillName, String skillType) {
        setButtonsEnabled(false); 

        double multiplier = getTypeMultiplier(skillType, enemy.type);
        int finalDamage = (int) (baseDamage * multiplier);
        
        // --- Narrator ---
        String effectText = "";
        if (multiplier > 1.0) effectText = " (Super Effective!)";
        else if (multiplier < 1.0 && multiplier > 0) effectText = " (Not very effective...)";
        
        log("You used " + skillName + "!");
        if (!effectText.isEmpty()) log(effectText);
        log("Dealt " + finalDamage + " damage to " + enemy.name + ".");
        // --------------------------------
        
        enemy.takeDamage(finalDamage);
        setButtonsEnabled(true);
        updateGUI(); 
        
        if (enemy.hp <= 0) {
            log(enemy.name + " fainted! You Win!"); 
            
            int x = rand.nextInt(0, 6);
            myBag.get(x).quantity++;
            
            javax.swing.JOptionPane.showMessageDialog(this, "You Win!" + "You got " + myBag.get(x).name);
            gameM.showMap();
            return; 
        }

        Timer enemyTurnTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enemyTurnAction(); 
            }
        });
        enemyTurnTimer.setRepeats(false); 
        enemyTurnTimer.start();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblPlayer = new javax.swing.JLabel();
        lblEnemy = new javax.swing.JLabel();
        pnlSkills = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnSkill1 = new javax.swing.JButton();
        btnSkill2 = new javax.swing.JButton();
        btnSkill3 = new javax.swing.JButton();
        btnSkill4 = new javax.swing.JButton();

        jButton1.setText("Fight");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Pokemon");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Bag");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Run");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        lblPlayer.setText("lblPlayer");

        lblEnemy.setText("lblEnemy");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSkill1.setText("jButton6");
        btnSkill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkill1ActionPerformed(evt);
            }
        });

        btnSkill2.setText("jButton7");
        btnSkill2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkill2ActionPerformed(evt);
            }
        });

        btnSkill3.setText("jButton8");
        btnSkill3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkill3ActionPerformed(evt);
            }
        });

        btnSkill4.setText("jButton9");
        btnSkill4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkill4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSkillsLayout = new javax.swing.GroupLayout(pnlSkills);
        pnlSkills.setLayout(pnlSkillsLayout);
        pnlSkillsLayout.setHorizontalGroup(
            pnlSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSkillsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSkill3)
                    .addComponent(btnSkill1))
                .addGap(31, 31, 31)
                .addGroup(pnlSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSkill2)
                    .addComponent(btnSkill4))
                .addGap(82, 82, 82))
        );
        pnlSkillsLayout.setVerticalGroup(
            pnlSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSkillsLayout.createSequentialGroup()
                .addGroup(pnlSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSkillsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack))
                    .addGroup(pnlSkillsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSkill2)
                            .addComponent(btnSkill1))))
                .addGap(18, 18, 18)
                .addGroup(pnlSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSkill3)
                    .addComponent(btnSkill4))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(828, Short.MAX_VALUE)
                .addComponent(lblEnemy)
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblPlayer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlSkills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(lblEnemy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(lblPlayer)
                .addGap(50, 50, 50)
                .addComponent(pnlSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        openPokemonMenu(); // เรียกเมนูเปลี่ยนตัว// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showSkillMenu();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSkill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkill1ActionPerformed
       useSkill(0); // TODO add your handling code here:
    }//GEN-LAST:event_btnSkill1ActionPerformed

    private void btnSkill2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkill2ActionPerformed
       useSkill(1); // TODO add your handling code here:
    }//GEN-LAST:event_btnSkill2ActionPerformed

    private void btnSkill3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkill3ActionPerformed
        useSkill(2);// TODO add your handling code here:
    }//GEN-LAST:event_btnSkill3ActionPerformed

    private void btnSkill4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkill4ActionPerformed
        useSkill(3);// TODO add your handling code here:
    }//GEN-LAST:event_btnSkill4ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        showMainMenu();// TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        openBagMenu();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tryToRun();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void enemyTurnAction() {
        Skill enemySkill = null;
        if (!enemy.mySkills.isEmpty()) {
            int randIndex = (int)(Math.random() * enemy.mySkills.size());
            enemySkill = enemy.mySkills.get(randIndex);
        }

        int damageToPlayer = 0;
        String skillName = "Struggle"; 
        
        if (enemySkill != null) {
            skillName = enemySkill.name;
            double multiplier = getTypeMultiplier(enemySkill.type, myPokemon.type);
            damageToPlayer = (int) (enemySkill.damage * multiplier);
            
        } else {
            damageToPlayer = 10; 
        }

        myPokemon.takeDamage(damageToPlayer);
        gameM.playSE(6);
        
        // --- Narrator ---
        log("Enemy " + enemy.name + " used " + skillName + "!");
        log("You took " + damageToPlayer + " damage.");
        // --------------------
        
        updateGUI();

        if (myPokemon.hp <= 0) {
            gameM.playSE(5);
            log(myPokemon.name + " fainted... You blacked out.");
            javax.swing.JOptionPane.showMessageDialog(this, myPokemon.name + " fainted...");
            myPokemon.hp = 0;
            gameM.showMap();
        } else {
            setButtonsEnabled(true);
            log("------------------------------------------------"); 
            log("What will " + myPokemon.name + " do?"); 
        }
    }

    private void setButtonsEnabled(boolean enabled) {
        jButton1.setEnabled(enabled); // Fight
        jButton2.setEnabled(enabled); // Pokemon
        jButton3.setEnabled(enabled); // Bag
        jButton4.setEnabled(enabled); // Run
    }

    // --- Skill Menu ---
    private void showSkillMenu() {
        gameM.playSE(4);
        jPanel1.setVisible(false);
        pnlSkills.setVisible(true); 
        
        updateSkillButtons();
    }

    private void showMainMenu() {
        pnlSkills.setVisible(false);
        jPanel1.setVisible(true);
    }

    private void updateSkillButtons() {
        javax.swing.JButton[] buttons = {btnSkill1, btnSkill2, btnSkill3, btnSkill4};
        for (javax.swing.JButton b : buttons) {
            b.setText("---");
            b.setEnabled(false);
        }

        if (myPokemon != null) {
            for (int i = 0; i < myPokemon.mySkills.size(); i++) {
                if (i < 4) { 
                    Skill s = myPokemon.mySkills.get(i);
                    buttons[i].setText(s.name + " (" + s.damage + ")");
                    buttons[i].setEnabled(true);
                }
            }
        }
    }

    private void useSkill(int index) {
        if (index > 1 ) {
            gameM.playSE(7);
        } else {
            gameM.playSE(6);
        }
        if (myPokemon.mySkills.size() > index) {
            Skill selectedSkill = myPokemon.mySkills.get(index);
            
            System.out.println("Used skill: " + selectedSkill.name);
            
            performAttack(selectedSkill.damage, selectedSkill.name, selectedSkill.type);
            
            showMainMenu();
        }
    } 

    private double getTypeMultiplier(String skillType, String enemyType) {
        if (skillType == null || enemyType == null) return 1.0;
        
        if (skillType.equals(enemyType)) {
            return 0.5;
        }
        
        switch (skillType) {
            case "Electric":
                if (enemyType.equals("Water")) return 2.0;
                break;
            case "Water":
                if (enemyType.equals("Fire")) return 2.0;
                break;
            case "Fire":
                if (enemyType.equals("Leaf")) return 2.0;
                break;
            case "Leaf":
                if (enemyType.equals("Rock")) return 2.0;
                break;
            case "Rock":
                if (enemyType.equals("Electric")) return 2.0;
                break;
        }
        
        return 1.0;
    } 
    
    // --- Inventory System ---
    private void openBagMenu() {
        gameM.playSE(4);
        java.util.List<GameItem> availableItems = new java.util.ArrayList<>();
        for (GameItem item : myBag) {
            if (item.quantity > 0) {
                availableItems.add(item);
            }
        }
        
        if (availableItems.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "The bag is empty! No items left.");
            return;
        }

        GameItem[] options = availableItems.toArray(new GameItem[0]);

        GameItem selectedItem = (GameItem) javax.swing.JOptionPane.showInputDialog(
                this,
                "Choose an item to use:",         
                "Bag",               
                javax.swing.JOptionPane.PLAIN_MESSAGE,
                null,
                options,                    
                options[0]);                

        if (selectedItem != null) {
            useItem(selectedItem);
        }
    }

    private void useItem(GameItem item) {
        item.quantity--; 
        gameM.playSE(9);
        if (item.name.contains("Ball")) {
            tryToCatch(item.name); 
        } else if (item.name.contains("Candy")) {
            String prevName = myPokemon.name;
            myPokemon.evolution();
            javax.swing.JOptionPane.showMessageDialog(this, prevName + " evoled into " + myPokemon.name + "!");
            updateGUI();
            
        } else {
            // Potion Logic
            if (myPokemon.hp >= myPokemon.maxHp) {
                javax.swing.JOptionPane.showMessageDialog(this, "HP is already full!");
                item.quantity++; 
                return;
            }
            int oldHp = myPokemon.hp;
            myPokemon.hp += item.healAmount;
            if (myPokemon.hp > myPokemon.maxHp) myPokemon.hp = myPokemon.maxHp;
            
            updateGUI();
            javax.swing.JOptionPane.showMessageDialog(this, 
                    "Used " + item.name + "! Healed " + (myPokemon.hp - oldHp) + " HP.");
            
            setButtonsEnabled(false);
            Timer enemyTurnTimer = new Timer(1500, e -> enemyTurnAction());
            enemyTurnTimer.setRepeats(false);
            enemyTurnTimer.start();
        }
    }

    private void tryToRun() {
        if (Math.random() > 0.5) {
            javax.swing.JOptionPane.showMessageDialog(this, "Got away safely!");
            gameM.showMap();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Failed to escape!");
            
            setButtonsEnabled(false); 
            
            Timer enemyTurnTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enemyTurnAction(); 
                }
            });
            enemyTurnTimer.setRepeats(false);
            enemyTurnTimer.start();
        }
    }

    // --- Switch System ---
    private void openPokemonMenu() {
        if (gameM.player.myParty.isEmpty()) return;

        gameM.playSE(4);
        
        Pokemon[] options = gameM.player.myParty.toArray(new Pokemon[0]);
        Pokemon selectedPoke = (Pokemon) javax.swing.JOptionPane.showInputDialog(
                this, "Choose a Pokemon:", "Party",
                javax.swing.JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (selectedPoke != null) {
            
            gameM.playSE(8);
            
            if (selectedPoke == myPokemon) {
                javax.swing.JOptionPane.showMessageDialog(this, "This Pokemon is already battling!");
                return;
            }
            if (selectedPoke.hp <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "This Pokemon has fainted!");
                return;
            }

            myPokemon = selectedPoke;
            
            gameM.player.myParty.remove(myPokemon);
            gameM.player.myParty.add(0, myPokemon);
            
            updateGUI();
            updateSkillButtons(); 
            
            javax.swing.JOptionPane.showMessageDialog(this, "Go! " + myPokemon.name + "!");
            
            setButtonsEnabled(false);
            Timer enemyTurnTimer = new Timer(1500, e -> enemyTurnAction());
            enemyTurnTimer.setRepeats(false);
            enemyTurnTimer.start();
        }
    }

    // --- Catch System ---
    private void tryToCatch(String ballName) {
        System.out.println("Threw " + ballName + "!");
        
        double hpPercent = (double) enemy.hp / enemy.maxHp;
        double catchChance = (1.0 - hpPercent) + 0.2; 
        
        if (ballName.equals("Master Ball")) catchChance = 100.0;
        else if (ballName.equals("Ultra Ball")) catchChance += 0.3;
        
        if (Math.random() < catchChance) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                    "Gotcha! " + enemy.name + " was caught!");
            
            gameM.player.myParty.add(enemy);
            // System.out.println(gameM.player.myParty);
            gameM.showMap();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, 
                    "Aww! It appeared to be caught! (It broke free!)");
            
            setButtonsEnabled(false);
            Timer enemyTurnTimer = new Timer(1500, e -> enemyTurnAction());
            enemyTurnTimer.setRepeats(false);
            enemyTurnTimer.start();
        }
    }

    private void log(String text) {
        txtLog.append("\n" + text); 
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSkill1;
    private javax.swing.JButton btnSkill2;
    private javax.swing.JButton btnSkill3;
    private javax.swing.JButton btnSkill4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEnemy;
    private javax.swing.JLabel lblPlayer;
    private javax.swing.JPanel pnlSkills;
    // End of variables declaration//GEN-END:variables
}
