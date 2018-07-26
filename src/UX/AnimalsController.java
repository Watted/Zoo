package UX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.zoo.siraj.Cage;
import main.java.com.zoo.siraj.Food;

import java.util.Iterator;
import java.util.List;


public class AnimalsController {
    @FXML
    private Button close;
    @FXML
    private Button addAnimal;
    @FXML
    private ComboBox animalType;
    @FXML
    private ComboBox addCage;
    @FXML
    private TextField addCzxageSize;
    @FXML
    private ComboBox foodType;
    @FXML
    private ComboBox maxFoodAmount;
    @FXML
    private Button add;
    @FXML
    private Button cancelAdding;
    @FXML
    private Button feedAnimal;
    @FXML
    private void initialize() {
        animalType.setVisible(false);
        addCage.setVisible(false);
        addCzxageSize.setVisible(false);
        foodType.setVisible(false);
        maxFoodAmount.setVisible(false);
        add.setVisible(false);
        cancelAdding.setVisible(false);

        close.setOnAction(close->((Stage) ((Button) close.getSource()).getScene().getWindow()).close());
        addAnimal.setOnAction(addAnimals->{
            animalType.setVisible(true);
            ObservableList<String> options =
                    FXCollections.observableArrayList(
                            "Lion","Monkey","Snake"
                    );
            animalType.getItems().addAll(options);
            animalType.getSelectionModel().selectFirst();
            Main.zoo.addCage(new Cage(5));
            Main.zoo.addCage(new Cage(4));
            ObservableList<Integer> optionsCage = FXCollections.observableList(
                    Main.zoo.getCages()
                );
            addCage.getSelectionModel().selectFirst();
            addCage.getItems().addAll(optionsCage);
            addCage.setVisible(true);
            addCzxageSize.setVisible(true);
            ObservableList<Food> options1 =
                    FXCollections.observableArrayList(
                            Food.fruits,Food.meats,Food.worms,Food.insects,Food.seeds,Food.grains,Food.plants
                    );
            foodType.setVisible(true);
            foodType.getItems().addAll(options1);
            foodType.getSelectionModel().selectFirst();
            ObservableList<Integer> options2 =
                    FXCollections.observableArrayList(
                            1,2,3,4,5,6,7,8,9,10
                    );
            maxFoodAmount.setVisible(true);
            maxFoodAmount.getItems().addAll(options2);
            maxFoodAmount.getSelectionModel().select(5);
            add.setVisible(true);
            cancelAdding.setVisible(true);
            addAnimal.setDisable(true);

        });
        cancelAdding.setOnAction(cancel->{
            animalType.setVisible(false);
            addCage.setVisible(false);
            addCzxageSize.setVisible(false);
            foodType.setVisible(false);
            maxFoodAmount.setVisible(false);
            add.setVisible(false);
            cancelAdding.setVisible(false);
            addAnimal.setDisable(false);

        });
        add.setOnAction(add->{
            //Main.zoo.addAnimalToCage();
        });
        feedAnimal.setOnAction(feeding->{
            Application app = new Application() {
                @Override
                public void start(Stage primaryStage) throws Exception {
                    Parent root = FXMLLoader.load(getClass().getResource("FeedAnimal.fxml"));
                    primaryStage.setTitle("FeedAnimal Controller");
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