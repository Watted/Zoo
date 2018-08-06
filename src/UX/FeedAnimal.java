package UX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.java.com.zoo.siraj.Animal;
import main.java.com.zoo.siraj.Employee;
import main.java.com.zoo.siraj.Food;

import java.util.*;

public class FeedAnimal {
    @FXML
    private ComboBox type;
    @FXML
    private ComboBox amount;
    @FXML
    private Button feed;
    @FXML
    private Button cancel;

    private Animal animal;
    private Employee employee;

    @FXML
    private void initialize() {

        type.setOnAction(type->{
            updateDetails();
        });

        feed.setOnAction(feed->{
            Main.zoo.feedAnimal(employee,animal,(Food) type.getSelectionModel().getSelectedItem(),(Integer) amount.getSelectionModel().getSelectedItem());
            Stage thisStage = (Stage) ((Button) feed.getSource()).getScene().getWindow();
            thisStage.close();
        });

        cancel.setOnAction(cancelAction->{
            Stage thisStage = (Stage) ((Button) cancelAction.getSource()).getScene().getWindow();
            thisStage.close();
        });
    }

    private void updateDetails() {
        int maxFoodPerKind = this.animal.getMaxFoodPerKind((Food) this.type.getSelectionModel().getSelectedItem());
        List<Integer> amounts = new ArrayList<>();
        for (int i = 1; i <= maxFoodPerKind; i++) {
            amounts.add(i);
        }
        ObservableList<Integer> options =
                FXCollections.observableArrayList(
                        amounts
                );
        amount.getItems().clear();
        amount.getItems().addAll(options);
        amount.getSelectionModel().selectLast();
    }

    public void setDetails(Animal animal,Employee employee) {
        this.employee = employee;
        this.animal = animal;
        Map<Food, Integer> food = animal.getMaxFood();
        Set<Map.Entry<Food, Integer>> entries = food.entrySet();
        List<Food> listOfFood = new ArrayList<>();
        Iterator<Map.Entry<Food, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<Food, Integer> next = iterator.next();
            listOfFood.add(next.getKey());
        }
        ObservableList<Food> options1 =
                FXCollections.observableArrayList(
                        listOfFood
                );
        type.getItems().addAll(options1);
        type.getSelectionModel().selectLast();
        //updateDetails();
    }
}
