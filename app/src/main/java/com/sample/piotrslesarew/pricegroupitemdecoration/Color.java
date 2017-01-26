package com.sample.piotrslesarew.pricegroupitemdecoration;

import android.support.annotation.ColorInt;

class Color {

    private int colorValue;
    private double price;

    Color(@ColorInt int colorValue, double price) {
        this.colorValue = colorValue;
        this.price = price;
    }

    int getColorValue() {
        return colorValue;
    }

    double getPrice() {
        return price;
    }
}