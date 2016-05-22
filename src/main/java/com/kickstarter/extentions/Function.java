package com.kickstarter.extentions;

@FunctionalInterface
public interface Function<T> {
    T getResult() throws Exception;
}