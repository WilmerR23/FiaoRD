package com.example.fiaoRDITSC.Utility;

public class StringSpinnerMap {

    public int key;
    public Object tag;

    public StringSpinnerMap(int string, Object tag) {
        this.key = string;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag.toString();
    }
}


