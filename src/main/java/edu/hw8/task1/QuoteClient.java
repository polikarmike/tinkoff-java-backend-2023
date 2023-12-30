package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class QuoteClient implements Runnable {
    public QuoteClient() {}

    private volatile boolean isRunning = true;

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 6666;
    private static final int BUFFER_SIZE = 1024;

    @SuppressWarnings("RegexpSinglelineJava")
    @Override
    public void run() {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()) {

            Scanner scanner = new Scanner(System.in);

            while (isRunning) {
                System.out.println("Ваня: ");
                String request = scanner.nextLine();
                if ("exit".equalsIgnoreCase(request.trim())) {
                    break;
                }

                outputStream.write(request.getBytes());

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = inputStream.read(buffer);
                String response = new String(buffer, 0, bytesRead);
                System.out.println("Сервер: " + response);
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка ввода/вывода при работе с сокетом: " + e.getMessage(), e);
        }
    }

    public void stop() {
        isRunning = false;
    }
}

