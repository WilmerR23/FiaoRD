package com.example.fiaoRD.ui.TipoUsuario;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fiaoRD.R;
import com.example.fiaoRD.ui.BaseFragment;
import com.example.fiaoRD.ui.PrestamistaColmaderoCodigo.PrestamistaColmaderoCodigo;

public class TipoUsuario extends BaseFragment implements View.OnClickListener {

    private TipoUsuarioViewModel mViewModel;
    private Button btnColmadero, btnPrestamista, btnCliente;

    public static TipoUsuario newInstance() {
        return new TipoUsuario();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tipo_usuario_fragment, container, false);
        btnCliente = v.findViewById(R.id.btnCliente);
      //  btnPrestamista = v.findViewById(R.id.btnPrestamista);
        btnColmadero = v.findViewById(R.id.btnColmadero);

        btnCliente.setOnClickListener(this);
      //  btnPrestamista.setOnClickListener(this);
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

                break;
         //   case R.id.btnPrestamista:

          //      break;
        }
    }
}
