package lk.playtech.chatapplicationinp.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.playtech.chatapplicationinp.dto.UserDTO;
import lk.playtech.chatapplicationinp.model.LoginModel;
import java.io.File;
import java.io.IOException;

public class Login extends Application {

    @FXML
    private JFXButton btnAddPp;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private ImageView imgProPic;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUserName;




    @FXML
    void btnAddPpOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a Profile Picture");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPEG image", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG image", "*.png"),
                new FileChooser.ExtensionFilter("All images", "*.jpg", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imgProPic.setImage(image);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login-form.fxml"))));
        stage.centerOnScreen();
        stage.setTitle("Welcome Form");
        stage.fullScreenProperty();
        stage.setResizable(false);

        stage.show();
    }


        @FXML
        void btnLoginOnAction (ActionEvent event) throws IOException {
            String username = txtUserName.getText();
            var dto = new UserDTO(username);

            try {
                boolean isSaved = LoginModel.saveUser(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Login Successfully!!!").show();
                    clearFields();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/client-chat-form.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();

                    // Get the controller of the Client class
                    Client clientController = fxmlLoader.getController();

                    // Set the username in the Client class
                    clientController.setClientName(username);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Login Unsuccessful!!!").show();
            }
        }


    private void clearFields() {

    }


}


