package JDataEntry;

import Code.DBWorker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class JResultVisitation {
    private DBWorker dbWorker = new DBWorker();
    private Statement statement = null;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension JTABLE_SIZE = new Dimension(640, 480);

    JResultVisitation() {
        try {
            statement = dbWorker.getConnection()
                    .createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT v.employee_id, e.employee_name, c.course_name, v.visitation_order\n" +
                    "FROM visitation AS v INNER JOIN employee_start AS e\n" +
                    "ON v.employee_id = e.employee_id\n" +
                    "INNER JOIN course AS c\n" +
                    "ON c.course_id = v.course_id\n" +
                    "ORDER BY v.visitation_order;");

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
                String columnName = resultSetMetaData.getColumnName(column);
                switch (columnName) {
                    case "employee_name":
                        defaultTableModel.addColumn("Имя сотрудника");
                        break;
                    case "course_name":
                        defaultTableModel.addColumn("Название курса");
                        break;
                    case "visitation_order":
                        defaultTableModel.addColumn("Порядок посещения");
                        break;
                    default:
                        defaultTableModel.addColumn(resultSetMetaData.getColumnName(column));
                        break;
                }
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
                            vector.add(resultSet.getString("course_name"));
                        case 4:
                            vector.add(Integer.toString(resultSet.getInt("visitation_order")));
                    }
                }
                defaultTableModel.addRow(vector);
            }

            JFrame jFrame = new JFrame("Порядок посещения курсов сотрудниками");
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
