/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import javax.swing.ImageIcon;

/**
 * an snowPea shoots snow to zombies and slows them down
 *
 * @author Parham Damavandi
 */
public class SnowPea extends PeaShooter {

    /**
     *
     * @param x1 is location(x) of plant
     * @param x2 is location(y) of plant
     * @param row shows which row plant located in game
     * @param row1 shows which colum plant entered
     */
    public SnowPea(int x1, int x2, int row, int row1) {
        super(x1, x2, row, row1);
        plantIcon = new ImageIcon("Images\\Snow_Pea_(HD_size).png");
        pea = new ImageIcon("Images\\ProjectileSnowPea.png");
    }

}
