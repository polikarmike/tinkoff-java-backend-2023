package edu.hw8.task1;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuoteServer implements Runnable {
    public QuoteServer() {}

    private final static Logger LOGGER = LogManager.getLogger();
    private static final int PORT = 6666;
    private static final int MAX_CONNECTIONS = 5;

    private volatile boolean isRunning = true;

    @SuppressWarnings("RegexpSinglelineJava")
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен. Ожидание подключений...");

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новое подключение: " + clientSocket.getInetAddress());


                executorService.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при принятии входящего подключения "
                + "или обработке клиента: " + e.getMessage(), e);
        } finally {
            executorService.shutdown();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
