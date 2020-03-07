package com.example.fiaoRDITSC;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fiaoRDITSC.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRDITSC.Models.BaseModel;
import com.example.fiaoRDITSC.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private Firebase _Firebase;
    public String id = "";
    public Object vm;

    public BaseActivity() {
        _Firebase = new Firebase();
    }

    @Override
    public void onCallFragment(Fragment frg) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, frg).commit();
    }

    @Override
    public void onCallFragmentKey(int key, Fragment frg) {
        getSupportFragmentManager().beginTransaction()
                .replace(key, frg).commit();
    }



    @Override
    public void onMakeToast(String text, int Duration) {
        Toast.makeText(this,text,Duration).show();
    }

    @Override
    public String Save(BaseModel vm, String id, String parent, String mensaje) {
        return _Firebase.Save(vm,id == "" ? this.id : id,parent,mensaje);
    }

    @Override
    public void Obtener(String id, String child, Class clase, BaseFragment listener) {
        _Firebase.Obtener(id, child, clase,this, listener);
    }

    @Override
    public void ObtenerTodos(String id, String child, Class clase, BaseFragment listener) {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add(child);
        lista.add(this.id == "" ? id : this.id);
        _Firebase.ObtenerTodos(lista, clase,this, listener);
    }

    @Override
    public void onDataFound(Object obj, BaseFragment listener) {
        vm = obj;
        listener.receiveData(obj);
    }

    @Override
    public void onDataTodosFound(List<Object> objs, BaseFragment listener) {
        listener.receiveDataTodos(objs);
    }

    @Override
    public void onCallIntent(Class clase) {
        Intent i = new Intent(this, clase);
        startActivity(i);
    }

    @Override
    public void onCallIntentWithData(Class clase, BaseModel bm) {
        Intent i = new Intent(this, clase);
        i.putExtra("BaseModel", bm);
        startActivity(i);
    }

    @Override
    public String UpdateKey(List<String> parent, String key, Object value, String mensaje) {
        return _Firebase.UpdateKey(parent,key,value,mensaje);
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Object getBaseModel() {
        return vm;
    }
}
