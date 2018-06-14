package JDataEntry;

import Code.Main;

import javax.swing.*;
import java.awt.*;

public class JDialogCountCourse extends JFrame {
    protected static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    protected static final Dimension FRAME_SIZE = new Dimension(400, 400);
    protected static final Dimension TEXT_FIELD_SIZE = new Dimension(250, 50);
    protected static final Dimension BUTTON_SIZE = new Dimension(200, 40);


    private int countPeopleCourse = 0;

    public int getCountPeopleCourse() {
        return countPeopleCourse;
    }

    public void setCountPeopleCourse(int countPeopleCourse) {
        this.countPeopleCourse = countPeopleCourse;
    }


    JDialogCountCourse() {
        this.setLayout(null);

        this.setBounds(DISPLAY_SIZE.width / 2 - FRAME_SIZE.width / 2,
                DISPLAY_SIZE.height / 2 - FRAME_SIZE.height / 2,
                FRAME_SIZE.width, FRAME_SIZE.height);
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());

        this.setTitle("АСПРПК: ограничение на число человек на курсе");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea jTextAreaWelcome = new JTextArea("Введите ограничение на число\n" +
                "обучающихся сотрудников\nна одном курсе:");

        jTextAreaWelcome.setEditable(false);

        jTextAreaWelcome.setBackground(new Color(238, 238, 238));

        this.add(jTextAreaWelcome);
        jTextAreaWelcome.setBounds(FRAME_SIZE.width / 2 - TEXT_FIELD_SIZE.width / 2,
                10,
                TEXT_FIELD_SIZE.width,
                TEXT_FIELD_SIZE.height);

        JTextField jCountPeopleOnCourse = new JTextField("25");
        jCountPeopleOnCourse.setVisible(true);
        jCountPeopleOnCourse.setToolTipText("Введите ограничение на число обучающихся на одном курсе");
        jCountPeopleOnCourse.createToolTip();

        this.add(jCountPeopleOnCourse);
        jCountPeopleOnCourse.setBounds(FRAME_SIZE.width / 2 - 50, 60, 100, 40);

        JButton jButtonAcceptLimits = new JButton("Принять ограничение");
        jButtonAcceptLimits.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonAcceptLimits);
        jButtonAcceptLimits.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                110, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonAcceptLimits.addActionListener(e -> {
            try {
                setCountPeopleCourse(Integer.parseInt(jCountPeopleOnCourse.getText()));
                if (countPeopleCourse >= 0) {
                    Main.countPeopleCourseMain = getCountPeopleCourse();
                    JDialogCountPeople jDialogCountPeople = new JDialogCountPeople();
                    jDialogCountPeople.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Введите корректное число >= 0!");
                }
            } catch (NumberFormatException e1) {

                JOptionPane.showMessageDialog(null, "Ошибка");
            }
        });
    }
}
