package uk.ac.cam.group2.seaspray.widget;

import uk.ac.cam.group2.seaspray.data.TideData;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.*;

public class TidePanel extends JPanel {
        public TidePanel(LinkedList<TideData> data){
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.gridx = 0;
            c.weightx = 0.1;
            c.fill = GridBagConstraints.HORIZONTAL;

            if (data.size() < 4){
                JLabel message = new JLabel("Data not available");
                message.setHorizontalAlignment(JLabel.CENTER);
                add(message,c);
            }else{
                for (int i = 0; i < 4; i++){
                    TideData dt = data.get(i);
                    JLabel entry = new JLabel((dt.isHigh() ? "High: " : "Low: ")+dt.getTimeString());
                    add(entry,c);
                    c.gridx++;
                }
            }
        }
}
