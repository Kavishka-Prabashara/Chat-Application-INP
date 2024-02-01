package lk.playtech.chatapplicationinp.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static lk.playtech.chatapplicationinp.controller.Server.clients;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;
    private String clientName; // New: Store the client's name

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = (String) inputStream.readObject();

                // Include the sender's name in the broadcasted message
                String formattedMessage =+1+": " + message;

                // Broadcast the message to all clients
                Server.broadcastMessage(formattedMessage, this);
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle client disconnection
            clients.remove(this);
            System.out.println("Client disconnected: " + clientSocket);
        }
    }

    // Send a message to this client
    public void sendMessage(String message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // New: Get the client's name
    public String getClientName() {
        return clientName;
    }

    // New: Set the client's name
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
