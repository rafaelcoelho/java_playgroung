package com.rscoelho.java.functional.parallel.stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

public class YahooFinance {

    public static BigDecimal getPrice(final String ticker) {
        try {
            final URL url = new URL("https://quantprice.herokuapp.com/api/v1.1/scoop/period?tickers=" + ticker);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            final String data = reader.lines().skip(1).findFirst().get();
            final String[] dataItems = data.split(",");
            return new BigDecimal(dataItems[dataItems.length - 1]);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
