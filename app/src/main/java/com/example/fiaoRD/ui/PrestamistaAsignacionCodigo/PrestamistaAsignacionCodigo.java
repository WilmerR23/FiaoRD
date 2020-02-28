package com.example.fiaoRD.ui.PrestamistaAsignacionCodigo;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fiaoRD.Firebase;
import com.example.fiaoRD.Interfaces.OnFragmentListener;
import com.example.fiaoRD.MainActivity;
import com.example.fiaoRD.R;
import com.example.fiaoRD.ui.BaseFragment;
import com.example.fiaoRD.ui.PrestamistaColmaderoCodigo.PrestamistaColmaderoCodigo;
import com.example.fiaoRD.ui.PrestamistaColmaderoCodigo.PrestamistaColmaderoCodigoViewModel;
import com.example.fiaoRD.ui.login.LoginFragment;

import java.util.ArrayList;
import java.util.List;

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
        mListener.Save(vm, LoginFragment.id,"CodigoUsuarioPrestamista","");
        etAsignacionCodigo.setText(codigo);
    }


}
