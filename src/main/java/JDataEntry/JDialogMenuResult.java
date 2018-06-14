package JDataEntry;

import Code.DBWorker;

import javax.swing.*;
import java.awt.*;

public class JDialogMenuResult extends JFrame {
    DBWorker dbWorker = new DBWorker();

    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension FRAME_SIZE = new Dimension(400, 400);
    private static final Dimension TEXT_FIELD_SIZE = new Dimension(280, 50);
    private static final Dimension BUTTON_SIZE = new Dimension(250, 40);


    JDialogMenuResult() {

        this.setLayout(null);

        this.setBounds(DISPLAY_SIZE.width / 2 - FRAME_SIZE.width / 2,
                DISPLAY_SIZE.height / 2 - FRAME_SIZE.height / 2,
                FRAME_SIZE.width, FRAME_SIZE.height);
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());

        this.setTitle("Результаты работы АСПРПК");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JTextArea jTextAreaWelcome = new JTextArea("Получены результаты работы АСПРПК.\n" +
                "Выберите нужный отчет для просмотра.");

        jTextAreaWelcome.setEditable(false);

        jTextAreaWelcome.setBackground(new Color(238, 238, 238));

        this.add(jTextAreaWelcome);
        jTextAreaWelcome.setBounds(FRAME_SIZE.width / 2 - TEXT_FIELD_SIZE.width / 2,
                10,
                TEXT_FIELD_SIZE.width,
                TEXT_FIELD_SIZE.height);

        JButton jButtonEmployeeStart = new JButton("Исходные данные сотрудников");
        jButtonEmployeeStart.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonEmployeeStart);
        jButtonEmployeeStart.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                60, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonEmployeeStart.addActionListener(e -> new JResultEmployeeStart());

        JButton jButtonEmployeeEnd = new JButton("Конечные данные сотрудников");
        jButtonEmployeeEnd.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonEmployeeEnd);
        jButtonEmployeeEnd.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                110, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonEmployeeEnd.addActionListener(e -> new JResultEmployeeEnd());

        JButton jButtonVisitation = new JButton("Посещение курсов");
        jButtonVisitation.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonVisitation);
        jButtonVisitation.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                160, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonVisitation.addActionListener(e -> new JResultVisitation());

        JButton jButtonCourse = new JButton("Сведения о курсах");
        jButtonCourse.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonCourse);
        jButtonCourse.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                210, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonCourse.addActionListener(e -> new JResultCourse());

        JButton jButtonCompetence = new JButton("Сведения о компетенциях");
        jButtonCompetence.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonCompetence);
        jButtonCompetence.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                260, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonCompetence.addActionListener(e -> new JResultCompetence());

        JButton jButtonDialogStart = new JButton("Повторное планирование");
        jButtonDialogStart.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonDialogStart);
        jButtonDialogStart.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                310, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonDialogStart.addActionListener(e -> {
            dispose();
            new JDialogStart();

        });


    }
}
