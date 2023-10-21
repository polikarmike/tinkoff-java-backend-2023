package edu.hw2.Task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final double managerErrorProbability = 0.3;
    private final double connectionErrorProbability = 0.2;
    private final Random random = new Random();

    @Override
    public Connection getConnection() {
        if (random.nextDouble() < managerErrorProbability) {
            return new FaultyConnection(connectionErrorProbability);
        }

        return new StableConnection();
    }
}
