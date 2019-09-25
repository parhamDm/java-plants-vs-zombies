/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * A Sun is an Object which comes to Screen for points user wants to add plant.
 * a user in game needs sun energy.Sun has a timer for coming to screen. it is
 * handled by <code>MovementAndInteracting</code> class. and a dead timer
 *
 * @author Parham Damavandi
 */
public class Sun {

    /**
     * sun icon will be read from file it is public because we need to draw it
     * continoiousely
     */
    public ImageIcon sun = new ImageIcon("Images\\Sun_PvZ2.png");
    private final int disappearTime = 3000;
    /*
     * we have two kind of suns
     * 1.sunflower
     * 2.normal sun that comes to screen
     * the kind is handled here
     */
    private final int howComeToScreen;
    //when sun is dead it will be removed from our array
    public boolean dead;
    //locations of sun whic changes
    //we need it for redroeing so its public
    int x, y;
    private int stopLocation;
    private final int timeToDie = 4000;
    //dead timer klls sun aftre some seconds
    private Timer deadTimer;
    boolean stopped;

    /**
     * adds simple sun object to String
     *
     * @param xx is first x location of sun
     * @param yy is first y location of sun
     * @param kind is kind of sun
     */
    public Sun(int xx, int yy, int kind) {
        x = xx;
        y = yy;
        dead = false;
        howComeToScreen = kind;
        stopLocation = 0;
        //choosein a random number where plant stop
        if (kind == 1) {
            Random rand = new Random();
            int n = rand.nextInt(700) + 1;
            x = n;
            y = 0;
            this.stopLocation = rand.nextInt(500) + 100;
        }

        deadTimer = new Timer(10, (ActionEvent e) -> {
            dead = true;
            deadTimer.stop();
        });
    }

    /**
     * this method will be called every 1/100 seconds by Movement class
     */
    public void howCome() {
        //first of all we must understand what type plant is 
        //the movement of different types are varies
        switch (howComeToScreen) {
            case 0:
                //sunflower's sun will stop very soon
                //and moves in a line
                if (stopLocation > 20) {
                    deadTimer.setInitialDelay(disappearTime);
                    deadTimer.start();
                } else {
                    y += 2;
                    x--;
                    stopLocation++;
                }
                break;
            case 1:
                /* the normal sun will come to screen in a line
                 * and stops in a particular place which is randomally chosen                  
                 */
                if (stopLocation < y) {
                    if (!deadTimer.isRunning()) {
                        deadTimer.setInitialDelay(disappearTime);
                        deadTimer.start();
                    }
                } else {
                    y += 2;
                }
        }
    }
}
