/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import javax.swing.ImageIcon;

/**
 * PeaShooter class .a peaShooter object shoot peas to zombies
 *
 * @author Parham Damavandi
 */
public class PeaShooter extends Plant {

    //ImageIcon pea;
    ImageIcon pea = new ImageIcon("Images\\ProjectilePea.png");

    public int peaX, peaY;
    boolean shootingState;
    /**
     *
     * @param x1 is location(x) of plant
     * @param x2 is location(y) of plant
     * @param row shows which row plant located in game
     * @param row1 shows which colum plant entered
     */
    public PeaShooter(int x1, int x2, int row, int row1) {
        super(x1, x2, row, row1);
        plantIcon = new ImageIcon("Images\\asasas.png");
        peaX = -100;
        peaY = -100;
    }

    /**
     * setting peaShooter in shooting state
     */
    public void setShootingState() {
        shootingState = true;
    }

    /**
     * remove shooting state of plant and it will no longer shoot
     */
    public void removeShootingState() {
        if ((peaX > 1000 && peaX < 1100) || peaX > 4000) {
            shootingState = false;
        }
    }

    /**
     * disappears a pea
     */
    @Override
    public void dissapearPea() {
        peaY = -1000;
        peaX = 3000 + peaX;
    }
    boolean dis;

    /**
     * in <code>MovementAndInteracting</code> class this method will be called
     * 100 times every second
     */
    public void shotingTime() {
        /*
         * if no zombie will be faced with plant is not in state of shooting.
         * and it must not shoot and THE pea must not be seen
         */
        if (!shootingState) {
            peaX = -100;
            peaY = -100;
            return;
        }

        /*
         * if plant comes to shooting state the defult pea location is here
         * shown
         */
        if (peaX < 0) {
            peaX = x + 40;
            peaY = y + 15;
            return;
        }
        /*
         * this is a simple timer for peaShooter to shoot
         */
        if (peaX > 4000 + x) {
            peaX = x + 40;
            peaY = y + 15;
            return;
        }

        if (peaX > 1320 && peaX < 1500) {
            shootingState = false;
            peaX = -100;
            return;
        }
        peaX = peaX + 4;
        peaY = y + 15;
    }
}
