package edu.hw9.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatsCollectorTest {

    @Test
    @DisplayName("Проверка сбора статистики")
    void statsCollectorTest() throws ExecutionException, InterruptedException {
        // Given
        StatsCollector collector = new StatsCollector();

        double[] metric1Values = {0.1, 0.05, 1.4, 5.1, 0.3};
        double[] metric2Values = {2.5, 1.0, 3.5, 0.8, 2.0};

        // When
        collector.push("metric1", metric1Values);
        collector.push("metric2", metric2Values);

        List<MetricStats> metricStatsList = collector.stats();

        // Then
        assertEquals(2, metricStatsList.size());

        MetricStats metricStats1 = metricStatsList.get(0);
        String expectedMetricStats1 = "MetricStats{metricName='metric1', sum=6.949999999999999, average=1.39, min=0.05, max=5.1}";
        assertEquals(expectedMetricStats1, metricStats1.toString());

        String expectedMetricStats2 = "MetricStats{metricName='metric2', sum=9.8, average=1.9600000000000002, min=0.8, max=3.5}";
        MetricStats metricStats2 = metricStatsList.get(1);
        assertEquals(expectedMetricStats2, metricStats2.toString());

    }
}
