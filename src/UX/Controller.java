package UX;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.com.zoo.siraj.Cage;
import main.java.com.zoo.siraj.Food;
import main.java.com.zoo.siraj.Zoo;

import javax.swing.*;
import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;


public class Controller {
    @FXML
    private Pane controlPannel;
    @FXML
    private Label welcomeLabel;
    @FXML
    private ComboBox cageSelector;
    @FXML
    private Button animals;
    @FXML
    private Button employe;
    @FXML
    private Button cages;
    @FXML
    private Button close;
    @FXML
    private SplitPane mainSplitPane;
    @FXML
    private ImageView imgBox;
    @FXML
    private Label statusL;
    @FXML
    private Label animalsNumL;
    @FXML
    private Label animalTypeL;
    @FXML
    private Label mins;
    @FXML
    private Label secs;
    @FXML
    private Button buyFood;
    @FXML
    private Circle plantsAlarm;
    @FXML
    private Label plantsAlarmNum;
    @FXML
    private Circle grainsAlarm;
    @FXML
    private Label grainsAlarmNUM;
    @FXML
    private Circle seedsAlarm;
    @FXML
    private Label seedsAlarmNum;
    @FXML
    private Circle meatAlarm;
    @FXML
    private Label meatAlarmNum;
    @FXML
    private Circle fruitAlarm;
    @FXML
    private Label fruitAlarmNum;
    @FXML
    private Circle wormsAlarm;
    @FXML
    private Label wormsAlarmNum;
    @FXML
    private Circle insectsAlarm;
    @FXML
    private Label insectsAlarmNum;
    @FXML
    private ListView notifications;
    @FXML
    private void initialize() {
        HashMap<String,String> images = new HashMap<>();
        images.put("Lion","UX\\imgs\\lion.jpg");
        images.put("Snake", "UX\\imgs\\snake.jpg");
        images.put("Monkey","UX\\imgs\\monkey.jpg");
        mainSplitPane.setVisible(false);
        controlPannel.setVisible(false);
        FadeTransition ft = new FadeTransition(Duration.millis(3000), welcomeLabel);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
        ft.play();
        new java.util.Timer().schedule(new java.util.TimerTask(){

            @Override
            public void run() {
                mainSplitPane.setVisible(true);
                welcomeLabel.setVisible(false);
                controlPannel.setVisible(true);
            }
        },3000);

        Timer time = new Timer(500,act->{
            Platform.runLater(()-> {
                if(!cageSelector.getItems().containsAll(Main.zoo.getCagesMap().keySet())) {
                    cageSelector.getItems().clear();
                    cageSelector.getItems().addAll(Main.zoo.getCagesMap().keySet().toArray());
                }
            });
            ////////////////////////Update Alarms////////////////////////
            Platform.runLater(()-> {
                if (Main.zoo.getFoods().get(Food.plants) == 0) plantsAlarm.setFill(Color.RED);
                else plantsAlarm.setFill(Color.GREEN);
                if (Main.zoo.getFoods().get(Food.grains) == 0) grainsAlarm.setFill(Color.RED);
                else grainsAlarm.setFill(Color.GREEN);
                if (Main.zoo.getFoods().get(Food.meats) == 0) meatAlarm.setFill(Color.RED);
                else meatAlarm.setFill(Color.GREEN);
                if (Main.zoo.getFoods().get(Food.seeds) == 0) seedsAlarm.setFill(Color.RED);
                else seedsAlarm.setFill(Color.GREEN);
                if (Main.zoo.getFoods().get(Food.worms) == 0) wormsAlarm.setFill(Color.RED);
                else wormsAlarm.setFill(Color.GREEN);
                if (Main.zoo.getFoods().get(Food.insects) == 0) insectsAlarm.setFill(Color.RED);
                else insectsAlarm.setFill(Color.GREEN);
                if (Main.zoo.getFoods().get(Food.fruits) == 0) fruitAlarm.setFill(Color.RED);
                else fruitAlarm.setFill(Color.GREEN);
                plantsAlarmNum.setText(Main.zoo.getFoods().get(Food.plants).toString());
                grainsAlarmNUM.setText(Main.zoo.getFoods().get(Food.grains).toString());
                seedsAlarmNum.setText(Main.zoo.getFoods().get(Food.seeds).toString());
                meatAlarmNum.setText(Main.zoo.getFoods().get(Food.meats).toString());
                fruitAlarmNum.setText(Main.zoo.getFoods().get(Food.fruits).toString());
                wormsAlarmNum.setText(Main.zoo.getFoods().get(Food.worms).toString());
                insectsAlarmNum.setText(Main.zoo.getFoods().get(Food.insects).toString());
            });
        });
        Timer time1 = new Timer(1000,act->{
            Platform.runLater(()-> {
                int minute = Integer.parseInt(mins.getText());
                int seconds = Integer.parseInt(secs.getText());
                if(seconds == 59){
                    minute++;
                    mins.setText("0"+minute);
                    secs.setText("00");
                }else {
                    seconds++;
                    secs.setText(seconds+"");
                }
                if(minute == 10) {
                    Main.day++;
                    Main.zoo.resetAnimalBellies();
                    mins.setText("00");
                    secs.setText("00");
                    for (String timeF : Main.zoo.getDealsPerDate().keySet()) {
                        int perFood = Integer.parseInt(timeF);
                        if(Main.day - perFood >= 2){
                            notifications.getItems().add(new String(
                                    Main.zoo.getDealsPerDate().get(perFood+"").getCurrent().toString()
                                            + " Is Wasted !"));
                            Main.zoo.removeFoodAmount(Main.zoo.getDealsPerDate().get(perFood+""));
                            Main.zoo.getDealsPerDate().remove(timeF);
                        }
                    }
                }


            });
        });

        time.start();
        time1.start();
        cageSelector.setOnAction(selected->{
            Platform.runLater(()->{
            if(Main.zoo.getCagesMap().containsKey(cageSelector.getSelectionModel().getSelectedItem().toString())) {
                Cage c = Main.zoo.getCagesMap().get(cageSelector.getSelectionModel().getSelectedItem().toString());
                if (c.getContentAnimal().size() == 0) {
                    statusL.setText(statusL.getText().substring(0, 6) + " : EMPTY!");
                } else if (c.getContentAnimal().size() == c.getSize()) {
                    statusL.setText(statusL.getText().substring(0, 6) + " : FULL!");
                } else {
                    statusL.setText(statusL.getText().substring(0, 6) + " : Not Full");
                }
                animalsNumL.setText(animalsNumL.getText().substring(0, 25) + " " + c.getContentAnimal().size() + " / " + c.getSize());
                if (c.getContentAnimal().size() > 0) {
                    Platform.runLater(() -> {
                        animalTypeL.setText(animalTypeL.getText().substring(0, 14) + " : " + c.getContentAnimal().get(0).getName());
                        imgBox.setImage(new Image(images.get(c.getContentAnimal().get(0).getName())));
                    });
                }
            }
            });
        });
        //Buttons Events
        //close
        close.setOnAction(action-> {
            try {
                Zoo.saveToFile(Main.zoo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);

        });
        buyFood.setOnAction(clicked->{
            Application app = new Application() {
                @Override
                public void start(Stage primaryStage) throws Exception {
                    Parent root = FXMLLoader.load(getClass().getResource("BuyFood.fxml"));
                    primaryStage.setTitle("Buy Food");
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
