package uk.ac.cam.group2.seaspray;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.UncheckedIOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.ac.cam.group2.seaspray.search.*;
import uk.ac.cam.group2.seaspray.data.*;
import uk.ac.cam.group2.seaspray.widget.*;


public class SeaSpray extends JFrame {
    private SearchPanel searchPanel; // search screen
    private JPanel rootPanel; // home screen
    private JPanel mainPanel; // main panel, switches between search and root
    private JPanel headerPanel; // panel containing the header, always on top
    private JPanel header; // actual header inside the panel

    // header buttons
    private IconButtonWidget getLocationButton;
    private IconButtonWidget goSearchButton;
    private IconButtonWidget returnButton;

    // current location information
    private double[] currentCoords; // latitude, longitude
    private String locationName;

    public SeaSpray() {
        super("SeaSpray");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 640);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        // header button functionality
        try {
            getLocationButton = new IconButtonWidget("src/main/resources/map.png",
                                                     this::findCurrentLocation);
        } catch (IOException e) {
            throw new UncheckedIOException("Missing map icon", e);
        }

        try {
            goSearchButton = new IconButtonWidget("src/main/resources/search.png",
                                                  this::switchToSearch);
        } catch (IOException e) {
            throw new UncheckedIOException("Missing search icon", e);
        }

        try {
            returnButton = new IconButtonWidget("src/main/resources/back.png", () -> {
                    loadLocation(currentCoords[0], currentCoords[1], locationName);
                    searchPanel.reset();
            });
        } catch (IOException e) {
            throw new UncheckedIOException("Missing back icon", e);
        }

        // initializing header panel
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        headerPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));

        // creating the main screen that will switch between search and information
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        searchPanel = new SearchPanel(this);

        rootPanel = new JPanel();
        ScrollLayoutManager lm = new ScrollLayoutManager(rootPanel);
        rootPanel.setLayout(lm);
        rootPanel.addMouseListener(lm);
        rootPanel.addMouseMotionListener(lm);

        // CURRENT, HOURLY, WEEKLY data
        findCurrentLocation();
        changeHeader(goSearchButton);

        // adding everything together
        add(headerPanel);
        headerPanel.add(header, BorderLayout.CENTER);

        mainPanel.add(rootPanel, "root");
        mainPanel.add(searchPanel, "search");
        add(mainPanel);
    }

    private void changeHeader(IconButtonWidget b) {
        header = new Header(getLocationButton, b, locationName);
        headerPanel.removeAll();
        headerPanel.add(header, BorderLayout.CENTER);
        headerPanel.revalidate();
        headerPanel.repaint();
    }

    private void findCurrentLocation() {
        // TODO: finding location of user
        System.out.println("Getting local information");
        currentCoords = GetData.localWeather();
        locationName = "Cambridge (GB)";

        // making sure we're on the main screen
        ((CardLayout)mainPanel.getLayout()).show(mainPanel, "root");
        changeHeader(goSearchButton);

        update();
    }

    private void switchToSearch() {
        changeHeader(returnButton);
        ((CardLayout)mainPanel.getLayout()).next(mainPanel);
    }

    public void loadLocation(double lo, double la, String name) { // function called by SearchPanel to return to main screen and takes the selected location as an argument
        currentCoords = new double[]{la, lo};
        locationName = name;

        update();
        changeHeader(goSearchButton);
        ((CardLayout)mainPanel.getLayout()).next(mainPanel);
    }

    public void update() {
        // rebuild all components
        rootPanel.removeAll();
        List<DailyData> data = GetData.getWeather(currentCoords[0], currentCoords[1]);
        rootPanel.add(new CurrentPanel(new CurrentData(data.get(0))));

        // Find the 7 hour entries immediately after the current time
        Date current = new Date();
        DateFormat formatter = new SimpleDateFormat("HH");
        int time = Integer.valueOf(formatter.format(current))*100;
        List<HourlyData> next7 = new ArrayList<>();
        List<HourlyData> firstDay = data.get(0).getHours();
        for (HourlyData hd : firstDay){
            if (hd.getTime() >= time){
                next7.add(hd);
            }
        }
        firstDay = data.get(1).getHours();
        int i = 0;
        while (next7.size() < 7){
            next7.add(firstDay.get(i++));
        }

        rootPanel.add(new HourlyPanel(next7));

        rootPanel.add(new WeeklyPanel(data));
    }

    public static void main(String[] args) {
        new SeaSpray().setVisible(true);
    }
}
