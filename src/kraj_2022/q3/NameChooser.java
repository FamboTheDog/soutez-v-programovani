package kraj_2022.q3;

import javax.swing.*;
import java.util.Objects;

public class NameChooser extends JPanel {

    public NameChooser() {
        this.add(new JLabel("1. Hráč: "));
        JTextField playerOneInput = new JTextField("Jméno");
        this.add(playerOneInput);
        this.add(new JLabel("2. Hráč: "));
        JTextField playerTwoInput = new JTextField("Jméno");
        this.add(playerTwoInput);
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 7, 21, 7);
        slider.setMajorTickSpacing(7);
        slider.setSnapToTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        this.add(slider);
        String[] choices = {"1", "3", "5", "7"};
        final JComboBox<String> cb = new JComboBox<>(choices);
        this.add(cb);
        JButton submit = new JButton("Potvrdit");
        submit.addActionListener(x-> Main.switchToGame(playerOneInput.getText(), playerTwoInput.getText(), slider.getValue(), Integer.parseInt((String) Objects.requireNonNull(cb.getSelectedItem()))));
        this.add(submit);
    }

}
