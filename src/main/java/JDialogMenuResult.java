import javax.swing.*;
import java.awt.*;

public class JDialogMenuResult extends JFrame {
    protected static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    protected static final Dimension FRAME_SIZE = new Dimension(400, 400);
    protected static final Dimension TEXT_FIELD_SIZE = new Dimension(280, 50);
    protected static final Dimension BUTTON_SIZE = new Dimension(250, 40);

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

        JButton jButtonAcceptLimits = new JButton("Исходные данные сотрудников");
        jButtonAcceptLimits.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonAcceptLimits);
        jButtonAcceptLimits.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                60, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonAcceptLimits.addActionListener(e -> System.out.println("Исходные данные выведены"));

    }
}
