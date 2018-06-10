import javax.swing.*;
import java.awt.*;


public class JDialogStart extends JFrame {
    protected static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    protected static final Dimension FRAME_SIZE = new Dimension(400, 400);
    protected static final Dimension TEXT_FIELD_SIZE = new Dimension(250, 100);
    protected static final Dimension BUTTON_SIZE = new Dimension(200, 40);



    private int budget = 0;

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }






    public JDialogStart() {

        this.setLayout(null);

        this.setBounds(DISPLAY_SIZE.width / 2 - FRAME_SIZE.width / 2,
                DISPLAY_SIZE.height / 2 - FRAME_SIZE.height / 2,
                FRAME_SIZE.width, FRAME_SIZE.height);
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());

        this.setTitle("АСПРПК");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        JTextArea jTextAreaWelcome = new JTextArea("Добро пожаловать в АСПРПК!\n" +
                "Выберите ограничения на бюджет,\nколичество обучаемых на одном \n" +
                "курсе и общее количество обучаемых.\n" +
                "Введите ограничение на бюджет:");

        jTextAreaWelcome.setEditable(false);

        jTextAreaWelcome.setBackground(new Color(238, 238, 238));

        this.add(jTextAreaWelcome);
        jTextAreaWelcome.setBounds(FRAME_SIZE.width / 2 - TEXT_FIELD_SIZE.width / 2,
                10,
                TEXT_FIELD_SIZE.width,
                TEXT_FIELD_SIZE.height);


        JTextField jBudget = new JTextField("250");
        jBudget.setVisible(true);
        jBudget.setToolTipText("Введите ограничение на бюджет");
        jBudget.createToolTip();

        this.add(jBudget);
        jBudget.setBounds(FRAME_SIZE.width / 2 - 50, 110, 100, 40);

        JButton jButtonAcceptLimits = new JButton("Принять ограничение");
        jButtonAcceptLimits.setPreferredSize(BUTTON_SIZE);


        this.add(jButtonAcceptLimits);

        jButtonAcceptLimits.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                180, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonAcceptLimits.addActionListener(e -> {
            try {

                setBudget(Integer.parseInt(jBudget.getText()));
                if (budget >= 0) {
                    Main.budgetMain = getBudget();
                    JDialogCountCourse jDialogCountCourse = new JDialogCountCourse();
                    jDialogCountCourse.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Введите корректное число >= 0!");
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Ошибка");
            }
            System.out.println("Получен бюджет: " + budget);
        });
        this.setVisible(true);
    }
}
