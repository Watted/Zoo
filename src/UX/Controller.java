package UX;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;


public class Controller {
    @FXML
    private Pane controlPannel;
    @FXML
    private Label welcomeLabel;

    @FXML
    private Button animals;
    @FXML
    private Button employe;
    @FXML
    private Button cages;
    @FXML
    private Button close;

    @FXML
    private void initialize() {
        controlPannel.setVisible(false);
        FadeTransition ft = new FadeTransition(Duration.millis(3000), welcomeLabel);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
        ft.play();
        Timer timer = new Timer(4000, e ->{welcomeLabel.setVisible(false); controlPannel.setVisible(true);});
        timer.start();



        //Buttons Events
        //close
        close.setOnAction(action->System.exit(0));

        //Animals
        animals.setOnAction(action->{
            Application app = new Application() {
                @Override
                public void start(Stage primaryStage) throws Exception {
                    Parent root = FXMLLoader.load(getClass().getResource("AnimalsScreen.fxml"));
                    primaryStage.setTitle("Animals Controller");
                    Scene primScene = new Scene(root, 700,400);
                    primaryStage.setScene(primScene);
                    primaryStage.show();
                }
            };
            try {
                app.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        //Employes
        employe.setOnAction(action->{
            Application app = new Application() {
                @Override
                public void start(Stage primaryStage) throws Exception {
                    Parent root = FXMLLoader.load(getClass().getResource("EmployeeScreen.fxml"));
                    primaryStage.setTitle("Employees Controller");
                    Scene primScene = new Scene(root, 700,400);
                    primaryStage.setScene(primScene);
                    primaryStage.show();
                }
            };
            try {
                app.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //Cages
        cages.setOnAction(action->{
            Application app = new Application() {
                @Override
                public void start(Stage primaryStage) throws Exception {
                    Parent root = FXMLLoader.load(getClass().getResource("CagesScreen.fxml"));
                    primaryStage.setTitle("Cages Controller");
                    Scene primScene = new Scene(root, 700,400);
                    primaryStage.setScene(primScene);
                    primaryStage.show();
                }
            };
            try {
                app.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
