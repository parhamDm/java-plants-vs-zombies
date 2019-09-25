/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import java.awt.event.*;
import javax.swing.*;

/**
 * <code>SunFlower</code> adds sun to screen in determined times. developer can
 * change its time.
 *
 * @author Parham Damavandi
 */
public class SunFlower extends Plant {

    public final static int TimeOfSun = 15000;
    Timer shootSun;
    boolean haveSun;
    Sun sun;
    /**
     * creating sunFlower base on its location
     * @param x is location(x) of plant 
     * @param y is location(y) of plant
     * @param row shows which row plant located in game 
     * @param row1 shows which colum plant entered 
     */
    public SunFlower(int x, int y, int row, int row1) {
        super(x, y, row, row1);
        plantIcon = new ImageIcon("Images\\sunflower.png");
        haveSun = false;
        sun = null;
        
        shootSun = new Timer(TimeOfSun, (ActionEvent e) -> {
            sun = new Sun(x, y, 0);
        });
        shootSun.setInitialDelay(1500);
        shootSun.start();
    }

}
