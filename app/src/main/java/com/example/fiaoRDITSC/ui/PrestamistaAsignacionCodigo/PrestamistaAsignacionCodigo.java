package com.example.fiaoRDITSC.ui.PrestamistaAsignacionCodigo;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fiaoRDITSC.Firebase;
import com.example.fiaoRDITSC.MainActivity;
import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.home.HomeFragment;
import com.example.fiaoRDITSC.ui.login.LoginFragment;

import java.util.ArrayList;

public class PrestamistaAsignacionCodigo extends BaseFragment {

    private PrestamistaAsignacionCodigoViewModel mViewModel;
    private EditText etAsignacionCodigo;
    private Button btnIngresar;

    public static PrestamistaAsignacionCodigo newInstance() {
        return new PrestamistaAsignacionCodigo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.prestamista_asignacion_codigo_fragment, container, false);

        etAsignacionCodigo =  v.findViewById(R.id.etCodigoAsignado);
        btnIngresar = v.findViewById(R.id.btnAsignacionIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList lista = new ArrayList();
                lista.add("Usuarios");
                lista.add(LoginFragment.id);
                String msj = mListener.UpdateKey(lista,"primerIngreso", false, null);
                mListener.onCallIntent(MainActivity.class);
            }
        });
        _Firebase = new Firebase();

        _Firebase.ObtenerChildCount("CodigoUsuarioPrestamista",this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PrestamistaAsignacionCodigoViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void receiveChildrenCount(int count) {
        String codigo = String.format("%04d", count + 1);
        PrestamistaAsignacionCodigoViewModel vm = new PrestamistaAsignacionCodigoViewModel(codigo);
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("CodigoUsuarioPrestamista");
        String key = LoginFragment.id;
        lista.add(key);
        mListener.Save(vm, lista,"");
        etAsignacionCodigo.setText(codigo);
    }


}
