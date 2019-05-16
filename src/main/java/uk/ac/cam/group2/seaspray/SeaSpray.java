package uk.ac.cam.group2.seaspray;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uk.ac.cam.group2.seaspray.search.*;
import uk.ac.cam.group2.seaspray.data.*;

public class SeaSpray extends JFrame {
    private JPanel searchPanel, // search screen
                rootPanel, // home screen
                mainPanel, // main panel, switches between search and root
                headerPanel; // header, always on top

    public SeaSpray() {
        super("SeaSpray");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 640);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        headerPanel = new JPanel();
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 228));
        headerPanel.setBackground(Color.red);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        searchPanel = new SearchPanel(this);

        rootPanel = new JPanel();
        ScrollLayoutManager lm = new ScrollLayoutManager(rootPanel);
        rootPanel.setLayout(lm);
        rootPanel.addMouseListener(lm);
        rootPanel.addMouseMotionListener(lm);



        // CURRENT, HOURLY, WEEKLY data
        double[] latLon = GetData.localWeather();
        loadLocation(latLon[1],latLon[0]);

        add(headerPanel);
        mainPanel.add(rootPanel);
        mainPanel.add(searchPanel);
        add(mainPanel);
    }

    private void switchToSearch() {
        ((CardLayout)mainPanel.getLayout()).next(mainPanel); 
    }

    public void loadLocation(double lon, double lat) { // function called by SearchPanel to return to main screen and takes the selected location as an argument
        // rebuild all components

        ((CardLayout)mainPanel.getLayout()).next(mainPanel);
    }

    public static void main(String[] args) {
        new SeaSpray().setVisible(true);
    }

    private JPanel makePanel(String text) {
        JPanel panel = new JPanel();

        JButton button = new JButton(text);
        panel.add(button, BorderLayout.CENTER);

        button.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
                
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                switchToSearch(); 
            }
        });

        return panel;
    }
}
