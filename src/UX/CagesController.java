package UX;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.com.zoo.siraj.Cage;

public class CagesController {
    @FXML
    private Button close;
    @FXML
    private ComboBox addNumOfAnimals;
    @FXML
    private Button add;

    @FXML
    private Button cancel;

    @FXML
    private Button addCage;

    @FXML
    private ListView cagesList;

    @FXML
    private Label cageIdL;

    @FXML
    private Label numberOfAnimalsL;
    @FXML
    private Label specyL ;

    @FXML
    private Button removeCage;
    @FXML
    private Label sizeL;
    @FXML
    private void initialize() {
        for(int i = 1 ; i<= 10 ; i++)
            addNumOfAnimals.getItems().add(i);
        for(Object cage : Main.zoo.getCagesMap().keySet().toArray()){
            cagesList.getItems().add((String) cage);
        }
        addNumOfAnimals.setVisible(false);
        add.setVisible(false);
        cancel.setVisible(false);
        close.setOnAction(close->((Stage) ((Button) close.getSource()).getScene().getWindow()).close());
        addCage.setOnAction(addCages->{
            addNumOfAnimals.setVisible(true);
            add.setVisible(true);
            cancel.setVisible(true);
            addCage.setDisable(true);
        });
        cancel.setOnAction(cancelAdding->{
            addNumOfAnimals.setVisible(false);
            add.setVisible(false);
            cancel.setVisible(false);
            addCage.setDisable(false);

        });
        cagesList.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            String ID = newValue;
            if(cagesList.getSelectionModel().getSelectedItem() != null) {
                Cage c = Main.zoo.getCagesMap().get(ID);
                cageIdL.setText(cageIdL.getText().substring(0, 9) + " " + ID);
                numberOfAnimalsL.setText(numberOfAnimalsL.getText().substring(0, 19) + " " + c.getContentAnimal().size());
                if (c.getContentAnimal().size() == 0)
                    specyL.setText(specyL.getText().substring(0, 7) + "  EMPTY");
                else
                    specyL.setText(specyL.getText().substring(0, 7) + " " + c.getContentAnimal().get(0).getName());
                sizeL.setText(sizeL.getText().substring(0, 11) + " " + c.getSize());
            }

        });
        add.setOnAction(addingCage->{
            Main.zoo.addCage(new Cage(Integer.parseInt(addNumOfAnimals.getSelectionModel().getSelectedItem().toString())));
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Cage Added Successfuly !");
                alert.showAndWait();
                for(Object cage : Main.zoo.getCagesMap().keySet().toArray()){
                    if(!cagesList.getItems().contains(cage))
                         cagesList.getItems().add((String) cage);
                }
                addNumOfAnimals.setVisible(false);
                add.setVisible(false);
                cancel.setVisible(false);
                addCage.setDisable(false);
            });
        });
        removeCage.setOnAction(action->{
            Main.zoo.removeCage(cagesList.getSelectionModel().getSelectedItems().get(0).toString());
            cagesList.getItems().clear();
            for(Object cage : Main.zoo.getCagesMap().keySet().toArray()){
                cagesList.getItems().add((String) cage);
            }
            cagesList.getSelectionModel().clearSelection();
        });
    }
}
