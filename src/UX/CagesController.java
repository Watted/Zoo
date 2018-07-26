package UX;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CagesController {
    @FXML
    private Button close;
    @FXML
    private void initialize() {
        close.setOnAction(close->((Stage) ((Button) close.getSource()).getScene().getWindow()).close());
    }
}
