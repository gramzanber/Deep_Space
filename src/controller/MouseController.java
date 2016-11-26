// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import model.Laser;
import model.Missile;
import model.Shooter;

public class MouseController extends MouseAdapter {

    private int px;
    private int py;

    @Override
    public void mousePressed(MouseEvent me)
    {
        px = me.getX();
        py = me.getY();

        Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);
        
        if(SwingUtilities.isRightMouseButton(me))
        {
            Missile m = new Missile(shooter.getXofMissileShoot(), shooter.getYofMissileShoot(), px, py, Color.RED);
            synchronized (Main.gameData.friendFigures) { Main.gameData.friendFigures.add(m); }
        }
        else if(SwingUtilities.isLeftMouseButton(me))
        {
            Laser m = new Laser(shooter.getXofMissileShoot(), shooter.getYofMissileShoot(), Color.RED);
            synchronized (Main.gameData.friendFigures) { Main.gameData.friendFigures.add(m); }
        }
        else { System.out.printf("Mouse click error, Package: Controller; Class: MouseController.java \n"); }
    }
}
