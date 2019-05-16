package uk.ac.cam.group2.seaspray.widget;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    private JButton leftButton;
    private JButton rightButton;
    private JLabel locationName;

    private final Color buttonColor = Color.decode("#afeeee");
    private final Color headerColor = Color.decode("#189BD3");

    public Header (JButton left, JButton right, String text)  {
        leftButton = left;
        rightButton = right; 

        // put the text in the location label
        locationName = new JLabel(text);
        locationName.setHorizontalAlignment(JLabel.CENTER);

        // create a panel to contain the name label
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BorderLayout());
        namePanel.setBackground(headerColor);
        namePanel.add(locationName, BorderLayout.CENTER);

        leftButton.setBackground(buttonColor);
        rightButton.setBackground(buttonColor);

        leftButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        rightButton.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));

        // fix the same maximum square dimension for both buttons
        Dimension maxDim = new Dimension(50, 50);
        leftButton.setPreferredSize(maxDim);
        rightButton.setPreferredSize(maxDim);

        // add all components to the header
        setLayout(new BorderLayout());
        add(leftButton, BorderLayout.WEST);
        add(namePanel, BorderLayout.CENTER);
        add(rightButton, BorderLayout.EAST);
        
        this.setBackground(headerColor);
        setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
    }
}
