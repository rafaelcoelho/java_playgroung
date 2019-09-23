package com.rscoelho.java.functional;

@FunctionalInterface
public interface IMethodPattern<T, X extends Throwable> {

	void accept(T instance) throws X;
}
