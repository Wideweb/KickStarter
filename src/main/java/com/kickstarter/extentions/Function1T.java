package com.kickstarter.extentions;

@FunctionalInterface
public interface Function1T<T, T1> {
    T getResult(T1 x) throws Exception;
}