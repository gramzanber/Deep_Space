// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     November 15, 2015

package controller;

import java.io.File;
import java.io.IOException;
//import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundController 
{
    public void backgroundMusic()
    {
        try
        {
            File file = new File("src\\Sounds\\bgm_hyperspace.wav");
            //URL url = this.getClass().getClassLoader().getResource("explosion.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void fireLaser()
    {
        try
        {
            File file = new File("src\\model\\bgm_hyperspace.wav");
            //URL url = this.getClass().getClassLoader().getResource("explosion.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void fireMissile()
    {
        try
        {
            File file = new File("src\\model\\bgm_hyperspace.wav");
            //URL url = this.getClass().getClassLoader().getResource("explosion.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void explosion()
    {
        try
        {
            File file = new File("src\\model\\bgm_hyperspace.wav");
            //URL url = this.getClass().getClassLoader().getResource("explosion.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
}