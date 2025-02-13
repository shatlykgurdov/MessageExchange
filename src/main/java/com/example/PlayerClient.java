package com.example;

import java.io.*;
import java.net.*;

public class PlayerClient {
    private static final String SERVER_HOST = "127.0.0.1"; // Server IP
    private static final int PORT = 5000;  // Must match PlayerServer

    public static void main(String[] args) {
        new PlayerClient().start();
    }

    public void start() {
        try (Socket socket = new Socket(SERVER_HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("PlayerClient connected. Sending first message...");
            String message = "Hello";
            out.println(message);
            System.out.println("Client sent: " + message);

            for (int i = 0; i < 10; i++) {
                String response = in.readLine();
                if (response == null) break;
                System.out.println("Client received: " + response);

                out.println(response);
                System.out.println("Client sent: " + response);
            }

            System.out.println("Client finished communication.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
