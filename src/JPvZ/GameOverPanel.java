/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import static JPvZ.MainWindow.height;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * when the player looses this panel is replaced by garden
 *
 * @author ParhamDamavandi
 */
public class GameOverPanel extends JPanel {

    JButton readey;
    JButton backToMenu;
    Timer timer;

    /**
     * setting a game over panel with normal features
     */
    public GameOverPanel(MainWindow mv) {
        setSize(1123, height);
        setLocation(0, 0);
        setBackground(Color.BLACK);
        readey = new JButton("تلاش مجدد");
        backToMenu = new JButton("بازگشت به منو");
        readey.setSize(200, 60);
        backToMenu.setLocation(230, 600);
        backToMenu.setSize(200, 60);
        readey.setLocation(500, 600);
        backToMenu.setBackground(Color.BLACK);
        backToMenu.setFont(new Font("nazanin", Font.BOLD, 30));
        readey.setBackground(Color.BLACK);
        readey.setFont(new Font("nazanin", Font.BOLD, 30));
        readey.setForeground(Color.white);
        backToMenu.setForeground(Color.white);
        add(readey);
        add(backToMenu);
        readey.addActionListener((ActionEvent e) -> {
            MainWindow.LEVELNUMBER--;
            mv.state = "LEVEL";
            mv.switchState();
        });
        backToMenu.addActionListener((ActionEvent e) -> {
            MainWindow.LEVELNUMBER--;
            mv.state = "MENU";
            mv.switchState();
        });
        //play an Special sound whan game overs
        SoundPlayer a = new SoundPlayer("gameover2");
        a.play();
        timer = new javax.swing.Timer(10, (ActionEvent e) -> {
            repaint();
        });
        timer.start();
    }
    ImageIcon zombies = new ImageIcon("Images//theyKillu.png");

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(zombies.getImage(), 0, 0, null);

    }
}
