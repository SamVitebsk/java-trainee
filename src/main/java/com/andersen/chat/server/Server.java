package com.andersen.chat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    static final int PORT = 8088;

    public static void main(String[] args) {
        System.out.println("Server started");
        try (
                ServerSocket serverSocket = new ServerSocket(PORT);
                 Socket socket = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            System.out.println("Client connected");

            do {
                String request = reader.readLine();
                if (request.equals("exit")) {
                    System.out.println("server stopped");
                    break;
                }
                System.out.println("[client] " + request);

                String response = "[server] " + getRandomAnswer();
                System.out.println(response);

                writer.write(response);
                writer.newLine();
                writer.flush();
            } while (true);


        } catch (Exception e) {
            throw new RuntimeException("SERVER error", e);
        }
    }

    private static String getRandomAnswer() {
        String[] answers = {"Hello!", "Hi", "Good bye", "How are you?"};

        return answers[new Random().nextInt(answers.length)];
    }
}
