package com.example.fiaoRDITSC.ui.movimientos;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.ui.BaseFragment;

public class CrearPrestamo extends BaseFragment {

    private CrearPrestamoViewModel mViewModel;

    public static CrearPrestamo newInstance() {
        return new CrearPrestamo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.crear_prestamo_fragment, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CrearPrestamoViewModel.class);
        // TODO: Use the ViewModel
    }

}
