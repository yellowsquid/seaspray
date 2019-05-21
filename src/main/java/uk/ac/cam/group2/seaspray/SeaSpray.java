package uk.ac.cam.group2.seaspray;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uk.ac.cam.group2.seaspray.data.*;
import uk.ac.cam.group2.seaspray.search.*;
import uk.ac.cam.group2.seaspray.widget.*;

public class SeaSpray extends JFrame {
    private SearchPanel searchPanel; // search screen
    private JPanel rootPanel; // home screen
    private JPanel mainPanel; // main panel, switches between search and root
    private JPanel headerPanel; // panel containing the header, always on top
    private JPanel header; // actual header inside the panel
    private CurrentPanel currentPanel; // current conditions panel
    private HourlyPanel hourlyPanel; // next 24 hours
    private WeeklyPanel weeklyPanel; // next seven days

    // header buttons
    private IconButtonWidget currentLocationButton;
    private IconButtonWidget searchButton;
    private IconButtonWidget returnButton;

    // current location information
    private Location location;

    public SeaSpray() {
        super("SeaSpray");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 640);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        // header button functionality
        try {
            currentLocationButton =
                    new IconButtonWidget("src/main/resources/map.png", this::findCurrentLocation);
            currentLocationButton.setAlignment(IconWidget.WEST);
        } catch (IOException e) {
            throw new UncheckedIOException("Missing map icon", e);
        }

        try {
            searchButton = new IconButtonWidget("src/main/resources/search.png", this::switchToSearch);
            searchButton.setAlignment(IconWidget.EAST);
        } catch (IOException e) {
            throw new UncheckedIOException("Missing search icon", e);
        }

        try {
            returnButton =
                    new IconButtonWidget(
                            "src/main/resources/back.png",
                            () -> {
                                loadLocation(location);
                                searchPanel.reset();
                            });
            returnButton.setAlignment(IconWidget.EAST);
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
        currentPanel = new CurrentPanel();
        hourlyPanel = new HourlyPanel();
        weeklyPanel = new WeeklyPanel();
        rootPanel.add(currentPanel, Integer.valueOf(1));
        rootPanel.add(hourlyPanel, Integer.valueOf(1));
        rootPanel.add(weeklyPanel, Integer.valueOf(2));

        // CURRENT, HOURLY, WEEKLY data
        findCurrentLocation();
        changeHeader(searchButton);

        // adding everything together
        add(headerPanel);
        headerPanel.add(header, BorderLayout.CENTER);

        mainPanel.add(rootPanel, "root");
        mainPanel.add(searchPanel, "search");
        add(mainPanel);
    }

    private void changeHeader(IconButtonWidget b) {
        header = new Header(currentLocationButton, b, location.toString());
        headerPanel.removeAll();
        headerPanel.add(header, BorderLayout.CENTER);
        headerPanel.revalidate();
        headerPanel.repaint();
    }

    private void findCurrentLocation() {
        // TODO: finding location of user
        System.out.println("Getting local information");
        location = GetData.getCurrentLocation();

        // making sure we're on the main screen
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "root");
        changeHeader(searchButton);

        update();
    }

    private void switchToSearch() {
        changeHeader(returnButton);
        ((CardLayout) mainPanel.getLayout()).next(mainPanel);
    }

    /** Return to main screen and display the location. */
    public void loadLocation(Location location) {
        this.location = location;

        update();
        changeHeader(searchButton);
        ((CardLayout) mainPanel.getLayout()).next(mainPanel);
    }

    public void update() {
        // rebuild all components
        List<DailyData> dailyData = GetData.getWeather(location);
        List<TideData> tides =
                GetData.tideTimes(location).stream().limit(4).collect(Collectors.toList());

        // Find the 7 hour entries immediately after the current time
        Calendar time = Calendar.getInstance();

        List<HourlyData> next24Hours =
                dailyData.stream()
                        .map(DailyData::getHours)
                        .flatMap(Collection::stream)
                        .filter(hourly -> hourly.getTime().after(time))
                        .limit(7)
                        .collect(Collectors.toList());
        List<HourlyData> firstDay = dailyData.get(0).getHours();

        // add to the panel.
        currentPanel.setData(new CurrentData(dailyData.get(0), tides));
        hourlyPanel.setData(next24Hours);
        weeklyPanel.setData(dailyData);
    }

    public static void main(String[] args) {
        new SeaSpray().setVisible(true);
    }
}
