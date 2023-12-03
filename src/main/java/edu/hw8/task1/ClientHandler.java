package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private static final int BUFFER_SIZE = 1024;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String request = new String(buffer, 0, bytesRead);
                String response = QuoteService.getQuoteByKeyword(request);
                outputStream.write(response.getBytes());
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка ввода/вывода при обработке клиентского сокета: " + e.getMessage(), e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при закрытии клиентского сокета: " + e.getMessage(), e);
            }
        }
    }

}
