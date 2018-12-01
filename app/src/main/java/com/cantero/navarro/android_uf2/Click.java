package com.cantero.navarro.android_uf2;

import java.util.Date;

public class Click {
 private int number;
 private String name;

    public Click(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }
}
