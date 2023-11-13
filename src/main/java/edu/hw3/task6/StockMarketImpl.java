package edu.hw3.task6;

import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {
    private final PriorityQueue<Stock> stockPriorityQueue;

    public StockMarketImpl() {
        stockPriorityQueue =
            new PriorityQueue<>((stock1, stock2) -> Double.compare(stock2.getPrice(), stock1.getPrice()));
    }

    @Override
    public void add(Stock stock) {
        stockPriorityQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockPriorityQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockPriorityQueue.peek();
    }
}
