package com.rscoelho.java.functional.parallel.stream;

import java.util.Arrays;
import java.util.List;

public class Tickers {

    private static final List<String> symbols = Arrays.asList("HPE", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
            "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC");

    public static void main(String[] args) {
        final StockInfo stockInfo = symbols.stream()
                .map(StockUtil::getPrice)
                .filter(StockUtil.isPriceLessThan(500))
                .reduce(StockUtil::pickHigh)
                .get();

        System.out.println("High priced under $500 is " + stockInfo.price);
    }
}
