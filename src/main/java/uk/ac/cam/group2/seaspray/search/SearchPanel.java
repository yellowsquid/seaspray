package uk.ac.cam.group2.seaspray.search;

import uk.ac.cam.group2.seaspray.data.*;
import uk.ac.cam.group2.seaspray.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

//TODO: CHANGE BUILD.GRADLE BACK

public class SearchPanel extends JPanel {
    private List<Location> recents;
    private JTextField searchBar;
    private JButton searchButton;
    private JPanel entries;
    private int verticalSpace = 50,
                cap = 7;
    private SeaSpray source;

    public SearchPanel(SeaSpray ss) {
        source = ss;

        recents = new LinkedList<Location>();
        searchBar = new JTextField();
        searchButton = new JButton();
        entries = new JPanel();
        entries.setLayout(new BoxLayout(entries, BoxLayout.PAGE_AXIS));

        display(recents);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel searching = new JPanel();

        searchBar.setFont(new Font(searchBar.getFont().getFontName(), Font.PLAIN, 25));
        searchButton.setText("Search");

        searchButton.addMouseListener(new MouseListener(){
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
        
            @Override
            public void mouseClicked(MouseEvent e) {
                search();                
            }
        });

        searching.setLayout(new BoxLayout(searching, BoxLayout.LINE_AXIS));
        searching.add(searchBar);
        searching.add(searchButton);
        searching.setMaximumSize(new Dimension(250, 50));

        this.add(Box.createVerticalStrut(verticalSpace));
        this.add(searching);
        this.add(Box.createVerticalStrut(verticalSpace));
        this.add(entries);
    }

    public void display(List<Location> info) {
        entries.removeAll();

        if(info.size() == 0) {
            return;
        }

        for(Location l : info) {
            SearchEntry e = new SearchEntry(l);

            e.addMouseListener(new MouseListener(){
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
            
                @Override
                public void mouseClicked(MouseEvent e) {
                    select(l);                
                }
            });

            entries.add(e);
        }
        this.revalidate();
        this.repaint();
    }

    // revert to the recents view
    public void reset() {
        display(recents);
    }

    // selects a location, modifies recents accordingly
    public void select(Location l) {
        System.out.println("selected " + l.toString());

        if(recents.contains(l)) {
            recents.remove(l);
        }
        if(recents.size() == cap) {
            recents.remove(cap - 1);
        }
        recents.add(0, l);

        source.switchFromSearch(l);

        reset();
    }

    // searches for a location, shows results
    public void search() {
        System.out.println("Searching for " + searchBar.getText());
        List<Location> results = GetData.getPlaces(searchBar.getText());

        System.out.println("Found results:");
        for(Location l : results) {
            System.out.println(l);
        }

        display(results);
    }

    // public static void main(String[] args) {
    //     JFrame bigFrame = new JFrame();
    //     bigFrame.add(new SearchPanel());
    //     bigFrame.setSize(360, 640);
    //     bigFrame.setVisible(true);
    // }
}