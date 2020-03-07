package com.example.fiaoRDITSC.Interfaces;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.fiaoRDITSC.Models.BaseModel;
import com.example.fiaoRDITSC.ui.BaseFragment;

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
    void onCallIntentWithData(Class clase, BaseModel bm);
    String UpdateKey(List<String> parent,String key, Object value, String mensaje);
    Context getActivityContext();
    Object getBaseModel();
    void onCallFragmentKey(int key, Fragment frg);
}