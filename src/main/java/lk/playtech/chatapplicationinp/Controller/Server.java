package lk.playtech.chatapplicationinp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;
    Server server;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String messege;

    @FXML
    private TextField txtAreaChat;

    public void initialize() {
        txtAreaChat.setEditable(false);
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(3000);
                txtAreaChat.appendText("Server Started");
                socket = serverSocket.accept();
                txtAreaChat.appendText("\nClient Connected");
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (!messege.equals("END")) {
                    messege = dataInputStream.readUTF();
                    txtAreaChat.appendText("\nClient : " + messege);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}