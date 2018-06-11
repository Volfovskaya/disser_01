import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class JResultEmployeeEnd {
    private DBWorker dbWorker = new DBWorker();
    private Statement statement = null;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension JTABLE_SIZE = new Dimension(640, 480);


    JResultEmployeeEnd() {
        try {
            Statement statement = dbWorker.getConnection()
                    .createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT s.employee_id, s.employee_name, s.employee_position, e.employee_pc5, e.employee_pc6, e.employee_pc15\n" +
                    "FROM employee_start AS s\n" +
                    "RIGHT JOIN employee_end AS e\n" +
                    "ON s.employee_id = e.employee_id ORDER BY s.employee_id;");

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
                defaultTableModel.addColumn(resultSetMetaData.getColumnName(column));
            }

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
                    switch (column) {
                        case 1:
                            vector.add(Integer.toString(resultSet.getInt("employee_id")));
                        case 2:
                            vector.add(resultSet.getString("employee_name"));
                        case 3:
                            vector.add(resultSet.getString("employee_position"));
                        case 4:
                            vector.add(Integer.toString(resultSet.getInt("employee_pc5")));
                        case 5:
                            vector.add(Integer.toString(resultSet.getInt("employee_pc6")));
                        case 6:
                            vector.add(Integer.toString(resultSet.getInt("employee_pc15")));
                    }
                }
                defaultTableModel.addRow(vector);
            }

            JFrame jFrame = new JFrame("Данные сотрудников после обучения");
            JTable jTable = new JTableWithoutEdit();


            jTable.setModel(defaultTableModel);

            JScrollPane jScrollPane = new JScrollPane(jTable);

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
