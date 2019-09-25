/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * <code> SuicideCherry</code> suicides to save player's life!!<br/>
 * and kill all zombies which are around itself.its time to die can de changed
 * by developer
 *
 * @author Parham Damavandi
 */
public class SuicideCherry extends Plant {

    // a simple timer for delaying health 
    private Timer deadTimer;
    private final int bangDelay = 1500;
    //is create for handling time of sound
    private int kalakRashti;
    //its boom has a sound
    private SoundPlayer sp;

    /**
     * creating a normal suicide cherry
     *
     * @param x is location(x) of plant
     * @param y is location(y) of plant
     * @param row shows which row plant located in game
     * @param row0 shows which colum plant entered
     */
    public SuicideCherry(int x, int y, int row, int row0) {
        super(x, y, row, row0);
        kalakRashti = 1;
        sp = new SoundPlayer("startToboom");
        sp.play();
        deadTimer = new Timer(10, (ActionEvent e) -> {
            kalakRashti = 0;
            deadTimer.stop();
        });
        deadTimer.setInitialDelay(bangDelay);
        deadTimer.start();
        plantIcon = new ImageIcon("Images\\CerryBomb.png");
    }

    /**
     * if our plant is dead a sound will play
     *
     * @return true if plant is dead else it will return false
     */
    @Override
    public boolean isDead() {
        if (kalakRashti == 0) {
            deadTimer = null;
            sp = new SoundPlayer("boom");
            sp.play();
            return true;
        }
        return false;
    }

}
