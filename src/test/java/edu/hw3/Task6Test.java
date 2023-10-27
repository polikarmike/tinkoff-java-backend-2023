package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import edu.hw3.task6.StockMarketImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Task6Test {
    private StockMarket stockMarket;

    @BeforeEach
    void setUp() {
        stockMarket = new StockMarketImpl();
    }

    @Test
    @DisplayName("Добавление акций и определение самой дорогой акции")
    void testAddStockAndMostValuableStock() {
        // Given
        Stock stock1 = new Stock("Company A", 100.0);
        Stock stock2 = new Stock("Company B", 200.0);
        Stock stock3 = new Stock("Company C", 150.0);

        // When
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);

        // Then
        Stock mostValuableStock = stockMarket.mostValuableStock();
        assertNotNull(mostValuableStock);
        assertEquals("Company B", mostValuableStock.getName());
        assertEquals(200.0, mostValuableStock.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Удаление акции")
    void testRemoveStock() {
        // Given
        Stock stock1 = new Stock("Company A", 100.0);
        Stock stock2 = new Stock("Company B", 200.0);
        Stock stock3 = new Stock("Company C", 150.0);

        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);

        // When
        stockMarket.remove(stock2);

        // Then
        Stock mostValuableStock = stockMarket.mostValuableStock();
        assertNotNull(mostValuableStock);
        assertEquals("Company C", mostValuableStock.getName());
        assertEquals(150.0, mostValuableStock.getPrice(), 0.001);
    }
}
