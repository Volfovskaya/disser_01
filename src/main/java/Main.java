public class Main {

    public static void main(String[] args) {

        DBWorker dbWorker = new DBWorker();

        CalculationForDB.clearDynamicTables(dbWorker);

        dbWorker = new DBWorker();

        CalculationForDB.FirstCalculationEffect(dbWorker);

        dbWorker = new DBWorker();

        CalculationForDB.planMaker(dbWorker, 250, 100, 100);

    }
}
