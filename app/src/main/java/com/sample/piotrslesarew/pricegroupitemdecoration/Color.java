package com.sample.piotrslesarew.pricegroupitemdecoration;

import android.support.annotation.ColorInt;

public class Color {

    private int colorValue;
    private double price;

    public Color(@ColorInt int colorValue, double price) {
        this.colorValue = colorValue;
        this.price = price;
    }

    public int getColorValue() {
        return colorValue;
    }

    public double getPrice() {
        return price;
    }
}
