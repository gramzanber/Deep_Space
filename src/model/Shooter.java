// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Shooter extends GameFigure
{
    private Image launcherImageLeft, launcherImageRight;
    private boolean direction;
    private int health;

    public Shooter(int x, int y)
    {
        super(x, y);
        super.state = STATE_ALIVE;

        try { launcherImageLeft = ImageIO.read(getClass().getResource("..\\Images\\Player_Ship_Left.png")); }
        catch (IOException ex) { JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png" + ex.getMessage()); System.exit(-1); }
        try { launcherImageRight = ImageIO.read(getClass().getResource("..\\Images\\Player_Ship_Right.png")); }
        catch (IOException ex) { JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png" + ex.getMessage()); System.exit(-1); }
        
        this.direction = true;
        this.health = 10;
    }
    
    public boolean getDirection() { return this.direction; }

    @Override
    public void render(Graphics2D g)
    {
        if(!this.direction) { g.drawImage(launcherImageRight, (int) super.x, (int) super.y, 30, 30, null); }
        else { g.drawImage(launcherImageLeft, (int) super.x, (int) super.y, 30, 30, null); }
    }

    @Override
    public void update() {
        // no periodic update is required (not animated)
        // update is done via 'translate' when a key is pressed
    }

    public void translate(int dx, int dy)
    {
        if(dx >= 0) { this.direction = true; } else if(dx < 0) { this.direction = false; }
        super.x = super.x + dx;
        super.y = super.y + dy;
    }

    // Missile shoot location: adjut x and y to the image
    public float getXofMissileShoot() {
        return super.x + 15;
    }

    public float getYofMissileShoot() {
        return super.y;
    }

    public static Image getImage(String fileName) {
        Image image = null;
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException ioe) {
            System.out.println("Error: Cannot open image:" + fileName);
            JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
        }
        return image;
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Double(this.x, this.y, 30.0, 30.0);
    }
    
    @Override
    public String getObjectType()
    {
        return "Player";
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
