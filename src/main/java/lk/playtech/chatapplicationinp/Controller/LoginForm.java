package lk.playtech.chatapplicationinp.Controller;

import com.jfoenix.controls.JFXButton;
import impl.org.controlsfx.spreadsheet.FocusModelListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginForm {

    @FXML
    private JFXButton btnAddPp;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private ImageView imgProPic;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane root;

    @FXML
    void btnAddPpOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane=FXMLLoader.load(getClass().getResource("/view/client.fxml"));



    }

}
