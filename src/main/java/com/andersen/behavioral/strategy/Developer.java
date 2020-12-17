package com.andersen.behavioral.strategy;

import lombok.Setter;

@Setter
public class Developer {
    private Activity activity;

    public void executeActivity() {
        activity.doIt();
    }}
