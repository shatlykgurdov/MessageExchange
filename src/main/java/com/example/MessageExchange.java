package com.example;

/**
 * Implements message exchange with both players running in the same Java process.
 * This implementation follows requirement #5.
 *
 */

public class MessageExchange {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        player1.setOtherPlayer(player2);
        player2.setOtherPlayer(player1);

        player1.setInitiator(true);
        player2.setInitiator(false);

        Thread thread1 = new Thread(player1);
        Thread thread2 = new Thread(player2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted.");
        }

        System.out.println("Message exchange completed.");
    }
}