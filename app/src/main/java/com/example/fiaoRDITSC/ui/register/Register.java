package com.example.fiaoRDITSC.ui.register;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.util.Pair;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.Utility;
import com.example.fiaoRDITSC.ui.login.LoginFragment;

import java.security.NoSuchAlgorithmException;


public class Register extends BaseFragment implements View.OnClickListener {

    private RegisterViewModel mViewModel;
    private EditText  txtNombre, txtApellido, txtCedula, txtTelefono, txtDireccion, txtCorreo, txtClave, txtConfirmarClave;
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
                        if (txtCedula.getText().toString().length() == 11) {
                            if (txtTelefono.getText().toString().length() == 10) {
                                if(isValidEmail(txtCorreo.getText().toString())) {
                                    if (txtClave.getText().toString().equals(txtConfirmarClave.getText().toString())) {
                                        Pair<String,Boolean> par = Utility.validarClave(txtClave.getText().toString());
                                        if (par.second){

                                            _Firebase.ExisteKey(Utility.encodeForFirebaseKey(txtCorreo.getText().toString()), "Usuarios", this);

                                        } else {
                                            mListener.onMakeToast(par.first, Toast.LENGTH_SHORT);
                                        }
                                    } else {
                                        mListener.onMakeToast("Las contraseñas no coinciden.", Toast.LENGTH_SHORT);
                                    }
                                } else {
                                    mListener.onMakeToast("El campo correo debe contener un correo valido", Toast.LENGTH_SHORT);
                                }
                            } else {
                                mListener.onMakeToast("El campo telefono debe contener 10 digitos", Toast.LENGTH_SHORT);
                            }
                        } else {
                            mListener.onMakeToast("El campo cedula debe contener 11 digitos", Toast.LENGTH_SHORT);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void receiveExisteKey(boolean valor) {
        if (valor) {
            mListener.onMakeToast("Ya existe un usuario con este correo.", Toast.LENGTH_LONG);
        } else {
            RegisterViewModel vm = null;
            try {
                vm = new RegisterViewModel(
                        txtNombre.getText().toString(),
                        txtApellido.getText().toString(),
                        txtCedula.getText().toString(),
                        txtTelefono.getText().toString(),
                        txtDireccion.getText().toString(),
                        txtCorreo.getText().toString(),
                        Utility.FormatToSha(txtClave.getText().toString()),
                        true
                );
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                mListener.onMakeToast(e.getMessage(), Toast.LENGTH_LONG);
            }

            String cadena = mListener.Save(vm, Utility.encodeForFirebaseKey(vm.getCorreo()), "Usuarios", "Usuario registrado con exito.");

            mListener.onCallFragment(LoginFragment.newInstance());
            mListener.onMakeToast(cadena, Toast.LENGTH_SHORT);
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
