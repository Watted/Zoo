package UX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.com.zoo.siraj.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AnimalsController {
    @FXML
    private Label lablAnimalType,lablId,lablCage,lablEmployee,lablSize,lablFoodType,lablMax;
    @FXML
    private Button close;
    @FXML
    private Button addAnimal;
    @FXML
    private ComboBox animalType;
    @FXML
    private ComboBox addCage,addEmployee;
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
    public Button romverAnimal;
    @FXML
    private TreeView listOfAnimals;
    @FXML
    private void initialize() {
        uploadDetails();
        listOfAnimals.setMouseTransparent(false);
        animalType.setVisible(false);
        addCage.setVisible(false);
        addEmployee.setVisible(false);
        addAnimalSize.setVisible(false);
        foodType.setVisible(false);
        maxFoodAmount.setVisible(false);
        add.setVisible(false);
        cancelAdding.setVisible(false);


        setToTreeView();

        listOfAnimals.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                showDetails();
            }
        });
        romverAnimal.setOnAction(remove ->{
            removeAnimal();
        });

        close.setOnAction(close->((Stage) ((Button) close.getSource()).getScene().getWindow()).close());
        addAnimal.setOnAction(addAnimals->{
            listOfAnimals.setMouseTransparent(true);
            animalType.setVisible(true);
            addCage.setVisible(true);
            addEmployee.setVisible(true);
            addAnimalSize.setVisible(true);
            lablFoodType.setVisible(true);
            foodType.setVisible(true);
            lablMax.setVisible(true);
            maxFoodAmount.setVisible(true);
            add.setVisible(true);
            cancelAdding.setVisible(true);
            addAnimal.setDisable(true);

        });
        cancelAdding.setOnAction(cancel->{
            disableDetails();

        });
        add.setOnAction(add->{
            addAnimalToTheList();
            disableDetails();
        });
        feedAnimal.setOnAction(feeding->{
            Application app = new Application() {
                @Override
                public void start(Stage primaryStage) throws Exception {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("FeedAnimal.fxml"));
                    try {
                        Parent root = loader.load();
                        primaryStage.setTitle("Feed Animal");
                        Scene primScene = new Scene(root, 700,400);
                        primaryStage.setScene(primScene);
                        primaryStage.show();
                        FeedAnimal feedAnimalController = loader.getController();

                        TreeItem item = (TreeItem) listOfAnimals.getSelectionModel().getSelectedItem();
                        Animal animalById = Main.zoo.getAnimalById((String) item.getValue());
                        Employee employee1 = Main.zoo.getEmployeeToThisAnimal(animalById);

                        feedAnimalController.setDetails(animalById,employee1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            };
            try {
                app.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private void disableDetails() {
        listOfAnimals.setMouseTransparent(false);
        animalType.setVisible(false);
        addCage.setVisible(false);
        addEmployee.setVisible(false);
        addAnimalSize.setVisible(false);
        foodType.setVisible(false);
        maxFoodAmount.setVisible(false);
        add.setVisible(false);
        cancelAdding.setVisible(false);
        addAnimal.setDisable(false);
    }

    private void uploadDetails() {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Lion","Monkey","Snake"
                );
        animalType.getItems().addAll(options);
        animalType.getSelectionModel().selectFirst();

        ObservableList<Integer> optionsCage = FXCollections.observableList(
                Main.zoo.getCages()
        );
        addCage.getItems().addAll(optionsCage);
        addCage.getSelectionModel().selectLast();
        ObservableList<String> optionEmployee = FXCollections.observableList(
                Main.zoo.getEmployees()
        );
        addEmployee.getItems().addAll(optionEmployee);
        addEmployee.getSelectionModel().selectLast();
        ObservableList<Integer> options2 =
                FXCollections.observableArrayList(
                        1,2,3,4,5,6,7,8,9,10
                );
        addAnimalSize.getItems().addAll(options2);
        addAnimalSize.getSelectionModel().select(5);
        ObservableList<Food> options1 =
                FXCollections.observableArrayList(
                        Food.fruits,Food.meats,Food.worms,Food.insects,Food.seeds,Food.grains,Food.plants
                );
        foodType.getItems().addAll(options1);
        foodType.getSelectionModel().selectFirst();
        maxFoodAmount.getItems().addAll(options2);
        maxFoodAmount.getSelectionModel().select(5);
    }

    private void setToTreeView() {
        ArrayList<TreeItem> product = getAnimals();
        TreeItem rootItem = new TreeItem("Animals");
        rootItem.getChildren().addAll(product);
        listOfAnimals.setRoot(rootItem);
    }

    private void addAnimalToTheList() {
        Cage cageWithThisSize = Main.zoo.getCageWithThisSize((Integer) addCage.getSelectionModel().getSelectedItem());
        Employee employeeWithThisId = Main.zoo.getEmployeeWithThisId((String) addEmployee.getSelectionModel().getSelectedItem());
        if (animalType.getSelectionModel().getSelectedItem().equals("Snake")){
            Animal snake = new Snake((Integer) addAnimalSize.getSelectionModel().getSelectedItem());
            snake.addFood((Food) foodType.getSelectionModel().getSelectedItem(),(Integer)maxFoodAmount.getSelectionModel().getSelectedItem());
            Main.zoo.addAnimalToCage(snake,cageWithThisSize);
            Main.zoo.addToTreatmentEmployee(employeeWithThisId,snake);
        }else if (animalType.getSelectionModel().getSelectedItem().equals("Lion")){
            Animal lion = new Lion((Integer) addAnimalSize.getSelectionModel().getSelectedItem());
            lion.addFood((Food) foodType.getSelectionModel().getSelectedItem(),(Integer)maxFoodAmount.getSelectionModel().getSelectedItem());
            Main.zoo.addAnimalToCage(lion,cageWithThisSize);
            Main.zoo.addToTreatmentEmployee(employeeWithThisId,lion);
        }else{
            Animal monkey = new Monkey((Integer) addAnimalSize.getSelectionModel().getSelectedItem());
            monkey.addFood((Food) foodType.getSelectionModel().getSelectedItem(),(Integer)maxFoodAmount.getSelectionModel().getSelectedItem());
            Main.zoo.addAnimalToCage(monkey,cageWithThisSize);
            Main.zoo.addToTreatmentEmployee(employeeWithThisId,monkey);
        }
        setToTreeView();

    }

    private void showDetails(){
        TreeItem item = (TreeItem) listOfAnimals.getSelectionModel().getSelectedItem();
        if (item == null){
            return;
        }
        TreeItem parent = item.getParent();

        if (parent==null|| parent.getParent()==null){
            return;
        }else {
            Animal animalById = Main.zoo.getAnimalById((String) item.getValue());
            Cage cageToThisAnimal = Main.zoo.getCageToThisAnimal(animalById);
            Employee employeeToThisAnimal = Main.zoo.getEmployeeToThisAnimal(animalById);
            lablAnimalType.setText("Animal Type: " +animalById.getName());
            lablId.setText("ID: "+ animalById.getId());
            lablSize.setText("Animal Size: " + String.valueOf(animalById.getCageSize()));
            lablCage.setText("Cage:  ID: " + cageToThisAnimal.getId() + "  size: "+ cageToThisAnimal.getSize());
            lablEmployee.setText("Employee: ID: " + employeeToThisAnimal.getId());
            lablMax.setVisible(false);
            lablFoodType.setVisible(false);
        }
    }

    private void removeAnimal() {
        TreeItem item = (TreeItem) listOfAnimals.getSelectionModel().getSelectedItem();
        if (item == null){
            return;
        }
        TreeItem parent = item.getParent();

        if (parent==null|| parent.getParent()==null){
            return;
        }
        else {
            Main.zoo.removeAnimalById((String) item.getValue());
            parent.getChildren().remove(item);
            resetLabels();
        }
    }

    private void resetLabels() {
        lablMax.setVisible(true);
        lablFoodType.setVisible(true);
        lablSize.setText("Animal Size: ");
        lablCage.setText("Cage: ");
        lablEmployee.setText("Employee: ");
        lablId.setText("ID: ");
        lablAnimalType.setText("Animal Type: ");
    }

    private ArrayList<TreeItem> getAnimals() {
        ArrayList<TreeItem> animals = new ArrayList<>();

        TreeItem snake = new TreeItem("Snake");
        snake.getChildren().addAll(getSnake());


        TreeItem monkey = new TreeItem("Monkey");
        monkey.getChildren().addAll(getMonkey());

        TreeItem lion = new TreeItem("Lion");
        lion.getChildren().addAll(getLion());

        animals.add(snake);
        animals.add(monkey);
        animals.add(lion);

        return animals;
    }

    private ArrayList<TreeItem> getLion() {
        List<String> listLion = Main.zoo.getAnimals("Lion");
        Iterator<String> iteratorLion = listLion.iterator();
        ArrayList<TreeItem> lion = new ArrayList<>();
        while (iteratorLion.hasNext()){
            String next = iteratorLion.next();
            lion.add(new TreeItem(next));
        }
        return lion;

    }

    private ArrayList<TreeItem> getMonkey() {
        List<String> listMonkey = Main.zoo.getAnimals("Monkey");
        Iterator<String> iteratorMonkey = listMonkey.iterator();
        ArrayList<TreeItem> monkey = new ArrayList<>();
        while (iteratorMonkey.hasNext()){
            String next = iteratorMonkey.next();
            monkey.add(new TreeItem(next));
        }
        return monkey;
    }

    private ArrayList<TreeItem> getSnake() {
        List<String> listSnake = Main.zoo.getAnimals("Snake");
        Iterator<String> iteratorSnake = listSnake.iterator();
        ArrayList<TreeItem> snake = new ArrayList<>();
        while (iteratorSnake.hasNext()){
            String next = iteratorSnake.next();
            TreeItem temp = new TreeItem(next);
            //temp.addEventHandler();
            snake.add(temp);
        }
        return snake;
    }

}