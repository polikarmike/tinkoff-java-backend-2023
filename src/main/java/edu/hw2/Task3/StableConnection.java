package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info("Команда успешно выполнена (StableConnection)");
    }

    @Override
    public void close() {
        LOGGER.info("Соединение завершено (StableConnection)");
    }
}
