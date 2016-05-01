package com.kickstarter.extentions;

@FunctionalInterface
public interface Action1T<T> {
    void run(T x) throws Exception;
}
