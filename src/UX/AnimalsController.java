package UX;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AnimalsController {
    @FXML
    private Button close;
    @FXML
    private Button addAnimal;
    @FXML
    private ComboBox animalType;
    @FXML
    private TextField addCage;
    @FXML
    private TextField addCzxageSize;
    @FXML
    private ComboBox foodType;
    @FXML
    private TextField addMaxFoodAmount;
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
        addMaxFoodAmount.setVisible(false);
        add.setVisible(false);
        cancelAdding.setVisible(false);

        close.setOnAction(close->((Stage) ((Button) close.getSource()).getScene().getWindow()).close());
        addAnimal.setOnAction(addAnimals->{
            animalType.setVisible(true);
            addCage.setVisible(true);
            addCzxageSize.setVisible(true);
            foodType.setVisible(true);
            addMaxFoodAmount.setVisible(true);
            add.setVisible(true);
            cancelAdding.setVisible(true);
            addAnimal.setDisable(true);

        });
        cancelAdding.setOnAction(cancel->{
            animalType.setVisible(false);
            addCage.setVisible(false);
            addCzxageSize.setVisible(false);
            foodType.setVisible(false);
            addMaxFoodAmount.setVisible(false);
            add.setVisible(false);
            cancelAdding.setVisible(false);
            addAnimal.setDisable(false);

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