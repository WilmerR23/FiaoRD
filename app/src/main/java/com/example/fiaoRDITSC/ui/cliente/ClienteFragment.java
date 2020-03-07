package com.example.fiaoRDITSC.ui.cliente;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.register.RegisterViewModel;

public class ClienteFragment extends BaseFragment {

    private ClienteViewModel mViewModel;
    private TextView txtDireccion, txtCorreo, txtTelefono, txtCedula, txtNombre;

    public static ClienteFragment newInstance() {
        return new ClienteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cliente_fragment, container, false);

        RegisterViewModel vm = (RegisterViewModel) mListener.getBaseModel();
        txtDireccion = v.findViewById(R.id.txtDireccionData);
        txtCorreo = v.findViewById(R.id.txtCorreoData);
        txtTelefono = v.findViewById(R.id.txtTelefonoData);
        txtCedula = v.findViewById(R.id.txtCedulaData);
        txtNombre = v.findViewById(R.id.txtNombre);

        if (vm != null) {

            txtDireccion.setText(vm.getDireccion());
            txtCorreo.setText(vm.getCorreo());
            txtTelefono.setText(vm.getTelefono());
            txtCedula.setText(vm.getCedula());
            txtNombre.setText(vm.getNombre() + " " +vm.getApellido());
        }


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ClienteViewModel.class);
        // TODO: Use the ViewModel
    }

}
