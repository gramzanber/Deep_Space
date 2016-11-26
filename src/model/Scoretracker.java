// Author:	Tyrel Tachibana
// Course:	CMSC 3103 - Object Oriented SW Design & Construction
// Semester:    Fall, 2015
// Project:	Term Project
// Created:     October 31, 2015

package model;

public class Scoretracker 
{
    private int score;
    
    public Scoretracker()
    {
        this.score = 0;
    }
    
    public void addToScore(int points)
    {
        this.score = this.score + points; 
    }
    
    public int getScore()
    {
        return this.score;
    }
}