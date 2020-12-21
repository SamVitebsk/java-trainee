package com.andersen.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client {
    static final int PORT = 8088;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            System.out.println("Connected to server");

            while (true) {
                String message = consoleReader.readLine();
                if (Objects.nonNull(message) && message.trim().isEmpty() || message.equals("exit")) {
                    writer.write("exit");
                    writer.flush();
                    break;
                } else {
                    writer.write(message);
                    writer.newLine();
                    writer.flush();

                    System.out.println("[client] " + message);
                    String response = reader.readLine();
                    System.out.println(response);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("CLIENT Error", e);
        }

    }
}
