package UX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.com.zoo.siraj.*;

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
    private ComboBox addAnimalSize;
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
    private TreeView listOfAnimals;
    @FXML
    private void initialize() {
        animalType.setVisible(false);
        addCage.setVisible(false);
        addAnimalSize.setVisible(false);
        foodType.setVisible(false);
        maxFoodAmount.setVisible(false);
        add.setVisible(false);
        cancelAdding.setVisible(false);

        Cage cage = new Cage(5);
        Main.zoo.addCage(cage);
        Main.zoo.addAnimalToCage(new Lion(3),cage);
        Main.zoo.addAnimalToCage(new Monkey(3),cage);
        Main.zoo.addAnimalToCage(new Snake(3),cage);

        TreeItem<String> lion1 = new TreeItem<String>("Lion");
        TreeItem<String> monkey1 = new TreeItem<String>("Monkey");
        TreeItem<String> snake1 = new TreeItem<String>("Snake");
        lion1.setExpanded(true);
        monkey1.setExpanded(true);
        snake1.setExpanded(true);
        List<String> lion = Main.zoo.getAnimals("Lion");
        List<String> monkey = Main.zoo.getAnimals("monkey");
        List<String> snake = Main.zoo.getAnimals("Snake");

        Iterator<String> iteratorLion = lion.iterator();
        while (iteratorLion.hasNext()){
            String next = iteratorLion.next();
            lion1.getChildren().add(new TreeItem<String>(next));
        }

        Iterator<String> iteratormonkey = monkey.iterator();
        while (iteratormonkey.hasNext()){
            String next = iteratorLion.next();
            monkey1.getChildren().add(new TreeItem<>(next));
        }

        Iterator<String> iteratorSnake = snake.iterator();
        while (iteratorSnake.hasNext()){
            String next = iteratorSnake.next();
            snake1.getChildren().add(new TreeItem<>(next));
        }


        listOfAnimals.setRoot(lion1);
        listOfAnimals.setRoot(monkey1);
        listOfAnimals.setRoot(snake1);

        close.setOnAction(close->((Stage) ((Button) close.getSource()).getScene().getWindow()).close());
        addAnimal.setOnAction(addAnimals->{
            animalType.setVisible(true);
            ObservableList<String> options =
                    FXCollections.observableArrayList(
                            "Lion","Monkey","Snake"
                    );
            animalType.getItems().addAll(options);
            animalType.getSelectionModel().selectFirst();

            ObservableList<Integer> optionsCage = FXCollections.observableList(
                    Main.zoo.getCages()
                );
            addCage.setVisible(true);
            addCage.getItems().addAll(optionsCage);
            addCage.getSelectionModel().selectLast();
            ObservableList<Integer> options2 =
                    FXCollections.observableArrayList(
                            1,2,3,4,5,6,7,8,9,10
                    );
            addAnimalSize.setVisible(true);
            addAnimalSize.getItems().addAll(options2);
            addAnimalSize.getSelectionModel().select(5);
            ObservableList<Food> options1 =
                    FXCollections.observableArrayList(
                            Food.fruits,Food.meats,Food.worms,Food.insects,Food.seeds,Food.grains,Food.plants
                    );
            foodType.setVisible(true);
            foodType.getItems().addAll(options1);
            foodType.getSelectionModel().selectFirst();
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
            addAnimalSize.setVisible(false);
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