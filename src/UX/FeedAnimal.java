package UX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class FeedAnimal {
    @FXML
    private ComboBox type;
    @FXML
    private ComboBox amount;
    @FXML
    private Button feed;
    @FXML
    private Button cancel;

    @FXML
    private void initialize() {
        ObservableList<Integer> options =
                FXCollections.observableArrayList(
                        1,2,3,4,5,6,7,8,9,10
                );
        amount.getItems().addAll(options);
        cancel.setOnAction(cancelAction->{
            Stage thisstage = (Stage) ((Button) cancelAction.getSource()).getScene().getWindow();
            thisstage.close();
        });
    }
}
