import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

        DBWorker dbWorker = new DBWorker();

        CalculationForDB.clearDynamicTables(dbWorker);

        dbWorker = new DBWorker();

        CalculationForDB.FirstCalculationEffect(dbWorker);

        dbWorker = new DBWorker();

        CalculationForDB.planMaker(dbWorker, 250, 100, 100);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("АСПРПК");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }
}
