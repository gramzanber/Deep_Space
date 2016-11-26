// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package controller;

import java.awt.geom.Rectangle2D;
import model.Blackhole;
import model.GameFigure;

public class Animator implements Runnable
{
    public boolean running = true;
    private boolean mainMenu = true;
    private boolean scoreScreen = false;
    private final int FRAMES_PER_SECOND = 60;

    @Override
    public void run()
    {
        //long startTime = System.currentTimeMillis();
        
        while (running)
        {
            if(this.mainMenu)
            {
                Main.gamePanel.menuRender();
            }
            else if(this.scoreScreen)
            {
                Main.gamePanel.scoreScreenRender();
            }
            else
            {
                processCollisions();

                Main.gameData.update();
                Main.gamePanel.gameRender();
                Main.gamePanel.printScreen();
            }
            //long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 3000);// - (int) (endTime - startTime);
            //System.out.println("Sleep Time: " + sleepTime);
            if (sleepTime > 0)
            {
                try {
                    Thread.sleep(sleepTime); // ms
                } catch (InterruptedException e) {

                }
            }
        }
        System.exit(0);
    }

    private synchronized void processCollisions()
    {
        // detect collisions between friendFigure and enemyFigures
        // if detected, mark it as STATE_DONE, so that
        // they can be removed at update() method       
        for (GameFigure friendFigure : Main.gameData.friendFigures)
        {
            Rectangle2D friendBox = ((GameFigure) friendFigure).getCollisionBox();
            for (GameFigure enemyFigure : Main.gameData.enemyFigures)
            {
                GameFigure enemy = (GameFigure) enemyFigure;
                if (friendBox.intersects(enemy.getCollisionBox()))
                {
                    enemy.damageFigure();
                    if(enemy.getHealth() == 0){ enemy.state = GameFigure.STATE_DESTROYED; }
                    if(enemy instanceof Blackhole) { Main.score.addToScore(10000); Main.gameData.goUpLevel(); }
                }
            }
        }    
    }
    
    public boolean isAtMainMenu()
    {
        return this.mainMenu;
    }
    
    public boolean isAtScoreScreen()
    {
        return this.scoreScreen;
    }
    
    public void startGame()
    {
        this.mainMenu = false;
        this.scoreScreen = false;
    }
    
    public void scoreScreen()
    {
        this.mainMenu = false;
        this.scoreScreen = true;
    }
    
    public void mainMenu()
    {
        this.mainMenu = true;
        this.scoreScreen = false;
    }
}
