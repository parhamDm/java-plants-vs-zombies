/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import static JPvZ.Garden.platforms;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * <p>
 * this class is the second important class.it gets arrays of<code>plants</code>
 * ,<code>zombies</code> and <code>suns</code> and movement of zombies and
 * plants are handled in this class.all actions are checked with one timer.this
 * is a positive feature and game wont be so heavy graphically.but this class is
 * heavy for processor.
 * </p>
 *
 * @author Parham Damavandi
 */
public class MovementAndInteracting {

    //waste timer for athlete zombie 
    int wasteTimer = 0;

    //timer is for how often zombie moves
    int zombieTimer;
    //is suns movement
    int suntimer;
    //array lists which are used for interacting 
    ArrayList<Zombie> zombies;
    ArrayList<Plant> plants;
    ArrayList<Sun> suns;
    ArrayList<LawnMover> mover;
    //defult timer of game
    Timer timer;
    //time for adding sun to Screen
    private AddSunToScreen addSunToScreen;
    //sun speed is speed of sun which comes to S
    int sunSpeed;

    public MovementAndInteracting(ArrayList<Zombie> zombies, ArrayList<Plant> plants,
            ArrayList<Sun> Suns, ArrayList<LawnMover> mover) {
        //generating constructors
        this.zombies = zombies;
        this.plants = plants;
        this.suns = Suns;
        this.mover = mover;
        suntimer = 30;

        addSunToScreen = new AddSunToScreen();
        //set a new timer and what it does
        timer = new Timer(10, (ActionEvent e) -> {
            shootplant();
            zombieWalking();
            sunTimer();
            killZombie();
            killALotOfZombies();
            goingToShootChecker();
            thisIsAboutSun();
            reachingPlant();
            moveOFLown();
            lawnKills();
            Manjanigshooot();
            shootBrother();
        });
        timer.start();
    }

    /**
     * when a zombie reaches plant,we must handle health decrease of plants
     */
    private void reachingPlant() {
        Barkhord b;
        Plant help = null;
        //check all plants and zombies in game
        for (Plant plant : plants) {
            for (Zombie zombie : zombies) {
                /*
                 setting new barkhord
                 note:barkord class is created to decrease helth of plants for
                 by zombies,etc...
                 */
                b = new Barkhord(zombie, plant);
                //zombie eates plant
                b.eating();
                //if plant running out of health we muuuuuuust remove it
                if (plant.dead) {
                    help = plant;
                }
            }
        }
        if (null != help) {
            platforms[help.row1 - 1][help.row - 1].setPlanted(false);
            plants.remove(help);
            safetyDeletePlant(help);
        }
    }

    /**
     * is timer for peaShooters to shoot
     */
    private void shootplant() {
        for (Plant plant : plants) {
            //finding peashooters
            if (plant instanceof PeaShooter) {
                PeaShooter p;
                p = (PeaShooter) plant;
                p.shotingTime();
            }
        }
    }

    /**
     * all zombies walk or sometimes don't this is handled here
     */
    private void zombieWalking() {
        Zombie z = null;
        for (Zombie zombie : zombies) {

            if (zombie.isDead() && !(zombie instanceof ManjanigZombie) && zombie != null) {
                z = zombie;
            }
        }
        safetyDeleteZombie(z);
        fix();
        if (zombieTimer != 15) {
            zombieTimer++;
            return;
        }

        zombieTimer = 0;
        for (Zombie zombie : zombies) {
            zombie.walking();
        }
    }

    /**
     * speed and stopping suns are handled here
     */
    private void sunTimer() {
        if (sunSpeed != 2) {
            sunSpeed++;
            return;
        }
        sunSpeed = 0;
        for (Sun sun : suns) {
            //this method must work continousely for each sun
            sun.howCome();
        }
    }

    /**
     * if zombie health is zero it must die and remove from array of zombies
     */
    private void killZombie() {
        //reaching pea ito zombie is handled heare
        Barkhord b;
        Zombie isGoingToDie = null;
        //we choose peachooters for checking their barkhord
        for (Plant plant : plants) {
            if (!(plant instanceof PeaShooter)) {
                continue;
            }
            //and checkind hitted zombie
            for (Zombie zombie : zombies) {
                b = new Barkhord(zombie, (PeaShooter) plant);
                b.hitCheking();
                if (zombie.isDead()) {
                    isGoingToDie = zombie;
                    Barkhord.eating.stop();
                    Garden.ROW[isGoingToDie.row - 1]--;
                }
            }
            //if a zombie dead we remove it fromm array
            safetyDeleteZombie(isGoingToDie);
        }
    }

    /**
     * when zombie dies it may have some actions such as shooting this method
     * removes zombie icon and wait till zombie actions completed and then
     * removes it from array of zombies
     *
     * @param isGoingToDie is zombie that we want kill
     */
    private void safetyDeleteZombie(Zombie isGoingToDie) {
        //if zombie is null it dosnt need to do anything more
        if (isGoingToDie == null) {
            return;
        }
        /*
         * only manjaniq zombie is our problem.
         * we remove it when he is not in shooting state
         */
        if (!(isGoingToDie instanceof ManjanigZombie)) {
            zombies.remove(isGoingToDie);
            return;
        }
        ManjanigZombie mz = (ManjanigZombie) isGoingToDie;
        //dissapear icon of manjaniq zombie
        if (mz.shoottingnow) {
            mz.zombieIcon = null;
            mz.x = 5000;
        } else {
            zombies.remove(mz);
        }
    }

    /**
     * suicideCerry works!!!!!
     */
    private void killALotOfZombies() {
        Zombie[] areDead = new Zombie[100];
        int i = 0;
        Plant a = null;
        //picking suicide cherry in game
        for (Plant plant : plants) {
            if (!(plant instanceof SuicideCherry)) {
                continue;
            }
            if (!plant.isDead()) {
                continue;
            }
            //killing all around zombies by their position
            for (Zombie zombie : zombies) {
                if (zombie.x > plant.x - 180 && zombie.x < plant.x + 180) {
                    if (zombie.y > plant.y - 140 && zombie.y < plant.y + 120) {
                        areDead[i] = zombie;
                        i++;
                    }
                }
            }
            //kill and removing all zombies from array
            for (int j = 0; j < i; j++) {
                Garden.ROW[areDead[j].row - 1]--;
                areDead[j].dead = true;
                Barkhord.eating.stop();
            }
            if (plant.isDead()) {
                a = plant;
            }
        }
        //finally we must remove plant because its dead
        if (null != a) {
            Garden.platforms[a.row1 - 1][a.row - 1].setPlanted(false);
            plants.remove(a);
        }
    }

    /**
     * a PeaShooter must understand when he is going to shoot
     */
    private void goingToShootChecker() {
        for (int i = 0; i < 5; i++) {
            goingToShootCheckerPlatform(i);
        }
    }

    private void goingToShootCheckerPlatform(int i) {
        //choosing peashooters
        for (Plant plant : plants) {
            if (!(plant instanceof PeaShooter)) {
                continue;
            }
            //shooting in a row
            PeaShooter p = (PeaShooter) plant;
            int t = 0;
            //if all zombies are forwarder than plant 
            for (Zombie zombie : zombies) {
                if (zombie.x > p.x && p.row == zombie.row) {
                    t++;
                }
            }
            if (t == 0) {
                p.removeShootingState();
            } else //if (/*(p.row == 1 + i) && */ROW[p.row-1] > 0) {
            {
                p.setShootingState();
            }
            // }

        }
    }

    /**
     * movement of sun icons (sunEnergy) is handled here
     */
    private void thisIsAboutSun() {
        //first of all we choose sunflowers
        for (Plant plant : plants) {
            if (!(plant instanceof SunFlower)) {
                continue;
            }
            SunFlower help;
            help = (SunFlower) plant;
            //we remove t if its dead
            if (help.sun == null) {
            } else if (help.sun.dead) {
                suns.remove(help.sun);
            } else if (!suns.contains(help.sun)) {
                suns.add(help.sun);
            }
        }
        //this part adds sun to screen every 15 seconds
        if (addSunToScreen.sun == null) {
        } else if (addSunToScreen.sun.dead) {
            suns.remove(addSunToScreen.sun);
        } else if (!suns.contains(addSunToScreen.sun)) {
            suns.add(addSunToScreen.sun);
        }
        Sun help = null;
        for (Sun sun : suns) {
            if (sun.dead) {
                help = sun;
            }
        }
        suns.remove(help);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * movement of lown movers are handled here;
     */
    private void moveOFLown() {
        boolean move = false;
        int row = -1;
        for (Zombie zombie : zombies) {
            if (zombie.x < -10) {
                move = true;
                row = zombie.row;
                for (LawnMover lawn : mover) {
                    if (lawn.row == row) {
                        lawn.moved = true;

                    }
                }
            }
        }

    }

    /**
     * when lawn moves it will kill all zombies in a row
     */
    private void lawnKills() {
        Zombie zz = null;
        LawnMover ll = null;
        for (LawnMover lawn : mover) {
            if (!lawn.moved) {
                //do nothing
                continue;
            }
            if (lawn.dead) {
                //will be removed soon
                ll = lawn;
                continue;
            }
            lawn.moveAndKill();
            for (Zombie z : zombies) {

                if (lawn.row != z.row) {
                    continue;
                }

                if (lawn.locationX > z.x && lawn.locationX < z.x + z.zombieWidth) {
                    zz = z;
                    break;
                }
            }
        }
        safetyDeleteZombie(zz);
        mover.remove(ll);
    }

    /**
     * a manjanig zombie shoots balls to plants. it must understand when it
     * should shoot
     */
    private void Manjanigshooot() {
        /*
         * it must underStand lastposition between plant and shoot them 
         */
        Plant hp = new Plant(1000, 0, 0, 0);
        //chi=ooseing manjanig zombie
        for (Zombie zombie : zombies) {
            if (!(zombie instanceof ManjanigZombie)) {
                continue;
            }
            //casting
            ManjanigZombie mz = (ManjanigZombie) zombie;
            if (mz.shoottingnow) {
                continue;
            }
            /*
             * if s in state of shooting we ignore current zombie now
             * and check next zombie that is manjanig zombie
             */
            for (Plant plant : plants) {
                if (plant instanceof SuicideCherry) {
                    continue;
                }
                if ((plant.row == mz.row)) {
                    //here we find farestplant
                    if (zombie.x < plant.x) {
                        continue;
                    }
                    if (plant.x < hp.x) {
                        hp = plant;
                    }
                    //and concluding distance
                    if (hp.x != 1000) {
                        mz.setDistance(mz.x - hp.x, hp);
                        mz.stopWalking();

                    }
                }
            }
        }
    }

    /**
     * is state of shooting for manjanig zombie. movement of ball is handled
     * here.also dying plant
     */
    private void shootBrother() {
        //first of all choosing zombies we want
        for (Zombie zombie : zombies) {
            if (!(zombie instanceof ManjanigZombie)) {
                continue;
            }
            Plant help = null;
            ManjanigZombie mz = (ManjanigZombie) zombie;
            //and its important to check shooted or not
            int t = 0;
            for (Plant p : plants) {
                if (p instanceof SuicideCherry) {
                    continue;
                }
                if (p.row == mz.row) {
                    t++;
                }
            }
            if (t == 0 && !mz.shoottingnow) {
                mz.startWalking();
            }
            mz.ballsOver();

            if (mz.shoottingnow) {
                // a movement will be checked
                mz.ShootToplant();
            }
            //choosing pants that are in same row to zombie
            for (Plant plant : plants) {
                if (plant instanceof SuicideCherry) {
                    continue;
                }
                if (plant.row != zombie.row) {
                    continue;
                }
                //remove a plant that health is over
                if (plant.health < 1) {
                    help = plant;
                }
                //manjaniqzombie kills on move
                if (plant.x < mz.x && plant.x > mz.x - zombie.zombieWidth) {
                    if (plant.row == mz.row) {
                        help = plant;
                    }
                }

            }
            if (help != null) {
                safetyDeletePlant(help);
                //remove from platforms array
                platforms[help.row1 - 1][help.row - 1].setPlanted(false);

            }
        }
    }

    /**
     * a plant when is dead or removed,it may be in shooting state. we remove it
     * from array when our plant is not shooing
     *
     * @param p is plant we want to remove
     */
    private void safetyDeletePlant(Plant p) {
        walkZombie(p);
        if (!(p instanceof PeaShooter)) {
            plants.remove(p);
            return;
        }
        PeaShooter ps = (PeaShooter) p;
        if (ps.peaX < 900) {
            ps.x = 10000;
            ps.plantIcon = null;
        } else {
            plants.remove(p);
        }
    }

    /**
     * a zombie will walk of no plant is in its way
     *
     * @param plant is plant that it has accrossed with.
     */
    private void walkZombie(Plant plant) {
        for (Zombie zombie : zombies) {
            if (zombie.row != plant.row) {
                continue;
            }
            if (zombie.x > plant.x - 30 && zombie.x < plant.x
                    + plant.plantIcon.getIconWidth() - 20) {
                zombie.startWalking();
            }
        }
    }

    /**
     * this method is created to remove a bug when manjanig zombie is shooting
     * and plant dies ,no problem appear.
     */
    public void fix() {
        int t = 0;
        for (Zombie zombie : zombies) {
            if (zombie instanceof ManjanigZombie) {
                for (Plant p : plants) {
                    if (p.row == zombie.row) {
                        t++;
                    }
                }
                if (t == 0) {
                    zombie.startWalking();
                }
            }
        }
    }
}
