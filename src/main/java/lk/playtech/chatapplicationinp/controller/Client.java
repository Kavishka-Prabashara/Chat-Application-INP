package lk.playtech.chatapplicationinp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    @FXML
    private JFXButton btnAddImage;

    @FXML
    private JFXButton btnEmojiPicker;

    @FXML
    private JFXButton btnSend;

    @FXML
    private AnchorPane clientRoot;

    @FXML
    private Label lblClientName;

    @FXML
    private GridPane paneGridforEmoji;

    @FXML
    private JFXTextArea txtChatArea;  // Single text area for all messages

    @FXML
    private TextField txtField;

    @FXML
    private ImageView clientProPic;

    private static final String SERVER_IP = "127.0.0.1"; // Replace with your server's IP
    private static final int SERVER_PORT = 5555;

    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public void initialize() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            // New code: Set the client's name (replace "YourClientName" with the actual client's name)
            setClientName("YourClientName");

            new Thread(this::listenForMessages).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // New method: Set the client's name
    public void setClientName(String username) {
        lblClientName.setText(username);
    }

    private void listenForMessages() {
        try {
            while (true) {
                String message = (String) inputStream.readObject();
                // Handle the received message, update the UI, etc.
                txtChatArea.appendText(message + "\n");  // Append the message to the single text area
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        String message = txtField.getText();

        // Append the message to the chat area
        txtChatArea.appendText(" You: " + message + "\n");

        // Send the message to the server
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear the text field after sending the message
        txtField.clear();
    }

    @FXML
    void btnAddImageOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmojiPickerOnAction(ActionEvent event) {

    }
}
