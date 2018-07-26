package UX;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AnimalsController {
    @FXML
    private Button close;
    @FXML
    private Button addAnimal;
    @FXML
    private TextField addName;
    @FXML
    private TextField addCage;
    @FXML
    private TextField addCzxageSize;
    @FXML
    private TextField addFoodTyepe;
    @FXML
    private TextField addMaxFoodAmount;
    @FXML
    private Button add;
    @FXML
    private Button cancelAdding;
    @FXML
    private void initialize() {
        addName.setVisible(false);
        addCage.setVisible(false);
        addCzxageSize.setVisible(false);
        addFoodTyepe.setVisible(false);
        addMaxFoodAmount.setVisible(false);
        add.setVisible(false);
        cancelAdding.setVisible(false);

        close.setOnAction(close->((Stage) ((Button) close.getSource()).getScene().getWindow()).close());
        addAnimal.setOnAction(addAnimals->{
            addName.setVisible(true);
            addCage.setVisible(true);
            addCzxageSize.setVisible(true);
            addFoodTyepe.setVisible(true);
            addMaxFoodAmount.setVisible(true);
            add.setVisible(true);
            cancelAdding.setVisible(true);
            addAnimal.setDisable(true);

        });
        cancelAdding.setOnAction(cancel->{
            addName.setVisible(false);
            addCage.setVisible(false);
            addCzxageSize.setVisible(false);
            addFoodTyepe.setVisible(false);
            addMaxFoodAmount.setVisible(false);
            add.setVisible(false);
            cancelAdding.setVisible(false);
            addAnimal.setDisable(false);

        });
    }

}