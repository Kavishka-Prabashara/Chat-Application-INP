package lk.playtech.chatapplicationinp.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GetStarted {


    @FXML
    private JFXButton btnGetStarted;

    @FXML
    private AnchorPane root;


    public void btnGetStartedOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login-form.fxml")));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();

    }
}
