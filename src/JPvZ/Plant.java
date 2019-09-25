/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import javax.swing.ImageIcon;

/**
 *
 * @author Parham
 */
public class Plant {
    //location of plant for drawing
    int x, y;
    //the row 
    int row;
    int row1;//coloum
    int health;
    boolean dead;
    ImageIcon plantIcon;
    /**
     * 
     * @param x is location(x) of plant 
     * @param y is location(y) of plant
     * @param row shows which row plant located in game 
     * @param row1 shows which colum plant entered 
     */
    public Plant(int x, int y,int row,int row1) {
        this.row=row;
        this.row1=row1;
        this.x = x;
        this.y = y;
        dead=false;
        health=1000;
    }
    public boolean isDead(){
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public ImageIcon getPlantIcon() {
          return plantIcon;
    }

    public void dissapearPea(){
    }
    public void startShooting(){
    }

    void notContinueShooting() {
    }
}
