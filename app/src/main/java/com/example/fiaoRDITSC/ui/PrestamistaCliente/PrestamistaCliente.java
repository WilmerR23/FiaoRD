package com.example.fiaoRDITSC.ui.PrestamistaCliente;

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

public class PrestamistaCliente extends BaseFragment {

    private PrestamistaClienteViewModel mViewModel;

    public static PrestamistaCliente newInstance() {
        return new PrestamistaCliente();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        View v = inflater.inflate(R.layout.prestamista_cliente_fragment, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PrestamistaClienteViewModel.class);
        // TODO: Use the ViewModel
    }

}
