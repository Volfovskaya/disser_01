package JDataEntry;

import Code.CalculationForDB;
import Code.DBWorker;
import Code.Main;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class JDialogStart extends JFrame {
    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension FRAME_SIZE = new Dimension(400, 400);
    private static final Dimension TEXT_FIELD_SIZE = new Dimension(250, 100);
    private static final Dimension BUTTON_SIZE = new Dimension(200, 40);

    DBWorker dbWorker = new DBWorker();


    private int budget = 0;

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public JDialogStart() {

        CalculationForDB.clearDynamicTables(dbWorker);
        try {
            dbWorker.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.setLayout(null);

        this.setBounds(DISPLAY_SIZE.width / 2 - FRAME_SIZE.width / 2,
                DISPLAY_SIZE.height / 2 - FRAME_SIZE.height / 2,
                FRAME_SIZE.width, FRAME_SIZE.height);
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());

        this.setTitle("АСПРПК: введите ограничение на бюджет");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JTextArea jTextAreaWelcome = new JTextArea("Введите ограничение на бюджет:");

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
                    JOptionPane.showMessageDialog(null,
                            "Введите корректное число >= 0!",
                            "Ошибка", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null,
                        "Введите корректное число (int)",
                        "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
            System.out.println("Получен бюджет: " + budget);
        });
        this.setVisible(true);
    }
}
