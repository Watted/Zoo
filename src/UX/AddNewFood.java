package UX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.java.com.zoo.siraj.Animal;
import main.java.com.zoo.siraj.Food;

import java.util.*;

public class AddNewFood {
    @FXML
    private Button cancelbtn,addbtn;
    @FXML
    private ComboBox foodtypeCombo,amountCobo;

    private Animal animal;

    @FXML
    private void initialize() {

        addbtn.setOnAction(feed->{
            //Main.zoo.feedAnimal(employee,animal,(Food) type.getSelectionModel().getSelectedItem(),(Integer) amount.getSelectionModel().getSelectedItem());
            this.animal.addFood((Food) foodtypeCombo.getSelectionModel().getSelectedItem(), (Integer) amountCobo.getSelectionModel().getSelectedItem());
            Stage thisStage = (Stage) ((Button) feed.getSource()).getScene().getWindow();
            thisStage.close();
        });

        cancelbtn.setOnAction(cancelAction->{
            Stage thisStage = (Stage) ((Button) cancelAction.getSource()).getScene().getWindow();
            thisStage.close();
        });
    }


    public void setDetails(Animal animalById) {
        this.animal = animalById;
        ObservableList<Integer> options2 =
                FXCollections.observableArrayList(
                        1,2,3,4,5,6,7,8,9,10
                );
        List<Food> listOfFood = new ArrayList<>();
        Map<Food, Integer> foods = this.animal.getMaxFood();
        Set<Map.Entry<Food, Integer>> entries = foods.entrySet();
        int flag;
        for (int i = 0; i < Food.values().length; i++) {
            flag =0;
            Iterator<Map.Entry<Food, Integer>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Food, Integer> next = iterator.next();
                Food key = next.getKey();

                if (key.equals(Food.values()[i])) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                listOfFood.add(Food.values()[i]);
            }
        }

        //System.out.println(Food.fruits);
        ObservableList<Food> options1 =
                FXCollections.observableArrayList(
                        listOfFood
                );
        foodtypeCombo.getItems().addAll(options1);
        foodtypeCombo.getSelectionModel().selectFirst();
        amountCobo.getItems().addAll(options2);
        amountCobo.getSelectionModel().select(5);
    }
}
