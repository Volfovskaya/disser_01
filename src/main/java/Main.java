public class Main {

    public static void main(String[] args) {

        DBWorker dbWorker = new DBWorker();

        CalculationForDB.theFirstCalculationEffect(dbWorker);

        dbWorker = new DBWorker();

        CalculationForDB.planMaker(dbWorker, 10000, 24, 100);

    }
}
