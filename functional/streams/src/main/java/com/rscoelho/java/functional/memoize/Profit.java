package com.rscoelho.java.functional.memoize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Profit {
    private final List<Integer> prices;

    public Profit(List<Integer> prices) {
        super();
        this.prices = prices;
    }

    public int maxProfit(final int packageLength) {
        final BiFunction<Function<Integer, Integer>, Integer, Integer> compute = (func, length) -> {
            int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;

            for (int i = 1; i < length; i++) {
                final int priceWhenCut = func.apply(i) + func.apply(length - i);

                if (profit < priceWhenCut)
                    profit = priceWhenCut;
            }

            return profit;
        };

        return callMemoized(compute, packageLength);
    }

    private <T, R> R callMemoized(BiFunction<Function<T, R>, T, R> function, final T input) {
        final Function<T, R> memoized = new Function<T, R>() {
            private final Map<T, R> resutl = new HashMap<>();

            @Override
            public R apply(final T input) {
                return resutl.computeIfAbsent(input, key -> function.apply(this, key));
            }
        };

        return memoized.apply(input);
    }
}
