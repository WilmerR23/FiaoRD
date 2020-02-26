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

import com.example.fiaoRD.MainActivity;
import com.example.fiaoRD.R;
import com.example.fiaoRD.ui.BaseFragment;

public class PrestamistaColmaderoCodigo extends BaseFragment {

    private PrestamistaColmaderoCodigoViewModel mViewModel;
    private Button btnIngresar;
    private EditText edCodigo;

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
                   String msj = mListener.UpdateKey("Usuarios","primerIngreso", false, null);
                    mListener.onCallIntent(MainActivity.class);
                } else {
                    mListener.onMakeToast("Debes ingresar un codigo valido de 4 digitos", Toast.LENGTH_SHORT);
                }
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PrestamistaColmaderoCodigoViewModel.class);
        // TODO: Use the ViewModel
    }

}
