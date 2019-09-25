/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import javax.swing.ImageIcon;

/**
 * this zombie shoot balls to plants and reduce plants health
 *
 * @author Parham Damavandi
 */
public class ManjanigZombie extends Zombie {

    //icon of zombie
    public ImageIcon ball = new ImageIcon("Images//basketball.png");
    //location z of ball
    public double ballX = 0;
    //location y of bsll
    public int ballY = 0;
    //numbers of balls which zombie has
    public int balls = 10;
    //first ztop location of zombie
    int firstStop = 800;
    //whan zombie has enogh balls he his in shooting state 
    private boolean shootingState;
    /* when a ball is coming to the zombie
     * zombie is in shooting state
     */
    boolean shoottingnow;
    //this is the pant which zombie is going to kill
    Plant diePlant;
    //amount of damage that a ball has on a certain plant
    private final int healthDecrease = 500;

    /**
     * making a manjanig zombie
     *
     * @param x1 is x location of zombie
     * @param x2 is y location of zombie
     * @param rowStart is the row that zombie start walking this number is
     * usually random by game
     */
    public ManjanigZombie(int x1, int x2, int rowStart) {
        super(x1, x2, rowStart);
        zombieIcon = new ImageIcon("Images//Pvzas_gw_catapult.png");
        k = 0;
        health = 500;
        shootingState = true;
    }

    /**
     * a zombie my stop walking this zombie stops walking when it have a plants
     * across itself
     */
    @Override
    public void stopWalking() {

        if (kk != 100) {
            kk++;
            return;
        }
        //if he dosnt runnig out of balls 
        kk = 0;
        if (x < firstStop && balls > 0) {
            if (shootingState) {
                super.stopWalking();
                ballX = x + 100;
                ballY = y + 10;
            }
            shoottingnow = true;
        }
    }
    //speed and time
    int k, kk;
    int vx, vy;
    /**
     * when balls are over zombie never stops
     */
    public void ballsOver() {
        if (balls == 0) {
            startWalking();
            shootingState = false;
        }
    }
    /**
     * when a zombie face a plant shoots to selected plant
     */
    public void ShootToplant() {

        if (k != 3) {
            k++;
            return;
        }
        //setting ampunt
        k = 0;
        ballX -= vx / 2;
        ballY -= vy / 2;
        vy -= 1;

        if (ballY > y + 10) {
            diePlant.health -= healthDecrease;
            shoottingnow = false;
            ballX = -100;
            ballY = -100;
            balls--;
        }
    }
    /**
     * we must concluding distance between zombie and plant and guess VX and VY 
     * @param x is distance between plant and zombie
     * @param plant is the plant which is going to die 
     */
    public void setDistance(int x, Plant plant) {
        distance = x + 30;
        vx = (distance) / 25;
        vy = 28;
        diePlant = plant;
    }
}
