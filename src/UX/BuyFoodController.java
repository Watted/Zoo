package UX;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.java.com.zoo.siraj.Food;

import java.util.HashMap;
import java.util.Map;

public class BuyFoodController {
    @FXML
    private ComboBox type;
    @FXML
    private ComboBox amount;
    @FXML
    private Button buy;
    @FXML
    private Button cancel;
    @FXML
    private void initialize() {
        Map<String,Food> f = new HashMap<>();
        for (Food food : Food.values()) {
            f.put(food.toString(),food);
        }
        Platform.runLater(()->{
            type.getItems().addAll(Food.values());
            for(int i = 1 ; i < 11 ; i ++)
                amount.getItems().add(i);
        });
        cancel.setOnAction(clicked->{
            Stage thisStage = (Stage) ((Button) clicked.getSource()).getScene().getWindow();
            thisStage.close();
        });
        buy.setOnAction(buy->{

            if(Main.zoo.buyFood(f.get(type.getSelectionModel().getSelectedItem().toString()),
                    Integer.parseInt(amount.getSelectionModel().getSelectedItem().toString()),Main.day+"")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Food Brought Successfuly !");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("No Animal Eats That Shit !");
                alert.showAndWait();
            }
        });
    }
}
