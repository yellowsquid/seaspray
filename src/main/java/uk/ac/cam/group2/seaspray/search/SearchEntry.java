package uk.ac.cam.group2.seaspray.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import uk.ac.cam.group2.seaspray.data.*;

public class SearchEntry extends JPanel {
    private JLabel text;

    public SearchEntry(Location content) {
        text = new JLabel(content.toString());
        text.setFont(new Font(text.getFont().getFontName(), Font.PLAIN, 25));

        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setMaximumSize(new Dimension(250, 60));

        this.setLayout(new BorderLayout());
        this.add(text);
    }
}
