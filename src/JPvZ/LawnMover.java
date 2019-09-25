/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JPvZ;

import javax.swing.ImageIcon;

/**
 * Lawn movers are last defense of player when zombies reach it it will kill 
 * all zombies in that row
 * @author Parham Damavandi
 */
public class LawnMover {
    //in defult Lawn is stopped but it will cmove soon
    boolean moved;
    // a smple icon of lawnmover
    ImageIcon icon=new ImageIcon("Images//Lawn_Mower.png");
    //location in game
    int locationX;
    int locationY;
    int row;
    //speed of lawn
    final int lawnSpeed=5;
    //when its dead it will not EXIST ANYMORE
    boolean dead;
    /**
     * setting a lawn mover in garden
     * @param locationY is location which lown is entered
     * @param row  is row number of garden
     */
    public LawnMover(int locationY,int row){
        moved=false;
        //it has not moved
        locationX=0;
        this.locationY=locationY;
        this.row=row;
    }
    /**
     * lawn will move and kills unless it is note in moved state
    */
    public void moveAndKill(){
        if(!moved){
            return;
        }
        
        locationX+=lawnSpeed;
        //whan it exites from lawn it will dissapear and dead
        if(locationX>1000)
            dead=true;
    }
}
