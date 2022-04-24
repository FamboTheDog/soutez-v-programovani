package kraj_2015.q1;

import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {

    public Settings(NormalDraw normalDraw) {
        this.setLayout(new BorderLayout());
        JButton stop = new JButton("Stop");
        stop.addActionListener(e-> {
            normalDraw.setDrawStage(1000);
            normalDraw.setSlowMode(false);
            normalDraw.setIteration(0);
            normalDraw.repaint();
        });
        this.add(stop, BorderLayout.EAST);

        JButton slowMode = new JButton("Nakresli pomalu");
        slowMode.addActionListener(e-> normalDraw.setSlowMode(true));
        this.add(slowMode, BorderLayout.WEST);
    }

}
