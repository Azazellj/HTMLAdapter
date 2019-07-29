package com.azazellj.recyclerview.adapter.html.base;

/**
 * Created by azazellj on 2/28/19.
 */
public enum TextGravity {

    CENTER("center"),
    END("end"),
    RIGHT("right");

    private final String name;

    TextGravity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
