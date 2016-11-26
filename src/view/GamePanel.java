// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package view;

import controller.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
    // Size of the canvas - determined at runtime once rendered
    public static int width;
    public static int height;

    // Off screen rendering
    private Graphics2D g2;
    private Image dbImage = null;                                               // Double buffer image
    private Image backgroundImage[];                                       // Double buffer image
    private int backgroundLocation;
    private int mainMenuHover;
    
    public GamePanel()
    {
        this.backgroundImage = new Image[2];
        try
        {
            backgroundImage[0] = ImageIO.read(getClass().getResource("..\\Images\\corona_bk.png"));
            backgroundImage[1] = ImageIO.read(getClass().getResource("..\\Images\\redeclipse_bk.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open background.png");
            System.exit(-1);
        }
        this.backgroundLocation = 0;
        this.mainMenuHover = 0;
    }
    
    public void menuRender()
    {
        width = getSize().width;
        height = getSize().height;
        
        g2 = (Graphics2D)this.getGraphics();
        g2.setColor(Color.RED);
        
        if(Math.abs(this.backgroundLocation) >= 350) { this.backgroundLocation = 0; }
        else { this.backgroundLocation = this.backgroundLocation - 5; }
        //System.out.println("Size: " + backgroundImage[0].getWidth(null) + " Location: " + Math.abs(this.backgroundLocation));
        g2.drawImage(backgroundImage[0], this.backgroundLocation, 0, backgroundImage[0].getWidth(null), backgroundImage[0].getHeight(null), null);
        
        g2.setFont(new Font("TimesRoman", Font.BOLD, 50));
        String gameNameString = "Star Gazer";
        g2.drawString(gameNameString, (width / 2) - 125, 100);
        
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        
        // Game Information
        String informationString = "Start";
        g2.drawString(informationString, (width / 2) - 50, (height / 2) - 50);
        // Level Information
        String levelString = "High Score";
        g2.drawString(levelString, (width / 2) - 50, (height / 2));
        // Score
        String scoreString = String.format("Quit");
        g2.drawString(scoreString, (width / 2) - 50, (height / 2) + 50);
        
        // Show selection
        if(this.mainMenuHover == 0) { g2.drawRect((width / 2) - 60, (height / 2) - 70, 120, 30); }
        else if(this.mainMenuHover == 1) { g2.drawRect((width / 2) - 60, (height / 2) - 20, 120, 30); }
        else if(this.mainMenuHover == 2) { g2.drawRect((width / 2) - 60, (height / 2) + 30, 120, 30); }
    }

    public void scoreScreenRender()
    {
        width = getSize().width;
        height = getSize().height;
        
        g2 = (Graphics2D)this.getGraphics();
        g2.setColor(Color.RED);
        
        if(Math.abs(this.backgroundLocation) >= 350) { this.backgroundLocation = 0; }
        else { this.backgroundLocation = this.backgroundLocation - 5; }
        g2.drawImage(backgroundImage[0], this.backgroundLocation, 0, backgroundImage[0].getWidth(null), backgroundImage[0].getHeight(null), null);
        
        g2.setFont(new Font("TimesRoman", Font.BOLD, 50));
        String gameNameString = "Star Gazer";
        g2.drawString(gameNameString, (width / 2) - 125, 100);
        
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        
        // Level Information
        String levelString = "High Scores";
        g2.drawString(levelString, (width / 2) - 50, (height / 2) - 140);
        // Score
        String scoreString01 = "1. Tyrel Tachibana 4526 11/27/2015";
        String scoreString02 = "2. Shay Piper 4515 11/28/2015";
        String scoreString03 = "3. N/A";
        String scoreString04 = "4. N/A";
        String scoreString05 = "5. N/A";
        g2.drawString(scoreString01, (width / 2) - 70, (height / 2) - 100);
        g2.drawString(scoreString02, (width / 2) - 70, (height / 2) - 80);
        g2.drawString(scoreString03, (width / 2) - 70, (height / 2) - 60);
        g2.drawString(scoreString04, (width / 2) - 70, (height / 2) - 40);
        g2.drawString(scoreString05, (width / 2) - 70, (height / 2) - 20);
        // Back Button
        String scoreString = String.format("Main Menu");
        g2.drawString(scoreString, (width / 2) - 50, (height / 2) + 50);
        
        // Highlight back button
        g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
    }
    
    public void gameRender()
    {
        width = getSize().width;
        height = getSize().height;
        if (dbImage == null) {
            // Creates an off-screen drawable image to be used for double buffering
            dbImage = createImage(width, height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            } else {
                g2 = (Graphics2D) dbImage.getGraphics();
            }
        }
        
        if(Math.abs(this.backgroundLocation) >= 350) { this.backgroundLocation = 0; }
        else { this.backgroundLocation = this.backgroundLocation - 5; }
        g2.drawImage(backgroundImage[Main.gameData.getLevel()], this.backgroundLocation, 0, backgroundImage[Main.gameData.getLevel()].getWidth(null), backgroundImage[Main.gameData.getLevel()].getHeight(null), null);
        
        this.drawStatistics();

        if (Main.animator.running)
        {
            synchronized (Main.gameData.enemyFigures)
            {
                Main.gameData.enemyFigures.stream().forEach((f) -> { f.render(g2); });

                try{
            Main.gameData.friendFigures.stream().forEach((f) -> {
                    f.render(g2);
                });
            }
            catch(Exception e) { System.out.println("GamePanel Line 82: " + e.getMessage()); }
                

            }

        }
    }

    // Use active rendering to put the buffered image on-screen
    public void printScreen()
    {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();                                 // Sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
    
    private void drawStatistics()
    {
        g2.setColor(Color.RED);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        
        // Game Information
        String informationString = "Star Gazer";
        g2.drawString(informationString, 20, 20);
        // Level Information
        String levelString = "Level: " + (Main.gameData.getLevel() + 1);
        g2.drawString(levelString, (width / 2) - 50, 20);
        // Score
        String scoreString = String.format("Score: %010d", Main.score.getScore());
        g2.drawString(scoreString, width - 135, 20);
    }
    
    public int getMainMenuSelection()
    {
        return this.mainMenuHover;
    }
    
    public void mainMenuSelectOption(int movement)
    {
        if(movement > 0)
        {
            if(this.mainMenuHover > 0){ this.mainMenuHover = this.mainMenuHover - 1; }
            else if(this.mainMenuHover == 0){ this.mainMenuHover = 2; }
        }
        if(movement < 0)
        {
            if(this.mainMenuHover < 2){ this.mainMenuHover = this.mainMenuHover + 1; }
            else if(this.mainMenuHover == 2){ this.mainMenuHover = 0; }
        }
    }
}