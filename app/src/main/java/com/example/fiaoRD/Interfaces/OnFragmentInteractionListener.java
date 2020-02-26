package com.example.fiaoRD.Interfaces;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

public interface OnFragmentInteractionListener {
    void onCallFragment(Fragment frg);
    void onMakeToast(String text, int Duration);
    String Save(ViewModel vm, String id, String parent, final String mensaje);
    void Obtener(String id, String child, Class clase, OnFragmentListener listener);
    void onDataFound(Object obj, OnFragmentListener listener);
    void onCallIntent(Class clase);
    String UpdateKey(String parent,String key, Object value, String mensaje);
}