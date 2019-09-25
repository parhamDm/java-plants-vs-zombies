/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import javax.swing.ImageIcon;

/**
 * a zombie in this game has features and they are shown below .for example it
 * runs ,walks ,etc ,..
 *
 * @author Parham Damavandi
 */
public class Zombie {

    //a zombie may jump or teleport 
    protected boolean jumped;
    //a defult size of zombie
    final static String size = "50*80";
    //a health that zombie has different zombies have different health
    protected int health;

    //location which zombie 
    public int x;
    int y;
    //width and height of zombie icon
    public int zombieWidth;
    public int zombieHeigth;
    //row of ombie
    public int row;
    //freeze speed
    protected final int freezeSpeed = 1;
    //normal speed of zombie
    private final int normalSpeed = 2;
    //time of freeze state
    private final int goneFreeze = 20;
    //shows freeze state
    private boolean freezState;
    //related to gone freeze
    private int freezeStateTrick;
    //when zombies die it must remove from screen
    public boolean dead;
    //useless

    //going to jump zombie
    private boolean goingToJump;
    //Icon of zombie
    public ImageIcon zombieIcon;
    //teleport state
    protected boolean teleport;
    //zombie may stop walking this boolean shows it
    private boolean stopped;
    //time ti waste
    protected boolean waste;
    int wasteTimer = 10;
    int distance;

    /**
     * making a normal zombie
     *
     * @param x1 is x location of zombie
     * @param x2 is y location of zombie
     * @param rowStart is the row that zombie start walking this number is
     * usually random by game
     */
    public Zombie(int x1, int x2, int rowStart) {
        //setting defult amount
        waste = false;
        stopped = false;
        this.x = x1;
        this.y = x2;
        row = rowStart;
        health = 200;
        dead = false;
        freezState = false;
        freezeStateTrick = 0;
        //defult icon
        zombieIcon = new ImageIcon("Images\\rsz_zombiehd.png");
        zombieWidth =50; //zombieIcon.getIconWidth();
        zombieHeigth =80; //zombieIcon.getIconHeight();
        teleport = false;
    }

    /**
     * zombie may walk slower.its i freeze state and shown below
     */
    public void enterFreezeState() {
        freezState = true;
        freezeStateTrick = 0;
    }

    /**
     * getting state of zombie
     *
     * @return true if zombie is dead
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * this method stops zombie from moving
     */
    public void stopWalking() {
        stopped = true;
    }

    /**
     * when zombie is stopped it will need to walk again .this method will
     * handle it
     */
    public void startWalking() {
        stopped = false;
    }

    /**
     * useless in zombie class but is used in athlete zombie class
     */
    public void jump() {
    }

    /**
     * this is handling movement of zombie.it will call many times 100 times per
     * second
     */
    public void walking() {
        //this is walking of an athlete zombie its better to be in AthleteZombie
        //class but its better to be used here
        if (this instanceof AthleteZombie) {
            /*
             * when athlete zombie reaches a lant it will wait for someseconds 
             * then it will jump.in thes time it will note move.constant variable
             * WAISTTIME shows how much it need to waist
             */
            if (waste && wasteTimer != AthleteZombie.WAISTTIME) {
                wasteTimer++;
                return;
                /*
                 * and if zombie exites from waisting this wil happen
                 * zombie becomes a normal zombie and moves like normal zombie
                 */
            } else {
                wasteTimer = 0;
                waste = false;
            }
            /*
             * jumping of zombie is handled heare.the icon is changed and zombie 
             * becomes normal zombie.
             */
            if (teleport) {
                zombieIcon = new ImageIcon("Images\\PoleVaultingZombie2.png");
                x -= 100;
                teleport = !teleport;
                jumped = true;
            }
        }
        /*
         * if zombie stopped it must not move.and function wont continue...
         */
        if (stopped) {
            return;
        }
        /*
         * freeze state has less speed in comparison with not freeze 
         */
        if (freezState) {
            x -= freezeSpeed;
            freezeStateTrick++;
        } else {
            x -= normalSpeed;
        }
        /*
         * time of freeze is handled here
         * I used variable freezeStateTrick to handle the time of being state
         */
        if (freezeStateTrick > goneFreeze) {
            freezState = !freezState;
            freezeStateTrick = 0;
        }

    }

    /**
     * when a zombie is in freeze state it will eat plants faster than normal
     *
     * @return true if zombie is in freeze state
     */
    public boolean isFreezState() {
        return freezState;
    }
    
    void setDistance(int x) {
    }
    /*
     * note:freeze state is a state which zombie moves slower it dosnt 
     * mean it will stop
     */


}
