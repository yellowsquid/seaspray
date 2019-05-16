package uk.ac.cam.group2.seaspray.widget;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    private JButton l;
    private JButton r;
    private JLabel txt;

    public Header (JButton left, JButton right, String text)  {
        l = left;
        r = right;
        txt = new JLabel(text);
        txt.setHorizontalAlignment(JLabel.CENTER);

        int height = 128;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.1;
        c.fill = GridBagConstraints.BOTH;
        add(l,c);

        c.weightx = 0.8;
        c.gridx = 1;
        add(txt,c);

        c.weightx = 0.1;
        c.gridx = 2;
        add(r,c);
        setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
    }

    public void updateText(String text){
        txt.setText(text);
    }

    public void update(JButton left, JButton right, String text){
        l = left;
        r = right;
        txt.setText(text);
    }
}
