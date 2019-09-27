package com.rscoelho.java.functional.tailcall.optimization;

import static com.rscoelho.java.functional.tailcall.optimization.TailCalls.call;
import static com.rscoelho.java.functional.tailcall.optimization.TailCalls.done;

import java.math.BigDecimal;

public class Factorial {

    public static void main(String[] args) {
        System.out.println("20000 is " + factorialTailRec(BigDecimal.ONE, BigDecimal.valueOf(200000000)).invoke());
    }

    public static TailCall<BigDecimal> factorialTailRec(final BigDecimal factorial, final BigDecimal number) {
        if (number.compareTo(BigDecimal.ONE) == 0) {
            return done(factorial);
        }

        return call(() -> factorialTailRec(factorial.multiply(number), number.subtract(BigDecimal.ONE)));
    }
}
