package lk.playtech.chatapplicationinp.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
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
    private JFXTextArea txtChatArea;

    @FXML
    private TextField txtField;


        private String messege = "";

        private String reply = "";

        ServerSocket serverSocket;

        Socket socket;

        DataInputStream dataInputStream;

        DataOutputStream dataOutputStream;

        public void initialize() {
            txtChatArea.setEditable(false);
            new Thread(() -> {
                try {
                    serverSocket = new ServerSocket(3000);
                    txtChatArea.appendText("Server Started");
                    socket = serverSocket.accept();
                    txtChatArea.appendText("\nClient Connected");
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    while (!messege.equals("END")) {
                        messege = dataInputStream.readUTF();
                        txtChatArea.appendText("\nClient : " + messege);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        }
}



