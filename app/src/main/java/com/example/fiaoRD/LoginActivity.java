package com.example.fiaoRD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fiaoRD.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRD.Interfaces.OnFragmentListener;
import com.example.fiaoRD.ui.login.LoginFragment;
import com.example.fiaoRD.ui.register.Register;

public class LoginActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private Firebase _Firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        if (savedInstanceState == null) {
           getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance()).commit();
        }

        _Firebase = new Firebase();
    }

    @Override
    public void onCallFragment(Fragment frg) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, frg).commit();
    }

    @Override
    public void onMakeToast(String text, int Duration) {
        Toast.makeText(this,text,Duration).show();
    }

    @Override
    public String Save(ViewModel vm, String id, String parent, String mensaje) {
        return _Firebase.Save(vm,id,parent,mensaje);
    }

    @Override
    public void Obtener(String id, String child, Class clase, OnFragmentListener listener) {
        _Firebase.Obtener(id, child, clase,this, listener);
    }

    @Override
    public void onDataFound(Object obj, OnFragmentListener listener) {
        listener.receiveData(obj);
    }

    @Override
    public void onCallIntent(Class clase) {
        Intent i = new Intent(LoginActivity.this, clase);
        startActivity(i);
    }
}
