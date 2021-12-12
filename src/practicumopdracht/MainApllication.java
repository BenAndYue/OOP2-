package practicumopdracht;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controller.PlayerController;


public class MainApllication extends Application {
    private static final String TITEL = "Earnings van een speler";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private static Stage primaryStage;

    public static void setStage(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage){
        MainApllication.primaryStage = primaryStage;
        PlayerController playerController = new PlayerController();

        primaryStage.setTitle(TITEL);
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setResizable(false);
        playerController.displayView();

    }
}
