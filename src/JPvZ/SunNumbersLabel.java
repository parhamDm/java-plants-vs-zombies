/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import java.awt.*;
import javax.swing.*;

/**
 * a simple <code>JLabel</code> which shows amount of sun which user has
 * @author Parham Damavandi
 */
public class SunNumbersLabel extends JLabel {
    //coustom font for our label
    private final Font font = new Font("arial", Font.BOLD, 18);
    /**
     * creates a simpleJLabel as it should
     * @param text is the text user wants to enter to label
     */
    public SunNumbersLabel(String text) {
        super(text);
        setLocation(15, 65);
        setSize(55, 20);
        setFont(font);
        setHorizontalAlignment(SwingConstants.CENTER);
        setAlignmentX(TOP_ALIGNMENT);
        setForeground(Color.BLACK);
    }
}
