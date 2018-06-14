package JDataEntry;

import Code.CalculationForDB;
import Code.DBWorker;
import Code.Main;

import javax.swing.*;
import java.awt.*;

public class JDialogCountPeople extends JFrame {
    protected static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    protected static final Dimension FRAME_SIZE = new Dimension(400, 400);
    protected static final Dimension TEXT_FIELD_SIZE = new Dimension(300, 50);
    protected static final Dimension BUTTON_SIZE = new Dimension(200, 40);

    private int countPeople = 0;
    public int getCountPeople() {
        return countPeople;
    }
    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }

    JDialogCountPeople() {
        this.setLayout(null);

        this.setBounds(DISPLAY_SIZE.width / 2 - FRAME_SIZE.width / 2,
                DISPLAY_SIZE.height / 2 - FRAME_SIZE.height / 2,
                FRAME_SIZE.width, FRAME_SIZE.height);
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());

        this.setTitle("АСПРПК: ограничение на общее число обучаемых");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea jTextCountPeople = new JTextArea("Введите ограничение на число\n" +
                "обучающихся сотрудников:");

        jTextCountPeople.setEditable(false);

        jTextCountPeople.setBackground(new Color(238, 238, 238));

        this.add(jTextCountPeople);
        jTextCountPeople.setBounds(FRAME_SIZE.width / 2 - TEXT_FIELD_SIZE.width / 2,
                10,
                TEXT_FIELD_SIZE.width,
                TEXT_FIELD_SIZE.height);

        JTextField jCountPeople = new JTextField("200");
        jCountPeople.setVisible(true);
        jCountPeople.setToolTipText("Введите ограничение на число обучающихся на одном курсе");
        jCountPeople.createToolTip();

        this.add(jCountPeople);
        jCountPeople.setBounds(FRAME_SIZE.width / 2 - 50, 60, 100, 40);

        JButton jButtonAcceptLimits = new JButton("Принять ограничение");
        jButtonAcceptLimits.setPreferredSize(BUTTON_SIZE);
        this.add(jButtonAcceptLimits);
        jButtonAcceptLimits.setBounds(FRAME_SIZE.width / 2 - BUTTON_SIZE.width / 2,
                110, BUTTON_SIZE.width, BUTTON_SIZE.height);

        jButtonAcceptLimits.addActionListener(e -> {
            try {
                setCountPeople(Integer.parseInt(jCountPeople.getText()));
                if (countPeople >= 0) {
                    Main.countPeopleMain = getCountPeople();
                    dispose();
                    JDialogMenuResult jDialogMenuResult = new JDialogMenuResult();


                    jDialogMenuResult.setVisible(true);


                    DBWorker dbWorker = new DBWorker();
                    CalculationForDB.FirstCalculationEffect(dbWorker);
                    dbWorker = new DBWorker();
                    CalculationForDB.planMaker(dbWorker, Main.budgetMain, Main.countPeopleCourseMain, Main.countPeopleMain);


                } else {
                    JOptionPane.showMessageDialog(null, "Введите корректное число >= 0!");
                }
            } catch (NumberFormatException e1) {

                JOptionPane.showMessageDialog(null, "Ошибка ввода");
            }
        });

    }
}
