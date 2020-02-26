package com.example.fiaoRD.ui.register;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.Toast;

import com.example.fiaoRD.Firebase;
import com.example.fiaoRD.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRD.R;
import com.example.fiaoRD.ui.BaseFragment;
import com.example.fiaoRD.ui.login.LoginFragment;


public class Register extends BaseFragment implements View.OnClickListener {

    private RegisterViewModel mViewModel;
    private EditText txtNombre, txtApellido, txtCedula, txtTelefono, txtDireccion, txtCorreo, txtClave, txtConfirmarClave;
    private Button btnRegistro;
    private TextView txtIniciaSesion;

    public static Register newInstance() {
        return new Register();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_fragment, container, false);
        txtNombre = v.findViewById(R.id.txtNombre);
        txtApellido = v.findViewById(R.id.txtApellido);
        txtCedula = v.findViewById(R.id.txtCedula);
        txtTelefono = v.findViewById(R.id.txtTelefono);
        txtDireccion = v.findViewById(R.id.txtDireccion);
        txtCorreo = v.findViewById(R.id.txtCorreo);
        txtClave = v.findViewById(R.id.txtClave);
        txtConfirmarClave = v.findViewById(R.id.txtConfirmarClave);

        btnRegistro = v.findViewById(R.id.btnRegistro);
        txtIniciaSesion = v.findViewById(R.id.txtIniciaSesion);

        btnRegistro.setOnClickListener(this);
        txtIniciaSesion.setOnClickListener(this);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
    }

    public boolean validarDatos() {
        return txtNombre.getText().toString().length() > 0
                && txtApellido.getText().toString().length() > 0
                && txtCedula.getText().toString().length() > 0
                && txtTelefono.getText().toString().length() > 0
                && txtDireccion.getText().toString().length() > 0
                && txtCorreo.getText().toString().length() > 0
                && txtClave.getText().toString().length() > 0
                && txtConfirmarClave.getText().toString().length() > 0;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistro:
                try {
                    if(validarDatos()) {
                        if (txtClave.getText().toString().equals(txtConfirmarClave.getText().toString())) {

                            RegisterViewModel vm = new RegisterViewModel(
                                    txtNombre.getText().toString(),
                                    txtApellido.getText().toString(),
                                    txtCedula.getText().toString(),
                                    txtTelefono.getText().toString(),
                                    txtDireccion.getText().toString(),
                                    txtCorreo.getText().toString(),
                                    txtClave.getText().toString(),
                                    true
                            );

                            String cadena = mListener.Save(vm, vm.getCorreo(), "Usuarios", "Usuario registrado con exito.");

                            mListener.onCallFragment(LoginFragment.newInstance());
                            mListener.onMakeToast(cadena, Toast.LENGTH_SHORT);

                        } else {
                            mListener.onMakeToast("Las contraseñas no coinciden.", Toast.LENGTH_SHORT);
                        }
                    } else {
                        mListener.onMakeToast("Debe completar todos los campos", Toast.LENGTH_SHORT);
                    }
                } catch (Exception ex) {
                    mListener.onMakeToast("Ha ocurrido un error: " + ex.getMessage(), Toast.LENGTH_LONG);
                }
                break;

            case R.id.txtIniciaSesion:
                mListener.onCallFragment(LoginFragment.newInstance());
                break;
        }
    }
}
