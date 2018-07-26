package UX;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CagesController {
    @FXML
    private Button close;
    @FXML
    private TextField addNumOfAnimals;
    @FXML
    private TextField addSpecy ;
    @FXML
    private Button add;

    @FXML
    private Button cancel;

    @FXML
    private Button addCage;

    @FXML
    private void initialize() {
        addNumOfAnimals.setVisible(false);
        addSpecy.setVisible(false);
        add.setVisible(false);
        cancel.setVisible(false);
        close.setOnAction(close->((Stage) ((Button) close.getSource()).getScene().getWindow()).close());
        addCage.setOnAction(addCages->{
            addNumOfAnimals.setVisible(true);
            addSpecy.setVisible(true);
            add.setVisible(true);
            cancel.setVisible(true);
            addCage.setDisable(true);
        });
        cancel.setOnAction(cancelAdding->{
            addNumOfAnimals.setVisible(false);
            addSpecy.setVisible(false);
            add.setVisible(false);
            cancel.setVisible(false);
            addCage.setDisable(false);

        });
    }
}
