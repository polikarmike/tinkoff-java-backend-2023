package edu.hw2.Task3;

public class FaultyConnectionManager implements ConnectionManager {
    private final double connectionErrorProbability = 0.9;

    @Override
    public Connection getConnection() {
        return new FaultyConnection(connectionErrorProbability); // Example failure probability
    }
}
