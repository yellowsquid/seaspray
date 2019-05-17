package uk.ac.cam.group2.seaspray.widget;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class IconButtonWidget extends IconWidget implements MouseListener {
    private final Callback callback;

    public IconButtonWidget(String path, Callback callback) throws IOException {
        super(path);
        this.callback = callback;
        addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        callback.onClick();
    }

    @FunctionalInterface
    public static interface Callback {
        void onClick();
    }
}
