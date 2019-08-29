package com.example.expensestracker.model;

public class spinneritem {
    private String kind;
    private int kind_img;
    public spinneritem(String kind, int kind_img) {
        this.kind = kind;
        this.kind_img = kind_img;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setKind_img(int kind_img) {
        this.kind_img = kind_img;
    }

    public int getKind_img() {
        return kind_img;
    }
}
