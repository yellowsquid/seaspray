package uk.ac.cam.group2.seaspray;

import javax.swing.JFrame;

public class SeaSpray extends JFrame {
    public SeaSpray() {
        super("SeaSpray");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(576, 1024);
    }

    public static void main(String[] args) {
        new SeaSpray().setVisible(true);
    }
}
