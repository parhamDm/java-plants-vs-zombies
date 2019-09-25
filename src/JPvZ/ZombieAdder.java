/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.MediaPlayer;
import javax.swing.Timer;

/**
 * <p>
 * ZombieAdder adds zombie in garden based on levelNo.<br/>
 * it reads from a file in <code>directory:levelDesign</code>. each line is a
 * command of adding new zombie.contains two parts first is time for adding
 * zombie second is kind of zombie. for example i want to add normal zombie in
 * 1st second.i type this 
 * <code>10-0<code>
 * </p>
 * <p>
 * the file finishes in this format<br/>
 * "TimeYouWANTToFinish"-99999<br/>
 *
 * @author Parham Damavandi<br/>
 * all rights reserved
 */
public class ZombieAdder {

    //i need a reader to read line by line
    private BufferedReader abc;
    //a timer for checkig we mus add zombie or not
    private Timer timer;
    //is Current time per seconds
    private int stopWatch;
    //and garden for adding to
    private Garden garden;

    /**
     * constructor of making a file checker and levelDesigner and a timer
     *
     * @param garden is the field we are working on
     * @param levelNumber gets level file
     */
    public ZombieAdder(Garden garden, int levelNumber) {
        this.garden = garden;
        stopWatch = 0;
        String number = levelNumber + "";
        File myfile = new File("levelDesign//level" + number + ".txt");
        //loading file and sending it to buffer reader
        try {
            abc = new BufferedReader(new FileReader(myfile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ZombieAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> lines = new ArrayList<String>();
        String line = null;
        while (true) {
            try {
                //reading line by line
                line = abc.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ZombieAdder.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (line == null) {
                break;
            }
            lines.add(line);
        }
        //generating new number
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //we read each String and we check it is time toadd or not
                stopWatch += 5;
                for (String list : lines) {
                    String[] parts = list.split("-");
                    int part1 = Integer.parseInt(parts[0]);
                    int part2 = Integer.parseInt(parts[1]);
                    //this line command checks if user wants to finish level or not
                    if (part2 == 99999 && part1 == stopWatch) {
                        garden.itsOver = true;
                        //its important to stop timer
                        timer.stop();
                        return;
                    }
                    //addzombie
                    if (part1 == stopWatch) {
                        garden.addZombie(part2);
                    }
                }
            }
        });
        //starting number
        timer.start();
    }

    public Timer getTimer() {
        return timer;
    }

}
