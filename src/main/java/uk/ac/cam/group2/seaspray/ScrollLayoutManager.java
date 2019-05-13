package uk.ac.cam.group2.seaspray;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Point;

import java.awt.event.MouseEvent;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.event.MouseInputListener;

public class ScrollLayoutManager
        implements LayoutManager, MouseInputListener{
    private static final long VELOCITY_CALC_MILLIS = 1;
    private static final double VELOCITY_DECAY = 0.995;

    private final Container parent;
    private double velocity; // Pixels per millisecond
    private double position; // Pixels
    private long lastTime;       // Last click event
    private double lastPosition; // Coords of last click
    private Timer timer;    // Velocity update

    public ScrollLayoutManager(Container parent) {
        this.parent = parent;

        this.velocity = 0;
        this.position = 0;

        this.lastTime = 0;
        this.lastPosition = 0;

        this.timer = null; // Only have a timer when actively updating
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        translate(0);
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        translate(0);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        int width = 0;
        int height = 0;
        for (Component comp : parent.getComponents()) {
            Dimension compSize = comp.getPreferredSize();

            if (compSize.width > width) {
                width = compSize.width;
            }

            if (compSize.height > height) {
                height = compSize.height;
            }
        }

        return new Dimension(width, height);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        int width = 0;
        int height = 0;
        for (Component comp : parent.getComponents()) {
            Dimension compSize = comp.getMinimumSize();

            if (compSize.width > width) {
                width = compSize.width;
            }

            if (compSize.height > height) {
                height = compSize.height;
            }
        }

        return new Dimension(width, height);
    }

    @Override
    public void layoutContainer(Container parent) {
        Component[] components = parent.getComponents();
        int width = parent.getWidth();
        int deltaHeight = parent.getHeight();

        if (position > 0) {
            position = 0;
            velocity = 0;
            if (timer != null) timer.cancel();
        }

        if (position < (1 - components.length) * deltaHeight) {
            position = (1 - components.length) * parent.getHeight();
            velocity = 0;
            if (timer != null) timer.cancel();
        }

        int currentTop = (int) position;

        for (Component component : components) {
            component.setBounds(0, currentTop, width, deltaHeight);
            currentTop += deltaHeight;
        }
    }

    // Called when you hit any mouse button.
    // Tells us a drag has started.
    @Override
    public void mousePressed(MouseEvent e) {
        // Record the last event
        lastTime = e.getWhen();
        lastPosition = e.getPoint().y;
        // Stop current movement
        if (timer != null) timer.cancel();
        velocity = 0;
    }

    // Called when you let go of any mouse button.
    // Tells us a drag has finished.
    @Override
    public void mouseReleased(MouseEvent e) {
        // Adjust velocity depending on how long mouse stationary
        // FIXME how to adjust between movements
        long deltaTime = e.getWhen() - lastTime;
        int periods = (int) (deltaTime / VELOCITY_CALC_MILLIS);

        for (int i = 0; i < periods; i++) {
            decayVelocity();
        }

        // Start the movement timer.
        timer = new Timer();
        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    translate(velocity * VELOCITY_CALC_MILLIS);
                    parent.repaint();
                    decayVelocity();

                    if (hasStopped()) {
                        timer.cancel();
                    }
                }
            },
            0, // Start moving immediately
            VELOCITY_CALC_MILLIS);
    }

    // Called when the mouse is dragged.
    // Tells us only when the mouse moves and is clicked.
    @Override
    public void mouseDragged(MouseEvent e) {
        // Adjust velocity depending on how long mouse stationary
        long deltaTime = e.getWhen() - lastTime;
        double deltaPosition = e.getPoint().y - lastPosition;

        // Translate because drag can start anywhere on screen.
        translate(deltaPosition);
        parent.repaint();

        // Replace last point
        lastTime += deltaTime;
        lastPosition += deltaPosition;

        // Add velocity from moving
        if (deltaTime > Integer.MAX_VALUE || deltaTime == 0) {
            // Really long time since last move so assume 0.
            // Also stops overflow when dividing.
            return;
        }

        // Assuming constant accelleration
        // From s = 1/2 (u + v) t
        velocity = deltaPosition / deltaTime;
        // System.out.println(deltaTime);
    }

    // Called when mouse enters the panel. We don't need this.
    @Override
    public void mouseEntered(MouseEvent e) {}

    // Called when mouse leaves the panel. We don't need this.
    @Override
    public void mouseExited(MouseEvent e) {}

    // Combines a mouse press and release.
    // We listen to both of these so we don't need this.
    @Override
    public void mouseClicked(MouseEvent e) {}

    // Called when mouse moves but isn't clicked. We don't need this.
    @Override
    public void mouseMoved(MouseEvent e) {}

    public void translate(double delta) {
        position += delta;
        layoutContainer(parent);
    }

    /**
     * Decays the velocity. Simulates resistance when scrolling.
     */
    public void decayVelocity() {
        velocity *= VELOCITY_DECAY;
    }

    /**
     * Has the panel stopped scrolling. It has stopped if it is moving at less
     * than 10 pixels per second in both the x and y directions.
     *
     * NOTE: if the panel has stopped according to above, the panel is
     * forcefully stopped.
     *
     * @returns {@code true} if the panel is stationary.
     */
    public boolean hasStopped() {
        if ((velocity < 1e-2) && (velocity > -1e-2)) {
            velocity = 0;
        }

        return velocity == 0;
    }
}
