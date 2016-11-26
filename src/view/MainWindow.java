// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package view;

import controller.ButtonListener;
import controller.KeyController;
import controller.Main;
import controller.MouseController;
import controller.SoundController;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
    
    public static JPanel gameStatPanel;
    public static JButton quitButton;

    public MainWindow()
    {
        quitButton = new JButton("Quit");

        quitButton.setFocusable(false);

        ButtonListener buttonListener = new ButtonListener();
        quitButton.addActionListener(buttonListener);

        //JPanel southPanel = new JPanel();
        //southPanel.add(quitButton);

        MouseController mouseController = new MouseController();
        Main.gamePanel.addMouseListener(mouseController);
        
        SoundController soundController = new SoundController();
        soundController.backgroundMusic();

        KeyController keyListener = new KeyController();
        Main.gamePanel.addKeyListener(keyListener);
        Main.gamePanel.setFocusable(true);

        Container c = getContentPane();
        c.add(Main.gamePanel, "Center");
        //c.add(southPanel, "South");
    }
}