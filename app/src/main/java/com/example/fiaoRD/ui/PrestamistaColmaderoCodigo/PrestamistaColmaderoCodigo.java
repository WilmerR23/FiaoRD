package com.example.fiaoRD.ui.PrestamistaColmaderoCodigo;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fiaoRD.Firebase;
import com.example.fiaoRD.MainActivity;
import com.example.fiaoRD.R;
import com.example.fiaoRD.ui.BaseFragment;
import com.example.fiaoRD.ui.PrestamistaAsignacionCodigo.PrestamistaAsignacionCodigo;
import com.example.fiaoRD.ui.PrestamistaAsignacionCodigo.PrestamistaAsignacionCodigoViewModel;
import com.example.fiaoRD.ui.login.LoginFragment;

import java.util.ArrayList;

public class PrestamistaColmaderoCodigo extends BaseFragment {

    private PrestamistaColmaderoCodigoViewModel mViewModel;
    private Button btnIngresar;
    private EditText edCodigo;
    private String key;

    public static PrestamistaColmaderoCodigo newInstance() {
        return new PrestamistaColmaderoCodigo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.codigo_prestamista_colmadero_fragment, container, false);
        btnIngresar = v.findViewById(R.id.btnIngresar);
        edCodigo = v.findViewById(R.id.etCodigo);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edCodigo.getText().toString().length() >= 4) {
                    _Firebase = new Firebase();
                    _Firebase.ObtenerKeyPorFiltro("codigo",edCodigo.getText().toString(),"CodigoUsuarioPrestamista",
                            PrestamistaAsignacionCodigoViewModel.class,PrestamistaColmaderoCodigo.this);
                } else {
                    mListener.onMakeToast("Debes ingresar un codigo valido de 4 digitos", Toast.LENGTH_SHORT);
                }
            }
        });
        return v;
    }

    @Override
    public void receiveObtenerPorFiltroData(Object obj) {
       if (obj != null) {
            key = (String) obj;
           _Firebase.ExisteValor(key,"PrestamistaClientes","cliente",LoginFragment.id,this);
        } else {
            mListener.onMakeToast("Este codigo no existe.", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void receiveExisteValor(boolean valor) {

        if (valor) {
            mListener.onMakeToast("Ya eres cliente de este prestamista.", Toast.LENGTH_LONG);
        } else {

            ArrayList lista = new ArrayList();

            lista.add("Usuarios");
            lista.add(LoginFragment.id);
            String msj = mListener.UpdateKey(lista,"primerIngreso", false, null);
            PrestamistaColmaderoCodigoViewModel vm = new PrestamistaColmaderoCodigoViewModel(LoginFragment.id,LoginFragment.Descripcion);
            _Firebase.SaveBaseModelPush(vm,key,"PrestamistaClientes","");
            mListener.onCallIntent(MainActivity.class);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PrestamistaColmaderoCodigoViewModel.class);
        // TODO: Use the ViewModel
    }

}
