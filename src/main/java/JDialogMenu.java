import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class JDialogMenu extends JFrame {
    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension FRAME_SIZE = new Dimension(400, 400);
    private static final Dimension PANEL_SIZE = new Dimension(80, 40);
    private static final Dimension TEXT_FIELD_SIZE = new Dimension(70, 20);
    private static final Dimension BUTTON_SIZE = new Dimension(170, 40);

    private int budget = 0;


    public JDialogMenu() {

        this.setBounds(DISPLAY_SIZE.width / 2 - FRAME_SIZE.width / 2,
                DISPLAY_SIZE.height / 2 - FRAME_SIZE.height / 2,
                FRAME_SIZE.width, FRAME_SIZE.height);
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());

        this.setTitle("АСПРПК");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jPanelWelcome = new JPanel();
        jPanelWelcome.setPreferredSize(new Dimension(80, 80));

        JPanel jPanelBudgetTxt = new JPanel();
        jPanelBudgetTxt.setPreferredSize(PANEL_SIZE);

        JPanel jPanelAcceptLimitsBt = new JPanel();
        jPanelAcceptLimitsBt.setPreferredSize(PANEL_SIZE);

        JTextArea jTextFieldWelcome = new JTextArea("Добро пожаловать в АСПРПК!\n" +
                "Выберите ограничения на бюджет,\nколичество обучаемых на одном \n" +
                "курсе и общее количество обучаемых.");

        jTextFieldWelcome.setBackground(new Color(238, 238, 238));

        jPanelWelcome.add(jTextFieldWelcome, BorderLayout.CENTER);
        this.add(jPanelWelcome, BorderLayout.NORTH);

        JTextField jBudget = new JTextField("250");

        jBudget.setVisible(true);
        jBudget.setAlignmentX(FRAME_SIZE.width / 2);
        jBudget.setAlignmentY(FRAME_SIZE.height / 2);
        jBudget.setMinimumSize(TEXT_FIELD_SIZE);
        jBudget.setMaximumSize(TEXT_FIELD_SIZE);
        jBudget.setPreferredSize(TEXT_FIELD_SIZE);


        jBudget.setToolTipText("Введите ограничение на бюджет");
        jBudget.createToolTip();

        jPanelBudgetTxt.add(jBudget, BorderLayout.CENTER);
        this.getContentPane().add(jPanelBudgetTxt, BorderLayout.CENTER);

        JButton jButtonAcceptLimits = new JButton("Принять ограничения");
        jButtonAcceptLimits.setPreferredSize(BUTTON_SIZE);


        jPanelAcceptLimitsBt.add(jButtonAcceptLimits, BorderLayout.CENTER);
        this.getContentPane().add(jPanelAcceptLimitsBt, BorderLayout.AFTER_LAST_LINE);

        jButtonAcceptLimits.setBounds(50, 50, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonAcceptLimits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    budget = Integer.parseInt(jBudget.getText());
                } catch (NumberFormatException e1) {

                    JOptionPane.showMessageDialog(null, "Ошибка");
                }


                System.out.println("Получен бюджет: " + budget);

            }
        });


        this.setVisible(true);


    }



}
