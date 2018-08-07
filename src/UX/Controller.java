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
import main.java.com.zoo.siraj.Animal;
import main.java.com.zoo.siraj.Cage;
import main.java.com.zoo.siraj.Food;
import main.java.com.zoo.siraj.Zoo;

import javax.swing.*;
import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Controller{
    @FXML
    private Label days;
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
    private ImageView imgBox1;
    @FXML
    private ImageView imgBox2;
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
        Set<Animal> eatingAnimals = new HashSet<>();
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
                        if (!cageSelector.getItems().containsAll(Main.zoo.getCagesMap().keySet()) || cageSelector.getItems().size() != Main.zoo.getCagesMap().keySet().size()) {
                            cageSelector.getItems().clear();
                            cageSelector.getItems().addAll(Main.zoo.getCagesMap().keySet().toArray());
                            statusL.setText("Status:");
                            animalsNumL.setText("Number of animals Inside:");
                            animalTypeL.setText("Type Of Animal:");
                            imgBox.setImage(null);
                            imgBox1.setImage(null);
                            imgBox2.setImage(null);
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
            Platform.runLater(()->days.setText("Day "+Main.day));
            Platform.runLater(()-> {
                int minute = Integer.parseInt(mins.getText());
                int seconds = Integer.parseInt(secs.getText());
                for(Animal animal : Main.zoo.getAnimalsHash()){
                    if(!animal.eating && eatingAnimals.contains(animal)){
                        Platform.runLater(()->{
                            notifications.getItems().add(animal.getName() + " Finished Eating");
                            eatingAnimals.remove(animal);
                        });
                    }
                    if(animal.eating && !eatingAnimals.contains(animal)){
                        Platform.runLater(()->{
                            notifications.getItems().add(animal.getName() + " Started Eating");
                            eatingAnimals.add(animal);
                        });


                    }
                }
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
                        int perFood = Integer.parseInt(timeF.substring(0,timeF.indexOf("$")));
                        if(Main.day - perFood >= 2){
                            Platform.runLater(()-> {
                                notifications.getItems().add(Math.min(Main.zoo.getDealsPerDate().get(timeF).getAmount(),
                                        Main.zoo.getFoods().get(Main.zoo.getDealsPerDate().get(timeF).getCurrent()))
                                        + "KG "+ new String(
                                        Main.zoo.getDealsPerDate().get(timeF).getCurrent().toString()
                                                + " Got Wasted !"));
                                Main.zoo.removeFoodAmount(Main.zoo.getDealsPerDate().get(timeF));
                                Main.zoo.getDealsPerDate().remove(timeF);
                            });
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
                    imgBox.setImage(new Image("UX\\imgs\\empy.jpg"));
                    imgBox1.setImage(new Image("UX\\imgs\\empy.jpg"));
                    imgBox2.setImage(new Image("UX\\imgs\\empy.jpg"));
                } else {
                    statusL.setText(statusL.getText().substring(0, 6) + " : Contains Animals!");
                }
                animalsNumL.setText(animalsNumL.getText().substring(0, 25) + " " + c.getContentAnimal().size());
                if (c.getContentAnimal().size() > 0) {
                    Platform.runLater(() -> {
                        animalTypeL.setText(animalTypeL.getText().substring(0, 14) + " : " + c.getContentAnimal().get(0).getName());
                        boolean[] flags = {false,false,false};
                        for (int i = 0 ; i <c.getContentAnimal().size() ; i++){
                            if(c.getContentAnimal().get(i).getName().equals("Lion")) flags[0]=true;
                            if(c.getContentAnimal().get(i).getName().equals("Monkey")) flags[1]=true;
                            if(c.getContentAnimal().get(i).getName().equals("Snake")) flags[2]=true;
                        }
                        if (flags[0])
                            imgBox.setImage(new Image("UX\\imgs\\lion.jpg"));
                        else
                            imgBox.setImage(new Image("UX\\imgs\\white.jpg"));
                        if (flags[1])
                            imgBox1.setImage(new Image("UX\\imgs\\monkey.jpg"));
                        else
                            imgBox1.setImage(new Image("UX\\imgs\\white.jpg"));
                        if (flags[2])
                            imgBox2.setImage(new Image("UX\\imgs\\snake.jpg"));
                        else
                            imgBox2.setImage(new Image("UX\\imgs\\white.jpg"));

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
