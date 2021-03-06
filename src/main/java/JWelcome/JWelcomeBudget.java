package JWelcome;

import javax.swing.*;
import java.awt.*;

public class JWelcomeBudget extends JFrame {
    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension FRAME_SIZE = new Dimension(400, 400);
    private static final Dimension TEXT_FIELD_SIZE = new Dimension(350, 40);
    private static final Dimension PANEL_BUTTON_SIZE = new Dimension(380, 150);
    private static final Dimension RADIO_BUTTON_SIZE = new Dimension(350, 40);
    private static final Dimension BUTTON_SIZE = new Dimension(200, 40);

    JWelcomeBudget() {

        this.setLayout(null);

        this.setBounds(DISPLAY_SIZE.width / 2 - FRAME_SIZE.width / 2,
                DISPLAY_SIZE.height / 2 - FRAME_SIZE.height / 2,
                FRAME_SIZE.width, FRAME_SIZE.height);
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());

        this.setTitle("АСПРПК: ограничение на бюджет");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);


        JTextArea jTextMathSet = new JTextArea("Есть ограничение на бюджет?");
        jTextMathSet.setEditable(false);
        jTextMathSet.setBackground(new Color(238, 238, 238));
        this.add(jTextMathSet);
        jTextMathSet.setBounds(FRAME_SIZE.width / 2 - TEXT_FIELD_SIZE.width / 2,
                10,
                TEXT_FIELD_SIZE.width,
                TEXT_FIELD_SIZE.height);

        ButtonGroup buttonGroup = new ButtonGroup();

        JRadioButton yesBudget = new JRadioButton("Да", false);
        yesBudget.setVisible(true);
        buttonGroup.add(yesBudget);

        yesBudget.setBounds(5, 10, RADIO_BUTTON_SIZE.width, RADIO_BUTTON_SIZE.height);

        JRadioButton noBudget = new JRadioButton("Нет", false);
        noBudget.setVisible(true);
        buttonGroup.add(noBudget);

        noBudget.setBounds(5, 50, RADIO_BUTTON_SIZE.width, RADIO_BUTTON_SIZE.height);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesBudget);
        buttonPanel.add(noBudget);

        this.add(buttonPanel);
        buttonPanel.setBounds(FRAME_SIZE.width / 2 - TEXT_FIELD_SIZE.width / 2,
                100,
                PANEL_BUTTON_SIZE.width,
                PANEL_BUTTON_SIZE.height);

        buttonPanel.setVisible(true);
        JButton jButtonAcceptMath = new JButton("Принять условие");
        jButtonAcceptMath.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonAcceptMath);

        jButtonAcceptMath.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                250, BUTTON_SIZE.width, BUTTON_SIZE.height);


        jButtonAcceptMath.addActionListener(e -> {
            if (yesBudget.isSelected()) {
                dispose();
                new JWelcomeCoursePrice();
            }
        });

    }
}
