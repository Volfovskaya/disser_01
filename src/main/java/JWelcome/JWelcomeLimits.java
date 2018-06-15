package JWelcome;

import javax.swing.*;
import java.awt.*;

public class JWelcomeLimits extends JFrame {


    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension FRAME_SIZE = new Dimension(400, 400);
    private static final Dimension TEXT_FIELD_SIZE = new Dimension(350, 40);
    private static final Dimension PANEL_BUTTON_SIZE = new Dimension(380, 150);
    private static final Dimension RADIO_BUTTON_SIZE = new Dimension(350, 40);
    private static final Dimension BUTTON_SIZE = new Dimension(200, 40);

    public JWelcomeLimits() {

        this.setLayout(null);

        this.setBounds(DISPLAY_SIZE.width / 2 - FRAME_SIZE.width / 2,
                DISPLAY_SIZE.height / 2 - FRAME_SIZE.height / 2,
                FRAME_SIZE.width, FRAME_SIZE.height);
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());

        this.setTitle("АСПРПК: выбор ограничений");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);


        JTextArea jTextMathSet = new JTextArea("Установите ограничения:");
        jTextMathSet.setEditable(false);
        jTextMathSet.setBackground(new Color(238, 238, 238));
        this.add(jTextMathSet);
        jTextMathSet.setBounds(FRAME_SIZE.width / 2 - TEXT_FIELD_SIZE.width / 2,
                10,
                TEXT_FIELD_SIZE.width,
                TEXT_FIELD_SIZE.height);

//        ButtonGroup buttonGroup = new ButtonGroup();

        JCheckBox mathMax = new JCheckBox("Общее число человек", false);
        mathMax.setVisible(true);
//        buttonGroup.add(mathMax);

        mathMax.setBounds(5, 10, RADIO_BUTTON_SIZE.width, RADIO_BUTTON_SIZE.height);

        JCheckBox mathMin = new JCheckBox("Число обучающихся на курсе", false);
        mathMin.setVisible(true);
//        buttonGroup.add(mathMin);

        mathMin.setBounds(5, 50, RADIO_BUTTON_SIZE.width, RADIO_BUTTON_SIZE.height);

        JCheckBox mathAVG = new JCheckBox("Максимизация приращения для уровня", true);
        mathAVG.setVisible(true);
//        buttonGroup.add(mathAVG);

        mathAVG.setBounds(5, 90, RADIO_BUTTON_SIZE.width, RADIO_BUTTON_SIZE.height);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(mathMax);
        buttonPanel.add(mathMin);
        buttonPanel.add(mathAVG);

        this.add(buttonPanel);
        buttonPanel.setBounds(FRAME_SIZE.width / 2 - TEXT_FIELD_SIZE.width / 2,
                100,
                PANEL_BUTTON_SIZE.width,
                PANEL_BUTTON_SIZE.height);

        buttonPanel.setVisible(true);

    }

}
