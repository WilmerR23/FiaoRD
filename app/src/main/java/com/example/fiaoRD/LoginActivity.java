package com.example.fiaoRD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fiaoRD.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRD.ui.login.LoginFragment;
import com.example.fiaoRD.ui.register.Register;

public class LoginActivity extends AppCompatActivity implements OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        if (savedInstanceState == null) {
           getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance()).commit();
        }
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
}
