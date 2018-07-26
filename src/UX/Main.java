package UX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Parent root = FXMLLoader.load(getClass().getResource("welcomeScreen.fxml"));
        primaryStage.setTitle("Hello World");
        Scene primScene = new Scene(root, screenSize.getWidth()*0.75, screenSize.getHeight());
        primaryStage.setScene(primScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
