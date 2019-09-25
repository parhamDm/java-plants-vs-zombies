/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import javax.swing.ImageIcon;

/**
 * an AthleteZombie will jump on first plant which encountered 
 * @author Parham Damavandi
 */
public class AthleteZombie extends Zombie {
    //a time wich wastes and then zombie jumpes
    static final int WAISTTIME=10;

    public AthleteZombie(int x1, int x2, int rowStart) {
        super(x1, x2, rowStart);
        jumped = false;
        zombieIcon = new ImageIcon("Images\\HD_Polevaulterzombie.png");
        health = 400;
    }
    /**
     * after jumping zombie will be teleported and cant be jumped again and 
     * its icon will be changed 
     */
    public void jump() {
        jumped = true;
        teleport = true;
        waste = true;
    }
}
