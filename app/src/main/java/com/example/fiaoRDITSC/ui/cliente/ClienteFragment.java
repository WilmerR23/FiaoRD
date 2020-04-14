package com.example.fiaoRDITSC.ui.cliente;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.PrestamistaColmaderoCodigo.PrestamistaColmaderoCodigoViewModel;
import com.example.fiaoRDITSC.ui.Utility;
import com.example.fiaoRDITSC.ui.home.HomeFragment;
import com.example.fiaoRDITSC.ui.login.LoginFragment;
import com.example.fiaoRDITSC.ui.movimientos.VerPrestamos;
import com.example.fiaoRDITSC.ui.register.RegisterViewModel;

import java.util.ArrayList;

public class ClienteFragment extends BaseFragment implements View.OnClickListener {

    private ClienteViewModel mViewModel;
    private TextView txtDireccion, txtCorreo, txtTelefono, txtCedula, txtNombre, txtApellido;
    private Button btnMovimientos, btnEditarPerfil;
    private RegisterViewModel vm;

    public static ClienteFragment newInstance() {
        return new ClienteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        mListener.setCurrentFragment(this,1);
        View v = inflater.inflate(R.layout.cliente_fragment, container, false);

        btnMovimientos = v.findViewById(R.id.btnPrestamos);
        btnEditarPerfil = v.findViewById(R.id.btnEditarPerfil);
        txtDireccion = v.findViewById(R.id.txtDireccion);
        txtApellido = v.findViewById(R.id.txtApellidos);
        txtCorreo = v.findViewById(R.id.txtCorreo);
        txtTelefono = v.findViewById(R.id.txtTelefonos);
        txtCedula = v.findViewById(R.id.txtCedula);
        txtNombre = v.findViewById(R.id.txtNombres);


        btnMovimientos.setOnClickListener(this);
        btnEditarPerfil.setOnClickListener(this);


//        vm = (RegisterViewModel) mListener.getBaseModel();
        if (HomeFragment.key != null) {
            txtApellido.setEnabled(false);
            txtDireccion.setEnabled(false);
            txtCorreo.setEnabled(false);
            txtTelefono.setEnabled(false);
            txtCedula.setEnabled(false);
            txtNombre.setEnabled(false);
            btnEditarPerfil.setVisibility(View.GONE);
            android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 4f);
            btnMovimientos.setLayoutParams(params);
            ArrayList<String> lista = new ArrayList<String>();
            lista.add("Usuarios");
            String key = HomeFragment.key;
            lista.add(key);
            mListener.Obtener(lista, RegisterViewModel.class, this);
        } else {

            vm = LoginFragment.UserVm;
            setScreenValues();
        }
        return v;
    }

    private void setScreenValues() {
        if (vm != null) {
            txtApellido.setText(vm.getApellido());
            txtDireccion.setText(vm.getDireccion());
            txtCorreo.setText(vm.getCorreo());
            txtTelefono.setText(vm.getTelefono());
            txtCedula.setText(vm.getCedula());
            txtNombre.setText(vm.getNombre());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ClienteViewModel.class);
        // TODO: Use the ViewModel
    }

    private RegisterViewModel setModel() {
        vm.setApellido(txtApellido.getText().toString());
        vm.setDireccion(txtDireccion.getText().toString());
        vm.setTelefono(txtTelefono.getText().toString());
        vm.setCedula(txtCedula.getText().toString());
        vm.setNombre(txtNombre.getText().toString());
        return vm;
    }

    public boolean validarDatos() {
        return txtNombre.getText().toString().length() > 0
                && txtApellido.getText().toString().length() > 0
                && txtCedula.getText().toString().length() > 0
                && txtTelefono.getText().toString().length() > 0
                && txtDireccion.getText().toString().length() > 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPrestamos:
                mListener.onCallFragmentKey(this,R.id.nav_host_fragment, VerPrestamos.newInstance(),"Prestamos");
                break;
            case R.id.btnEditarPerfil:
                if (validarDatos()) {
                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this.getActivityContext());
                    dialogo1.setTitle("Importante");
                    dialogo1.setMessage("¿ Esta seguro de editar su perfil ?");
                    dialogo1.setCancelable(true);
                    final ClienteFragment cF = this;
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            vm = setModel();
                            ArrayList lista = new ArrayList();
                            lista.add("Usuarios");
                            String msj = mListener.UpdateKey(lista,LoginFragment.id, vm, null);
                            Toast.makeText(cF.getActivityContext(),"Perfil actualizado satisfactoriamente",Toast.LENGTH_LONG).show();
                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            dialogo1.cancel();
                        }
                    });
                    dialogo1.show();
                } else {
                    mListener.onMakeToast("Debe completar todos los campos", Toast.LENGTH_SHORT);
                }
                break;
        }
    }

    @Override
    public void receiveData(Object obj) {
        vm = (RegisterViewModel) obj;
        setScreenValues();
    }
}
