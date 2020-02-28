package com.example.fiaoRD.ui.TipoUsuario;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fiaoRD.Interfaces.OnFragmentListener;
import com.example.fiaoRD.MainActivity;
import com.example.fiaoRD.R;
import com.example.fiaoRD.ui.BaseFragment;
import com.example.fiaoRD.ui.PrestamistaAsignacionCodigo.PrestamistaAsignacionCodigo;
import com.example.fiaoRD.ui.PrestamistaColmaderoCodigo.PrestamistaColmaderoCodigo;
import com.example.fiaoRD.ui.Utility;

import java.util.ArrayList;
import java.util.List;

public class TipoUsuario extends BaseFragment implements View.OnClickListener, OnFragmentListener {

    private TipoUsuarioViewModel mViewModel;
    private Button btnColmadero, btnCliente;

    public static TipoUsuario newInstance() { return new TipoUsuario(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tipo_usuario_fragment, container, false);
        btnCliente = v.findViewById(R.id.btnCliente);
        btnColmadero = v.findViewById(R.id.btnColmadero);

        btnCliente.setOnClickListener(this);
        btnColmadero.setOnClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TipoUsuarioViewModel.class);
        // TODO: Use the ViewModel

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCliente:
                mListener.onCallFragment(PrestamistaColmaderoCodigo.newInstance());
                break;
            case R.id.btnColmadero:
                mListener.onCallFragment(PrestamistaAsignacionCodigo.newInstance());
                break;
        }
    }

    @Override
    public void receiveChildrenCount(int count) {

    }
}
