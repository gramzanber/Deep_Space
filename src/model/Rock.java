// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static model.GameFigure.STATE_DONE;
import view.GamePanel;

public class Rock extends GameFigure
{
    private final int UNIT_TRAVEL = 1;                                          // Per frame
    private Image shipImage;
    private int rock;
    private int health;

    public Rock(float x, float y)
    {
        super(x, y);
        super.state = STATE_ALIVE;
        
        try
        {
            this.rock = (int)(Math.random() * 3);
            if(rock == 0) { shipImage = ImageIO.read(getClass().getResource("..\\Images\\Rock_Small.png")); this.health = 1; }
            else if(rock == 1) { shipImage = ImageIO.read(getClass().getResource("..\\Images\\Rock_Medium.png")); this.health = 2; }
            else if(rock == 2) { shipImage = ImageIO.read(getClass().getResource("..\\Images\\Rock_Large.png")); this.health = 3; }
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png" + ex.getMessage());
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        if(rock == 0) { g.drawImage(shipImage, (int) super.x, (int) super.y, 10, 10, null); }
        else if(rock == 1) { g.drawImage(shipImage, (int) super.x, (int) super.y, 20, 20, null); }
        else if(rock == 2) { g.drawImage(shipImage, (int) super.x, (int) super.y, 40, 40, null); }
    }

    @Override
    public void update()
    {
        super.x = super.x - UNIT_TRAVEL;
        boolean targetReached = super.x >= GamePanel.width;
        if (targetReached) { super.state = STATE_DONE; }
    }

    @Override
    public Rectangle2D getCollisionBox()
    {
        if(rock == 0) { return new Rectangle2D.Double((int)this.x, (int)this.y, 10, 10); }
        else if(rock == 1) { return new Rectangle2D.Double((int)this.x, (int)this.y, 20, 20); }
        else if(rock == 2) { return new Rectangle2D.Double((int)this.x, (int)this.y, 40, 40); }
        else { System.out.printf("There is a problem in the Rock class, method getCollisionBox" + "\n"); return null; }
    }
    
    @Override
    public int getSize() { return this.rock; }
    
    @Override
    public String getObjectType() { return "Rock"; }

    @Override
    public int getHealth() { return this.health; }

    @Override
    public void damageFigure() { this.health = this.health - 1; }
}
