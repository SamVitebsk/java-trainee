package com.andersen.chat.server;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

@Slf4j
public class Server {
    private static final int PORT = 8088;

    public static void main(String[] args) {
        log.info("Server started");

        try (
                ServerSocket serverSocket = new ServerSocket(PORT);
                 Socket socket = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            log.info("Client connected");

            do {
                String request = reader.readLine();
                if (request.equals("exit")) {
                    log.info("server stopped");
                    break;
                }
                log.info("[client] " + request);

                String response = "[server] " + getRandomAnswer();
                log.info(request);

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
