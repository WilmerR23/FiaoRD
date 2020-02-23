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
import com.example.fiaoRD.ui.register.Register;
import com.example.fiaoRD.ui.register.RegisterViewModel;

public class LoginFragment extends Fragment implements View.OnClickListener, OnFragmentListener {

    private LoginViewModel mViewModel;
    private TextView txtRegistrarse;
    private EditText txtCorreo, txtClave;
    private OnFragmentInteractionListener mListener;
    private Button btnIniciarSesion;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIniciaSesion:
                if (txtClave.getText().toString().length() > 0 && txtCorreo.getText().toString().length() > 0) {
                    mListener.Obtener(txtCorreo.getText().toString(),"Usuarios", RegisterViewModel.class, this);
                }
                break;
            case R.id.txtRegistrate:
                mListener.onCallFragment(Register.newInstance());
                break;
        }
    }

    @Override
    public void receiveData(Object obj) {
        RegisterViewModel vm = (RegisterViewModel) obj;

        if (vm.getClave().equals(txtClave.getText().toString())) {
            mListener.onCallIntent(MainActivity.class);
        }
    }
}
