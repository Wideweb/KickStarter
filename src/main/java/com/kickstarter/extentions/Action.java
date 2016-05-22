package com.kickstarter.extentions;

@FunctionalInterface
public interface Action {
    void run() throws Exception;
}