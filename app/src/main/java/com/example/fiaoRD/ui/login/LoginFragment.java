package com.example.fiaoRD.ui.login;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fiaoRD.Firebase;
import com.example.fiaoRD.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRD.Interfaces.OnFragmentListener;
import com.example.fiaoRD.MainActivity;
import com.example.fiaoRD.R;
import com.example.fiaoRD.ui.BaseFragment;
import com.example.fiaoRD.ui.TipoUsuario.TipoUsuario;
import com.example.fiaoRD.ui.Utility;
import com.example.fiaoRD.ui.register.Register;
import com.example.fiaoRD.ui.register.RegisterViewModel;

import java.util.List;

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private RegisterViewModel vm;
    private TextView txtRegistrarse;
    private EditText txtCorreo, txtClave;
    private Button btnIniciarSesion;
    public static String id = "";
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment2, container, false);
        txtRegistrarse = v.findViewById(R.id.txtRegistrate);

        btnIniciarSesion = v.findViewById(R.id.btnIniciaSesion);

        txtCorreo = v.findViewById(R.id.txtCorreoLogin);
        txtClave = v.findViewById(R.id.txtClaveLogin);

        btnIniciarSesion.setOnClickListener(this);
        txtRegistrarse.setOnClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIniciaSesion:
                if (txtClave.getText().toString().length() > 0 && txtCorreo.getText().toString().length() > 0) {
                    mListener.Obtener(Utility.encodeForFirebaseKey(txtCorreo.getText().toString()),"Usuarios", RegisterViewModel.class, this);
                }
                break;
            case R.id.txtRegistrate:
                mListener.onCallFragment(Register.newInstance());
                break;
        }
    }

    @Override
    public void receiveData(Object obj) {
        vm = (RegisterViewModel) obj;

        if (vm.getClave().equals(txtClave.getText().toString())) {
            id = Utility.encodeForFirebaseKey(vm.getCorreo());
           if (vm.getPrimerIngreso()) {
               mListener.onCallFragment(TipoUsuario.newInstance());
           } else {
               mListener.onCallIntent(MainActivity.class);
           }
        }
    }

    @Override
    public void receiveDataTodos(List<Object> obj) {

    }

    @Override
    public void receiveChildrenCount(int count) {

    }
}
