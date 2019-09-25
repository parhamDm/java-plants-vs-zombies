/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import javax.swing.ImageIcon;

/**
 * wallnNut is a great defense against zombies whit a lot if health
 *
 * @author Parham Damavandi
 */
public class WallNut extends Plant {
    //health of wallNut
    private final int plantHealth = 12000;

    /**
     * creates a normal WallNut
     * @param x is location(x) of plant
     * @param y is location(y) of plant
     * @param row shows which row plant located in game
     * @param row1 shows which colum plant entered
     */
    public WallNut(int x, int y, int row, int row1) {
        super(x, y, row, row1);
        health = plantHealth;
        plantIcon = new ImageIcon("Images\\PvZ1HDWallNut.png");
    }

}
