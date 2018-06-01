import java.sql.SQLException;

public class CalculationForLimitEffect {
    public static void main(String[] args) {

        DBWorker dbWorker = new DBWorker();
        try {
            System.out.println(CalculationForDB.calculationLimitMaxEffect(dbWorker));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbWorker.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
