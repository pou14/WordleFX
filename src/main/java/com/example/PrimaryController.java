package com.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void switchToStatistics() throws IOException {
        App.setRoot("statistics");
    }

    @FXML
    private void switchToRule() throws IOException {
        App.setRoot("rules");
    }

    public static void setTitle(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTitle'");
    }
}
