import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDialogMenuResult extends JFrame {
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

        jButtonEmployeeStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JResultEmployeeStart();
            }
        });

        JButton jButtonEmployeeEnd = new JButton("Конечные данные сотрудников");
        jButtonEmployeeEnd.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonEmployeeEnd);
        jButtonEmployeeEnd.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                110, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonEmployeeEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JResultEmployeeEnd();
            }
        });

    }
}
