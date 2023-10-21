package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final static Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws Exception {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) throws Exception {
        LOGGER.info("Выполнение команды: " + command);
        Exception lastException = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try (Connection connection = manager.getConnection()) {
                LOGGER.info("Подключение к соединению. Попытка №" + attempt);
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                lastException = e;
            }
        }

        LOGGER.info("Не удалось выполнить команду");
        throw new ConnectionException("Не удалось выполнить команду после " + maxAttempts + " попыток.", lastException);

    }
}
