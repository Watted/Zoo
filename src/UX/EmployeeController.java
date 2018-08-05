package UX;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.com.zoo.siraj.Employee;

import java.util.Arrays;

public class EmployeeController {
    @FXML
    private Button close;
    @FXML
    private TextField addName;
    @FXML
    private Button cancel;
    @FXML
    private Button add;
    @FXML
    private Button addEmployee;
    @FXML
    private ListView empList;
    @FXML
    private Label empNameL;
    @FXML
    private Label empIdL;
    @FXML
    private Label animalsL;
    @FXML
    private void initialize() {
        for(Object s: Main.zoo.getEmployeesMap().keySet().toArray()){
            empList.getItems().add(s);
        }
        addName.setVisible(false);
        cancel.setVisible(false);
        add.setVisible(false);

        close.setOnAction(close->((Stage) ((Button) close.getSource()).getScene().getWindow()).close());
        addEmployee.setOnAction(addEmp->{
            addName.setVisible(true);
            cancel.setVisible(true);
            add.setVisible(true);
            addEmployee.setDisable(true);
        });
        cancel.setOnAction(cancelAdding->{
            addName.setVisible(false);
            cancel.setVisible(false);
            add.setVisible(false);
            addEmployee.setDisable(false);
        });
        add.setOnAction(addingEmp->{
            if(!addName.getText().equals("")) {
                Main.zoo.addEmployee(new Employee(addName.getText()));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Employee Added Successfuly !");
                alert.showAndWait();
                for(Object s: Main.zoo.getEmployeesMap().keySet().toArray()){
                    if(!empList.getItems().contains(s))
                         empList.getItems().add(s);
                }
                addName.setVisible(false);
                cancel.setVisible(false);
                add.setVisible(false);
                addEmployee.setDisable(false);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Name Field Must Be Full to add Employee!");
                alert.showAndWait();
            }
        });
        empList.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            String ID = newValue;
            if(empList.getSelectionModel().getSelectedItem() != null) {
                Employee e = Main.zoo.getEmployeesMap().get(empList.getSelectionModel().getSelectedItem().toString());
                empNameL .setText(empNameL.getText().substring(0,15)+ " "  + e.getName());
                empIdL   .setText(empIdL  .getText().substring(0,4)+ " " + e.getId());
                animalsL .setText(animalsL.getText().substring(0,9)+ " " + Arrays.deepToString(e.getTreatmentAnimalsTypes().toArray()));
            }

        });
    }
}
