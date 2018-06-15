package JWelcome;

import JDataEntry.JDialogStart;

import javax.swing.*;
import java.awt.*;

public class JWelcomeLimits extends JFrame {


    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension FRAME_SIZE = new Dimension(400, 400);
    private static final Dimension TEXT_FIELD_SIZE = new Dimension(350, 20);
    private static final Dimension PANEL_BUTTON_SIZE = new Dimension(380, 150);
    private static final Dimension RADIO_BUTTON_SIZE = new Dimension(350, 30);
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
                5,
                TEXT_FIELD_SIZE.width,
                TEXT_FIELD_SIZE.height);


        JCheckBox allPeople = new JCheckBox("Общее число человек", false);
        allPeople.setVisible(true);

        allPeople.setBounds(5, 10, RADIO_BUTTON_SIZE.width, RADIO_BUTTON_SIZE.height);

        JCheckBox peopleOnCourse = new JCheckBox("Число обучающихся на курсе", false);
        peopleOnCourse.setVisible(true);

        peopleOnCourse.setBounds(5, 40, RADIO_BUTTON_SIZE.width, RADIO_BUTTON_SIZE.height);

        JCheckBox courseOnEmployee = new JCheckBox("Число курсов на сотрудника", true);
        courseOnEmployee.setVisible(true);

        courseOnEmployee.setBounds(5, 70, RADIO_BUTTON_SIZE.width, RADIO_BUTTON_SIZE.height);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(allPeople);
        buttonPanel.add(peopleOnCourse);
        buttonPanel.add(courseOnEmployee);

        this.add(buttonPanel);
        buttonPanel.setBounds(FRAME_SIZE.width / 2 - TEXT_FIELD_SIZE.width / 2,
                30,
                PANEL_BUTTON_SIZE.width,
                PANEL_BUTTON_SIZE.height);

        buttonPanel.setVisible(true);

        JButton jButtonAcceptMath = new JButton("Принять ограничения");
        jButtonAcceptMath.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonAcceptMath);

        jButtonAcceptMath.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                250, BUTTON_SIZE.width, BUTTON_SIZE.height);


        jButtonAcceptMath.addActionListener(e -> {
            if (allPeople.isSelected() && peopleOnCourse.isSelected()) {
                dispose();
                new JDialogStart();
            }
        });

    }

}
