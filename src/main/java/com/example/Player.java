package com.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Player implements Runnable {
    private final String name;
    private final BlockingQueue<String> messageQueue;
    private Player otherPlayer;
    private int sentMessageCount;
    private boolean isInitiator;
    private static final int MAX_MESSAGES = 10;

    public Player(String name) {
        this.name = name;
        this.messageQueue = new LinkedBlockingQueue<>();
        this.sentMessageCount = 0;
    }

    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public void setInitiator(boolean initiator) {
        isInitiator = initiator;
    }

    public void sendMessage(String message) {
        otherPlayer.receiveMessage(message);
        sentMessageCount++;
    }

    public void receiveMessage(String message) {
        messageQueue.offer(message);
    }

    @Override
    public void run() {
        try {
            if (isInitiator) {
                sendMessage("Hello");
            }

            while (sentMessageCount < MAX_MESSAGES || !messageQueue.isEmpty()) {
                String receivedMessage = messageQueue.take();
                System.out.println(name + " received: " + receivedMessage);

                if (sentMessageCount < MAX_MESSAGES) {
                    String replyMessage = receivedMessage + " " + (sentMessageCount + 1);
                    sendMessage(replyMessage);
                    System.out.println(name + " sent: " + replyMessage);
                }
            }

            System.out.println(name + " finished communication.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(name + " was interrupted.");
        }
    }
}