import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class JResultCompetence {
    private DBWorker dbWorker = new DBWorker();
    private Statement statement = null;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension JTABLE_SIZE = new Dimension(640, 480);

    JResultCompetence() {
        try {
            statement = dbWorker.getConnection()
                    .createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT competence_id, competence_name, competence_caption FROM competence;");

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
                defaultTableModel.addColumn(resultSetMetaData.getColumnName(column));
            }

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
                    switch (column) {
                        case 1:
                            vector.add(Integer.toString(resultSet.getInt("competence_id")));
                        case 2:
                            vector.add(resultSet.getString("competence_name"));
                        case 3:
                            vector.add(resultSet.getString("competence_caption"));
                    }
                }
                defaultTableModel.addRow(vector);
            }

            JFrame jFrame = new JFrame("Сведения о компетенциях");
            JTable jTable = new JTableWithoutEdit();

            jTable.setModel(defaultTableModel);

            JScrollPane jScrollPane = new JScrollPane(jTable,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            jFrame.setBounds(DISPLAY_SIZE.width / 2 - JTABLE_SIZE.width / 2,
                    DISPLAY_SIZE.height / 2 - JTABLE_SIZE.height / 2,
                    JTABLE_SIZE.width, JTABLE_SIZE.height);

            jFrame.add(jScrollPane);
            jFrame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbWorker.getConnection().close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
