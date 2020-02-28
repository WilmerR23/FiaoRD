package com.example.fiaoRD.Interfaces;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.fiaoRD.Models.BaseModel;
import com.example.fiaoRD.ui.BaseFragment;

import java.util.List;

public interface OnFragmentInteractionListener {
    void onCallFragment(Fragment frg);
    void onMakeToast(String text, int Duration);
    String Save(BaseModel vm, String id, String parent, final String mensaje);
    void Obtener(String id, String child, Class clase, BaseFragment listener);
    void ObtenerTodos(String id, String child, Class clase, BaseFragment listener);
    void onDataFound(Object obj, BaseFragment listener);
    void onDataTodosFound(List<Object> objs, BaseFragment listener);
    void onCallIntent(Class clase);
    String UpdateKey(List<String> parent,String key, Object value, String mensaje);
}