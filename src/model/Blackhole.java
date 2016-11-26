// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.GamePanel;

public class Blackhole extends GameFigure
{
    private final int WIDTH = 40;
    private final int HEIGHT = 10;
    private final int UNIT_TRAVEL = 2;                                          // Per frame
    private Image shipImage;
    private int health;

    public Blackhole(float x, float y) {
        super(x, y);
        super.state = STATE_ALIVE;
        
        this.health = 1;

        try {
            shipImage = ImageIO.read(getClass().getResource("..\\Images\\blackhole.jpg"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(shipImage, (int) super.x, (int) super.y, 30, 30, null);
    }

    @Override
    public void update()
    {
        if(super.x >= (GamePanel.width / 2))
        {
            super.x -= UNIT_TRAVEL;
        }
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Double(this.x - WIDTH / 2, this.y - HEIGHT / 2, .9 * WIDTH, .9 * HEIGHT);
    }
    
    @Override
    public String getObjectType()
    {
        return "Spaceship";
    }
    
    @Override
    public int getSize()
    {
        return 5;
    }

    @Override
    public int getHealth() { return this.health; }

    @Override
    public void damageFigure() { this.health = this.health - 1; }
}
