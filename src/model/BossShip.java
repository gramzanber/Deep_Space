// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class BossShip extends GameFigure
{
    private final int WIDTH = 40;
    private final int HEIGHT = 10;
    private final int UNIT_TRAVEL = 10;                                          // Per frame
    private Image shipImageLeft, shipImageRight;
    private long shootTime = System.currentTimeMillis();
    private boolean direction;
    private boolean getCloser;
    private int health;

    public BossShip(float x, float y)
    {
        super(x, y);
        super.state = STATE_ALIVE;

        try { this.shipImageLeft = ImageIO.read(getClass().getResource("..\\Images\\Boss_Ship_Left.png")); }
        catch (IOException ex) { JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png" + ex.getMessage()); System.exit(-1); }
        try { this.shipImageRight = ImageIO.read(getClass().getResource("..\\Images\\Boss_Ship_Right.png")); }
        catch (IOException ex) { JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png" + ex.getMessage()); System.exit(-1); }
        
        this.getCloser = true;
        this.direction = false;
        this.health = 5;
    }

    @Override
    public void render(Graphics2D g)
    {
        if(!this.direction) { g.drawImage(this.shipImageLeft, (int) super.x, (int) super.y, 50, 50, null); }
        else { g.drawImage(this.shipImageRight, (int) super.x, (int) super.y, 50, 50, null); }
    }

    @Override
    public void update()
    {
        boolean shootLaser = (System.currentTimeMillis() - this.shootTime) / 1000.0 > 2;
        Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);
        float shooterY = shooter.getYofMissileShoot();
        float shooterX = shooter.getXofMissileShoot();
        double distance = Math.sqrt((super.x-shooterX)*(super.x-shooterX) + (super.y-shooterY)*(super.y-shooterY));
        if((int)distance > 250) { this.getCloser = true; }
        else if((int)distance < 150) { this.getCloser = false; }
        this.direction = super.x-shooterX <= 0;
        if(shootLaser)
        {
            this.shootTime = System.currentTimeMillis();
            Laser m = new Laser(super.x, super.y, Color.PINK);
            synchronized (Main.gameData.enemyFigures) { Main.gameData.enemyFigures.add(m); }
        }
        if(this.getCloser) // Implement ship image rotation
        {
            if(super.x-shooterX > 0) { super.x = super.x - UNIT_TRAVEL; }
            else { super.x = super.x + UNIT_TRAVEL; }
            if(super.y-shooterY > 0) { super.y = super.y - UNIT_TRAVEL; }
            else { super.y = super.y + UNIT_TRAVEL; }
        }
        else
        {
            int option = (int) (Math.random() * 4);
            switch(option)
            {
                case 0:
                    super.x = super.x + UNIT_TRAVEL;
                    super.y = super.y + UNIT_TRAVEL;
                    break;
                case 1:
                    super.x = super.x + UNIT_TRAVEL;
                    super.y = super.y - UNIT_TRAVEL;
                    break;
                case 2:
                    super.x = super.x - UNIT_TRAVEL;
                    super.y = super.y + UNIT_TRAVEL;
                    break;
                case 3:
                    super.x = super.x - UNIT_TRAVEL;
                    super.y = super.y - UNIT_TRAVEL;
                    break;
            }
        }
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Double(this.x, this.y, 50.0, 50.0);
    }
    
    @Override
    public String getObjectType()
    {
        return "Spaceship Boss";
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
