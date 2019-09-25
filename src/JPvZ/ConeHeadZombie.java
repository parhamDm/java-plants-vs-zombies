/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JPvZ;

import javax.swing.ImageIcon;

/**
 * <code>ConeHeadZombie</code> have more health than normal zombie.
 * @author Parham Damavandi
 */
public class ConeHeadZombie extends Zombie{
    /**
     * creates a cone head zombie as usual but with more health
     * @param x1 is x location of zombie
     * @param x2 is y location of zombie
     * @param rowStart is the row that zombie start walking this number is
     * usually random by game
     */
    public ConeHeadZombie(int x1, int x2, int rowStart) {
        super(x1, x2, rowStart);
        health=300;
        //icon is different
        zombieIcon=new ImageIcon("Images//HD_Conehead_Zombie.png");
    }
}
