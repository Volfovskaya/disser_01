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

public class JResultCourse {
    private DBWorker dbWorker = new DBWorker();
    private Statement statement = null;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private static final Dimension DISPLAY_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension JTABLE_SIZE = new Dimension(640, 480);

    JResultCourse() {
        try {
            statement = dbWorker.getConnection()
                    .createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT course_id, course_name, course_price FROM course;");

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
                String columnName = resultSetMetaData.getColumnName(column);

                if (columnName.equals("course_name")) {
                    defaultTableModel.addColumn("Название курса");
                } else if (columnName.equals("course_price")) {
                    defaultTableModel.addColumn("Стоимость курса");
                } else {
                    defaultTableModel.addColumn(resultSetMetaData.getColumnName(column));
                }
            }

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
                    switch (column) {
                        case 1:
                            vector.add(Integer.toString(resultSet.getInt("course_id")));
                        case 2:
                            vector.add(resultSet.getString("course_name"));
                        case 3:
                            vector.add(Integer.toString(resultSet.getInt("course_price")));
                    }
                }
                defaultTableModel.addRow(vector);
            }

            JFrame jFrame = new JFrame("Сведения о курсах");
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
