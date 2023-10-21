package edu.hw2.Task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private final double failureProbability;
    private final Random random = new Random();

    public FaultyConnection(double failureProbability) {
        this.failureProbability = failureProbability;
    }

    @Override
    public void execute(String command) {
        if (random.nextDouble() < failureProbability) {
            throw new ConnectionException("Ошибка соединения (FaultyConnection)");
        }
        LOGGER.info("Команда успешно выполнена (FaultyConnection)");
    }

    @Override
    public void close() {
        LOGGER.info("Соединение завершено (FaultyConnection)");
    }
}
