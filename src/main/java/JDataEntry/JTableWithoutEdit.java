package JDataEntry;

import javax.swing.*;

public class JTableWithoutEdit extends JTable {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
