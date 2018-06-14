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

public class JResultEmployeeStart {
    private DBWorker dbWorker = new DBWorker();
    private Statement statement = null;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension JTABLE_SIZE = new Dimension(640, 480);


    JResultEmployeeStart() {
        try {
            statement = dbWorker.getConnection()
                    .createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee_start");

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
                String columnName;
                columnName = resultSetMetaData.getColumnName(column);

                switch (columnName) {
                    case "employee_name":
                        defaultTableModel.addColumn("Имя сотрудника");
                        break;
                    case "employee_birth":
                        defaultTableModel.addColumn("Дата рождения");
                        break;
                    case "employee_position":
                        defaultTableModel.addColumn("Должность сотрудника");
                        break;
                    case "employee_pc5":
                        defaultTableModel.addColumn("ПК-5");
                        break;
                    case "employee_pc6":
                        defaultTableModel.addColumn("ПК-6");
                        break;
                    case "employee_pc15":
                        defaultTableModel.addColumn("ПК-15");
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
                            vector.add(resultSet.getDate("employee_birth").toString());
                        case 4:
                            vector.add(resultSet.getString("employee_position"));
                        case 5:
                            vector.add(Integer.toString(resultSet.getInt("employee_pc5")));
                        case 6:
                            vector.add(Integer.toString(resultSet.getInt("employee_pc6")));
                        case 7:
                            vector.add(Integer.toString(resultSet.getInt("employee_pc15")));
                    }
                }
                defaultTableModel.addRow(vector);
            }

            JFrame jFrame = new JFrame("Исходные данные сотрудников");
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
