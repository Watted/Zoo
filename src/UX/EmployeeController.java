package UX;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmployeeController {
    @FXML
    private Button close;
    @FXML
    private TextField addName;
    @FXML
    private TextField addAnimals;
    @FXML
    private Button cancel;
    @FXML
    private Button add;
    @FXML
    private Button addEmployee;;
    @FXML
    private void initialize() {
        addName.setVisible(false);
        addAnimals.setVisible(false);
        cancel.setVisible(false);
        add.setVisible(false);

        close.setOnAction(close->((Stage) ((Button) close.getSource()).getScene().getWindow()).close());
        addEmployee.setOnAction(addEmp->{
            addName.setVisible(true);
            addAnimals.setVisible(true);
            cancel.setVisible(true);
            add.setVisible(true);
            addEmployee.setDisable(true);
        });
        cancel.setOnAction(cancelAdding->{
            addName.setVisible(false);
            addAnimals.setVisible(false);
            cancel.setVisible(false);
            add.setVisible(false);
            addEmployee.setDisable(false);

        });
    }
}
