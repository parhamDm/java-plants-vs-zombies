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
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Parhamdamavandi
 */
public class MenuPanel extends JPanel {

    Timer timer;
    JButton start;
    JButton exit;
    ImageIcon backGround = new ImageIcon("Images//plants_vs__zombies-wide.jpg");
    MainWindow mv;

    public MenuPanel(MainWindow mv) {
        this.mv = mv;
        setSize(1123, height);
        setLocation(0, 0);

        setLayout(null);
        start = new JButton("دفاع از زندگی!!");
        exit = new JButton("فرار کردن!!!");
        start.setSize(200, 60);
        exit.setLocation(360, 370);
        exit.setSize(200, 60);
        start.setLocation(360, 300);
        exit.setBackground(new Color(0, 173, 76));
        exit.setFont(new Font("nazanin", Font.BOLD, 30));
        start.setBackground(new Color(0, 173, 76));
        start.setFont(new Font("nazanin", Font.BOLD, 30));
        start.setForeground(Color.white);
        exit.setForeground(Color.white);
        add(start);
        add(exit);
        start.addActionListener((ActionEvent e) -> {
            mv.state = "LEVEL";
            timer.stop();
            mv.switchState();
        });
        exit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        timer = new Timer(10, (ActionEvent e) -> {
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround.getImage(), 0, 0, backGround.getImageObserver());
    }

}
