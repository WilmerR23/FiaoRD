package com.example.fiaoRDITSC;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.fiaoRDITSC.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRDITSC.Models.BaseModel;
import com.example.fiaoRDITSC.Utility.MessageDialog;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private Firebase _Firebase;
    public String id = "";
    public Object vm;
    private BaseFragment currentFrag;
    public NavigationView navigationView;
    public Menu nav_Menu;

    public BaseActivity() {
        _Firebase = new Firebase();
    }

    @Override
    public void onCallFragment(Fragment frg) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, frg).commit();
    }

    public BaseFragment getCurrentFragment() {
        return currentFrag;
    }

    @Override
    public void onCallFragmentKey(BaseFragment old_frg, int key, BaseFragment frg, String title) {
        currentFrag = frg;
        old_frg.title = old_frg.title != null ? old_frg.title : getSupportActionBar().getTitle().toString();
        currentFrag.prev_Fragment = old_frg;
        getSupportFragmentManager().beginTransaction().replace(key,frg)
//                .hide(old_frg)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .add(key, frg)
                .addToBackStack(null)
                .commitAllowingStateLoss();

        getSupportActionBar().setTitle(title);
    }

    public void setCurrentFragment(BaseFragment frg) {
        this.currentFrag = frg;
    }

    @Override
    public void setCurrentFragment(BaseFragment frg, int item) {
        currentFrag = frg;
        if (navigationView != null) {
            navigationView.getMenu().getItem(item).setChecked(true);
        }
    }

    @Override
    public void onMakeDialog(final BaseFragment bf, MessageDialog mD, final Object paremeter) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle(mD.Title);
        dialogo1.setMessage(mD.Message);
        dialogo1.setCancelable(true);
        dialogo1.setPositiveButton(mD.PositiveButtonMessage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                    bf.DialogPositiveCallback(paremeter);
            }
        });
        dialogo1.setNegativeButton(mD.NegativeButtonMessage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.cancel();
            }
        });
        dialogo1.show();
    }

    @Override
    public Menu getMenu() {
        return nav_Menu;
    }


    @Override
    public void onMakeToast(String text, int Duration) {
        Toast.makeText(this,text,Duration).show();
    }

    @Override
    public String Save(BaseModel vm, ArrayList<String> parent, String mensaje) {
        return _Firebase.Save(vm,parent,mensaje);
    }

    @Override
    public void Obtener(ArrayList<String> parent, Class clase, BaseFragment listener) {
        _Firebase.Obtener(parent,clase,this, listener);
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
//        vm = obj;
        listener.receiveData(obj);
    }

    @Override
    public void onDataTodosFound(List<Object> objs, BaseFragment listener) {
        listener.receiveDataTodos(objs);
    }

    @Override
    public void onDataTodosOrdenadosFound(List<Object> objs, BaseFragment listener) {
        listener.receiveDataTodosOrdenados(objs);
    }


    @Override
    public void onCallIntent(Class clase) {
        Intent i = new Intent(this, clase);
        startActivity(i);
        finish();
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
