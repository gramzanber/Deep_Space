// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package controller;

import javax.swing.JFrame;
import model.GameData;
import view.GamePanel;
import view.MainWindow;
import model.Scoretracker;

public class Main
{
    public static GamePanel gamePanel;
    public static GameData gameData;
    public static Animator animator;
    public static Scoretracker score;

    public static int WIN_WIDTH = 700;
    public static int WIN_HEIGHT = 600;

    public static void main(String[] args)
    {
        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel();
        score = new Scoretracker();

        JFrame game = new MainWindow();
        game.setTitle("Tachibana Final Project");
        game.setSize(WIN_WIDTH, WIN_HEIGHT);
        game.setLocation(100, 0);
        game.setResizable(false);                                               // Window size cannot change
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        
        // Start animation
        new Thread(animator).start();
    }
}
