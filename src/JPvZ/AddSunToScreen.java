/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * <code>AddSunToScreen</code> is an object which have a timer and it adds sun 
 * to screen base on sunAddTimer.developer can change it continously.
 * @author Parham Damavandi
 */
public class AddSunToScreen {
    //a simple timer and its not hard for cpu
    private final Timer timer;
    //sun Icon which we want to add
    /**
     * sun Object of the game
     * .its movement is determined in <code>Sun</code> object
     */
    Sun sun;
    
    private final int sunAddTime=10000;
    public AddSunToScreen() {
        //in Start our object is null
        sun = null;
        timer = new Timer(sunAddTime, (ActionEvent e) -> {
            sun = new Sun(0, 0, 1);
        });
        timer.setInitialDelay(2000);
        timer.start();
    }
}
