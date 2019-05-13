package uk.ac.cam.group2.seaspray;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SeaSpray extends JFrame {
    public SeaSpray() {
        super("SeaSpray");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 640);
        JPanel rootPanel = new JPanel();
        ScrollLayoutManager lm = new ScrollLayoutManager(rootPanel);
        rootPanel.setLayout(lm);
        rootPanel.addMouseListener(lm);
        rootPanel.addMouseMotionListener(lm);
        rootPanel.add(makePanel("Hi"));
        rootPanel.add(makePanel("Hello"));
        rootPanel.add(makePanel("Bye"));
        add(rootPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new SeaSpray().setVisible(true);
    }

    private static JPanel makePanel(String text) {
        JPanel panel = new JPanel();
        panel.add(new JButton(text), BorderLayout.CENTER);
        return panel;
    }
}
