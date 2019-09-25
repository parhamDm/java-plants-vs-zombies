/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

/**
 * this class is created for interacting between plants and zombies. thing like
 * shooting and hitting between zombies and plants will handle here
 *
 * @author Parham Damavandi
 */
public class Barkhord {

    public static SoundPlayer eating = new SoundPlayer("eating");
    ;
    Plant plant;
    Zombie zombie;

    /**
     * gets a zombie and plant.and save them
     * @param zom is a zombie we are checking
     * @param plant is a plant we are checking
     */
    public Barkhord(Zombie zom, Plant plant) {
        zombie = zom;
        this.plant = plant;
    }

    /**
     * checks shoot of a peashooter to zombie
     *
     * @return true if shooting removes 
     */
    public boolean hitCheking() {
        PeaShooter plant = (PeaShooter) this.plant;
        if (plant.peaX > zombie.x && plant.peaX < zombie.zombieWidth + zombie.x) {
            if (plant.row == zombie.row) {
                zombie.health -= 25;
                plant.dissapearPea();
                if (plant instanceof SnowPea) {
                    zombie.enterFreezeState();
                }
                if (zombie.health < 1) {
                    zombie.dead = true;
                    plant.shootingState = false;
                    return false;
                }
            }
        }
        return false;
    }

    public boolean eating() {
        if(plant.plantIcon==null){
            return false;
        }
        if (plant instanceof SuicideCherry) {
            return false;
        }
        if (zombie.x > plant.x - 20 && zombie.x < plant.x + plant.plantIcon.getIconWidth() - 20) {
            if (zombie.row == plant.row) {
                if ((zombie instanceof AthleteZombie) && !zombie.jumped) {
                    zombie.jump();
                }
                if (zombie.teleport) {
                    return false;
                }
                zombie.stopWalking();
                //is freeze or not
                if (zombie.isFreezState()) {
                    plant.health -= 1;
                } else {
                    plant.health -= 3;
                }
                if (!eating.isPlaying()) {
                    eating.repetivePlay();
                    eating.play();
                }
               
                if (plant.health < 100) {
                    plant.dead = true;
                    zombie.startWalking();
                    eating.stop();
                    return true;
                }
            }
        }
        return false;
    }
}
