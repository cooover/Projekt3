package application.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Label lbl_log;

    @FXML
    void loginAction(MouseEvent event) throws IOException {
    	Stage stageInfo = (Stage) lbl_log.getScene().getWindow();
		Parent parent =(Parent) FXMLLoader.load(getClass().getResource("/application/view/LoginView.fxml"));
		Scene sceneInfo = new Scene(parent);
		stageInfo.setScene(sceneInfo);
		stageInfo.setTitle("LOGOWANIE");
		stageInfo.getIcons().add(new Image("http://www.iconsplace.com/download/orange-database-512.png"));
		stageInfo.show();

    }

}

