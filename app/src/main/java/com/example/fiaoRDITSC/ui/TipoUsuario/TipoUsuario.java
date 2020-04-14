package com.example.fiaoRDITSC.ui.TipoUsuario;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fiaoRDITSC.Interfaces.OnFragmentListener;
import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.PrestamistaAsignacionCodigo.PrestamistaAsignacionCodigo;
import com.example.fiaoRDITSC.ui.PrestamistaColmaderoCodigo.PrestamistaColmaderoCodigo;

public class TipoUsuario extends BaseFragment implements View.OnClickListener, OnFragmentListener {

    private TipoUsuarioViewModel mViewModel;
    private Button btnColmadero, btnCliente;

    public static TipoUsuario newInstance() { return new TipoUsuario(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

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
