package com.rscoelho.java.functional.memoize;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProfitTest {
    private final List<Integer> prices = Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);
    private Profit profit;

    @Before
    public void setup() {
        profit = new Profit(prices);
    }

    @Test
    public void maxProfit_whenSizeIsFive_shouldReturnTen() {
        assertEquals(10, profit.maxProfit(5));
    }

    @Test
    public void maxProfit_whenSizeIsTwentyTwo_shouldReturnFortyFour() {
        assertEquals(44, profit.maxProfit(22));
    }

}
