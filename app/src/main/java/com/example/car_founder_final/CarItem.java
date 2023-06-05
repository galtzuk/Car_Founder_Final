package com.example.car_founder_final;

import android.graphics.Bitmap;
import android.widget.Button;

public class CarItem {

    private int image;
    private String text;
    public CarItem(int image ,String text ) {
        this.image = image;
        this.text = text;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;

    }

    public void setImage(int image) {
        this.image = image;

    }
}
