package uk.ac.cam.group2.seaspray.widget;

import java.awt.*;
import javax.swing.*;

public class Header extends JPanel {
    private IconButtonWidget leftButton;
    private IconButtonWidget rightButton;
    private JLabel locationName;

    private final Color buttonColor = Color.decode("#afeeee");
    private final Color headerColor = Color.decode("#189BD3");

    public Header(IconButtonWidget left, IconButtonWidget right, String text) {
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

        leftButton.setBackground(headerColor);
        rightButton.setBackground(headerColor);

        // fix the same maximum square dimension for both buttons
        Dimension maxDim = new Dimension(48, 48);
        leftButton.setPreferredSize(maxDim);
        rightButton.setPreferredSize(maxDim);

        // add all components to the header
        setLayout(new BorderLayout());
        add(leftButton, BorderLayout.WEST);
        add(namePanel, BorderLayout.CENTER);
        add(rightButton, BorderLayout.EAST);

        this.setBackground(headerColor);
    }
}
