package com.example.fiaoRDITSC.ui.login;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiaoRDITSC.MainActivity;
import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.TipoUsuario.TipoUsuario;
import com.example.fiaoRDITSC.ui.Utility;
import com.example.fiaoRDITSC.ui.register.Register;
import com.example.fiaoRDITSC.ui.register.RegisterViewModel;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private RegisterViewModel vm;
    private TextView txtRegistrarse;
    private EditText txtCorreo, txtClave;
    private Button btnIniciarSesion;
    public static String id = "";
    public static String Descripcion = "";
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment2, container, false);
        txtRegistrarse = v.findViewById(R.id.txtRegistrateAqui);

        btnIniciarSesion = v.findViewById(R.id.btnIniciaSesion);

        txtCorreo = v.findViewById(R.id.txtCorreoLogin);
        txtClave = v.findViewById(R.id.txtClaveLogin);

        btnIniciarSesion.setOnClickListener(this);
        txtRegistrarse.setOnClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIniciaSesion:
                if (txtClave.getText().toString().length() > 0 && txtCorreo.getText().toString().length() > 0) {
                    mListener.Obtener(Utility.encodeForFirebaseKey(txtCorreo.getText().toString()),"Usuarios", RegisterViewModel.class, this);
                }
                break;
            case R.id.txtRegistrateAqui:
                mListener.onCallFragment(Register.newInstance());
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void receiveData(Object obj) {
        vm = (RegisterViewModel) obj;

        try {
            if (vm.getClave().equals(
                    Utility.FormatToSha(txtClave.getText().toString()))) {
                id = Utility.encodeForFirebaseKey(vm.getCorreo());
                Descripcion = vm.getNombre() + " " + vm.getApellido() + " " + vm.getTelefono();
               if (vm.getPrimerIngreso()) {
                   mListener.onCallFragment(TipoUsuario.newInstance());
               } else {
                   mListener.onCallIntentWithData(MainActivity.class, vm);
               }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            mListener.onMakeToast(e.getMessage(), Toast.LENGTH_LONG);
        }
    }


    @Override
    public void receiveDataTodos(List<Object> obj) {

    }

    @Override
    public void receiveChildrenCount(int count) {

    }
}
