package com.example.fiaoRD.Interfaces;

import java.util.List;

public interface OnFragmentListener {

    void receiveData(Object obj);
    void receiveDataTodos(List<Object> obj);
    void receiveChildrenCount(int count);
}
