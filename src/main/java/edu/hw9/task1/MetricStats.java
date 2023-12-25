package edu.hw9.task1;

public class MetricStats {
    private final String metricName;
    private final double sum;
    private final double average;
    private final double min;
    private final double max;

    public MetricStats(String metricName, double sum, double average, double min, double max) {
        this.metricName = metricName;
        this.sum = sum;
        this.average = average;
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "MetricStats{"
            + "metricName='" + metricName + '\''
            + ", sum=" + sum
            + ", average=" + average
            + ", min=" + min
            + ", max=" + max
            + '}';
    }
}
