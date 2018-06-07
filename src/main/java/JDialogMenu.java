import javax.swing.*;
import java.awt.*;

public class JDialogMenu extends JFrame {
    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension FRAME_SIZE = new Dimension(400, 400);
    private static final Dimension PANEL_SIZE = new Dimension(80, 40);
    private static final Dimension TEXT_FIELD_SIZE = new Dimension(70, 20);



    public JDialogMenu() {

        this.setBounds(DISPLAY_SIZE.width / 2 - FRAME_SIZE.width / 2,
                DISPLAY_SIZE.height / 2 - FRAME_SIZE.height / 2,
                FRAME_SIZE.width, FRAME_SIZE.height);

        this.setTitle("АСПРПК");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jBudgetPanel = new JPanel();
        jBudgetPanel.setPreferredSize(PANEL_SIZE);



        JTextField jBudget = new JTextField("250");

        jBudget.setVisible(true);
        jBudget.setAlignmentX(FRAME_SIZE.width / 2);
        jBudget.setAlignmentY(FRAME_SIZE.height / 2);
        jBudget.setPreferredSize(TEXT_FIELD_SIZE);



        jBudget.createToolTip();
        jBudget.setToolTipText("Введите ограничение на бюджет");

        jBudgetPanel.add(jBudget, BorderLayout.CENTER);

        this.getContentPane().add(jBudgetPanel, BorderLayout.NORTH);







        this.setVisible(true);



    }

}
