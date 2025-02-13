package com.example;

import java.io.*;
import java.net.*;

public class PlayerServer {
    private static final int PORT = 5000;  // Server port
    private int messageCount = 0;
    private static final int MAX_MESSAGES = 10;

    public static void main(String[] args) {
        new PlayerServer().start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("PlayerServer started. Waiting for messages...");

            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Server received: " + message);

                    if (messageCount >= MAX_MESSAGES) {
                        System.out.println("Server reached message limit. Closing connection.");
                        break;
                    }

                    messageCount++;
                    String response = message + " " + messageCount;
                    out.println(response);
                    System.out.println("Server sent: " + response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
