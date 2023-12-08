package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;

public class StatsCollector {
    private final Map<String, List<Double>> data;

    StatsCollector() {
        this.data = new ConcurrentHashMap<>();
    }

    public void push(String metricName, double[] values) {
        data.compute(metricName, (key, existingValues) -> {
            List<Double> updatedValues = new ArrayList<>();

            if (existingValues != null) {
                updatedValues.addAll(existingValues);
            }

            for (double value : values) {
                updatedValues.add(value);
            }

            return updatedValues;
        });
    }

    public List<MetricStats> stats() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(data.size());
        List<Future<MetricStats>> futures = new ArrayList<>();

        for (Map.Entry<String, List<Double>> entry : data.entrySet()) {
            Callable<MetricStats> callable = getMetricStatsCallable(entry);

            futures.add(executor.submit(callable));
        }

        executor.shutdown();

        List<MetricStats> result = new ArrayList<>();
        for (Future<MetricStats> future : futures) {
            result.add(future.get());
        }

        return result;
    }

    @NotNull
    private static Callable<MetricStats> getMetricStatsCallable(Map.Entry<String, List<Double>> entry) {
        String metricName = entry.getKey();
        List<Double> values = entry.getValue();

        return () -> {
            double sum = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;

            for (double value : values) {
                sum += value;
                min = Math.min(min, value);
                max = Math.max(max, value);
            }

            double average = sum / values.size();

            return new MetricStats(metricName, sum, average, min, max);
        };
    }
}
