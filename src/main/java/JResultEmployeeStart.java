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
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension JTABLE_SIZE = new Dimension(640, 480);


    JResultEmployeeStart() {
        try {
            Statement statement = dbWorker.getConnection()
                    .createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee_start");

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
        }

    }
}
