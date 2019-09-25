/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import javax.swing.JFrame;

/**
 * inputs
 *
 * @author Psprhspm Dspmspvspndi
 */
public class MainWindow extends JFrame {

    // state where user enters 
    public String state;
    //resolation of game
    public static final int width = 933, height = 720;
    //oh... we have five levels its important which level we are in
    public static int LEVELNUMBER = 5;
    //a simpel garden for one of states
    private Garden garden;
    // sp class which addes zombie base of user inputs
    private ZombieAdder zombieAdder;
    //game overpanel is one of states in game
    private GameOverPanel gameoverPanel;
    //menu panel is one of states in game
    private MenuPanel menuPanel;

    //plays music of game repetively
    SoundPlayer sp;

    public MainWindow() {
        //setting defult variables
        setSize(width, height);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        //first state is menu
        state = "MENU";
        //switchState();
        menuPanel = new MenuPanel(this);
        add(menuPanel);
    }

    /**
     * switch state changes state in game its similar to gameLoop
     */
    public void switchState() {
        getContentPane().removeAll();
        repaint();
        garden = null;
        gameoverPanel = null;
        menuPanel = null;
        switch (state) {
            //this shows menu
            case "MENU":
                LEVELNUMBER = 1;
                menuPanel = new MenuPanel(this);
                add(menuPanel);
                break;
            //this slows gameOverPanel
            case "GAMEOVER":
                gameoverPanel = new GameOverPanel(this);
                getContentPane().add(gameoverPanel);
                break;
            //this goes to next level    
            case "LEVEL":
                if (LEVELNUMBER == 6) {
                    LEVELNUMBER = 1;
                    state = "MENU";
                    this.switchState();
                    return;
                }
                garden = new Garden(this);
                getContentPane().add(garden);
                //set level
                zombieAdder = new ZombieAdder(garden, LEVELNUMBER);
                LEVELNUMBER++;
                //game will finish if player is in last level and directed to menu

                break;
        }
    }
}
