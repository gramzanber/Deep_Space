// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package model;

import controller.Main;
import view.GamePanel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameData
{
    public final List<GameFigure> enemyFigures;
    public final List<GameFigure> friendFigures;
    public static Shooter shooter;
    private long rockTime = System.currentTimeMillis();
    private long enemyTime = System.currentTimeMillis();
    private boolean blackholeTime;
    private int enemyAmount;
    private boolean boss;
    private int level;

    public GameData()
    {
        enemyFigures = Collections.synchronizedList(new ArrayList<GameFigure>());
        friendFigures = Collections.synchronizedList(new ArrayList<GameFigure>());

        shooter = new Shooter(5, 250);

        friendFigures.add(shooter);
        this.boss = false;
        this.blackholeTime = false;
        this.level = 0;
        this.enemyAmount = (this.level + 1) * 5;
    }
    
    public void addUFOBoss()
    {
        this.enemyFigures.add(new BossShip((float) (GamePanel.width - 20), (float) (Math.random() * GamePanel.height)));
        this.boss = false;
    }

    public void addUFO()
    {
        this.enemyFigures.add(new EnemyShip((float) (GamePanel.width - 20), (float) (Math.random() * GamePanel.height)));
    }
    
    public void addRock()
    {
        this.enemyFigures.add(new Rock((float) (GamePanel.width - 20), (float) (Math.random() * GamePanel.height)));
    }
    
    public void addBlackhole()
    {
        this.enemyFigures.add(new Blackhole((float) (GamePanel.width - 20), (float) (Math.random() * GamePanel.height)));
        this.blackholeTime = false;
    }

    public void update()
    {
        synchronized (enemyFigures)
        {
            ArrayList<GameFigure> remove = new ArrayList<>();
            GameFigure f;
            for (GameFigure enemyFigure : enemyFigures)
            {
                f = enemyFigure;
                if (f.state == GameFigure.STATE_DESTROYED)
                {
                    remove.add(f);
                    if(f.getObjectType().equals("Rock")) { Main.score.addToScore((f.getSize() + 1) * 10); }
                    if(f.getObjectType().equals("Spaceship Boss")) { Main.score.addToScore(5000);  this.blackholeTime = true; }
                    if(f instanceof EnemyShip)
                    {
                        Main.score.addToScore(50);
                        this.enemyAmount = this.enemyAmount - 1;
                        if(this.enemyAmount == 0){ this.boss = true; } 
                    }
                }
            }
            enemyFigures.removeAll(remove);

            try{
            for (GameFigure g : enemyFigures) {
                g.update();
            }
            }
            catch(Exception e) { System.out.println("GameData Line 74: " + e.getMessage()); }
            
        }

        // missiles are removed if explosion is done
        synchronized (friendFigures) {
            ArrayList<GameFigure> remove = new ArrayList<>();
            GameFigure f;
            for (GameFigure friendFigure : friendFigures) {
                f = friendFigure;
                if (f.state == GameFigure.STATE_DONE)
                {
                    remove.add(f);
                }
            }
            friendFigures.removeAll(remove);

            for (GameFigure g : friendFigures) {
                g.update();
            }
        }
        
        boolean addRock = (System.currentTimeMillis() - this.rockTime) / 1000.0 > 5;
        if(addRock)
        {
            this.rockTime = System.currentTimeMillis();
            addRock();
        }
        
        boolean addEnemy = (System.currentTimeMillis() - this.enemyTime) / 1000.0 > 10;
        if(addEnemy)
        {
            this.enemyTime = System.currentTimeMillis();
            if(this.enemyAmount != 0) { addUFO(); }
        }
        
        if(this.blackholeTime)
        {
            addBlackhole();
        }
        
        if(this.boss) { this.addUFOBoss(); }
    }
    
    public int getLevel() { return this.level; }
    
    public void goUpLevel() { this.level = this.level + 1; this.enemyAmount = (this.level + 1) * 5; }
}
