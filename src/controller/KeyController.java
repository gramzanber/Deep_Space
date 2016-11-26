// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Shooter;

public class KeyController implements KeyListener
{
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(Main.animator.isAtMainMenu())
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    Main.gamePanel.mainMenuSelectOption(1);
                    break;
                case KeyEvent.VK_DOWN:
                    Main.gamePanel.mainMenuSelectOption(-1);
                    break;
                case KeyEvent.VK_ENTER:
                    switch(Main.gamePanel.getMainMenuSelection())
                    {
                        case 0:
                            Main.animator.startGame();
                            break;
                        case 1:
                            Main.animator.scoreScreen();
                            break;
                        case 2:
                            if (Main.animator.running) { Main.animator.running = false; }
                            else { System.exit(0); }
                            break;
                    }
                    break;
            }
        }
        else if(Main.animator.isAtScoreScreen())
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_ENTER:
                    Main.animator.mainMenu();
                    break;
            }
        }
        else
        {
            Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    shooter.translate(-10, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    shooter.translate(10, 0);
                    break;
                case KeyEvent.VK_UP:
                    shooter.translate(0, -10);
                    break;
                case KeyEvent.VK_DOWN:
                    shooter.translate(0, 10);
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.out.println("Implement pause menu");
                    if (Main.animator.running) { Main.animator.running = false; }
                    else { System.exit(0); }
                    break;
                case KeyEvent.VK_ENTER:
                    System.out.println("Implement Chat box");
                    break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
