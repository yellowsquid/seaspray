package uk.ac.cam.group2.seaspray.search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import uk.ac.cam.group2.seaspray.*;
import uk.ac.cam.group2.seaspray.data.*;

public class SearchPanel extends JPanel {
    private List<Location> recents;
    private JTextField searchBar;
    private JButton searchButton;
    private JPanel entries;
    private JLabel errorLabel;
    private int verticalSpace = 50;
    private int cap = 5;
    private SeaSpray source;

    public SearchPanel(SeaSpray ss) {
        // initialization
        source = ss;

        errorLabel = new JLabel();
        JPanel errorPanel = new JPanel(); // panel containing the error label
        errorPanel.add(errorLabel);
        errorPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        errorPanel.setBackground(Color.white);

        recents = new LinkedList<>();
        searchBar = new JTextField();
        searchButton = new JButton();

        entries = new JPanel();
        entries.setLayout(new BoxLayout(entries, BoxLayout.PAGE_AXIS));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel searching = new JPanel();
        searching.setBackground(Color.white);

        searchBar.setFont(new Font(searchBar.getFont().getFontName(), Font.PLAIN, 25));

        // changing search button appearance
        searchButton.setText("Search");
        searchButton.setMaximumSize(new Dimension(50, 50));
        searchButton.setBackground(Color.decode("#72C7EC"));

        // make button search when clicked
        searchButton.addActionListener(e -> search());

        // add everything search-related to the searching panel
        searching.setLayout(new BoxLayout(searching, BoxLayout.LINE_AXIS));
        searching.add(searchBar);
        searching.add(searchButton);
        searching.setMaximumSize(new Dimension(250, 50));

        // add everything together
        this.add(Box.createVerticalStrut(verticalSpace));
        this.add(searching);
        this.add(errorPanel);
        this.add(Box.createVerticalStrut(verticalSpace));
        this.add(entries);

        this.setBackground(Color.white);
    }

    private void display(List<Location> info) {
        entries.removeAll();

        // if there are no entries to display, return
        if (info.size() == 0) {
            return;
        }

        // make entries clickable, selecting their specific location
        for (Location l : info) {
            SearchEntry e = new SearchEntry(l);

            e.addMouseListener(
                    new MouseListener() {
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

        // redraw the panel
        this.revalidate();
        this.repaint();
    }

    // revert to the recents view
    public void reset() {
        display(recents);
    }

    // remove a location if it is in recents
    private void removeRecent(String s) {
        List<Location> toRemove = new LinkedList<>();

        for (Location l : recents) {
            if (l.toString().equals(s)) {
                toRemove.add(l);
            }
        }

        for (Location l : toRemove) {
            recents.remove(l);
        }
    }

    // selects a location, modifies recents accordingly
    private void select(Location l) {
        System.out.println("selected " + l.toString());

        removeRecent(l.toString());

        if (recents.size() == cap) { // if the recents list reaches the cap, remove the last one
            recents.remove(cap - 1);
        }
        recents.add(0, l); // add the new selection to the recents list

        source.loadLocation(l);

        // go back to showing the recent list
        reset();
    }

    // searches for a location, shows results
    private void search() {
        String searchPrompt = searchBar.getText();
        List<Location> results;
        errorLabel.setText(""); // clear the error output from the last time

        if (searchPrompt.equals("")) { // The user has not entered anything
            errorLabel.setText("Please enter something in the search bar!");
            return;
        } else {
            System.out.println("Searching for " + searchPrompt);
            results = GetData.getPlaces(searchPrompt);

            if (results.size() == 0) { // the search query did not return any results
                results = recents;
                errorLabel.setText("No results found!");
            } else { // logs the found results to the console
                System.out.println("Found results:");
                for (Location l : results) {
                    System.out.println(l);
                }
            }
        }

        display(results);
    }
}
