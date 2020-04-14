package com.example.fiaoRDITSC.ui.movimientos;

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
import android.widget.Toast;

import com.example.fiaoRDITSC.Interfaces.OnEditTextDatePicker;
import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.Utility.MessageDialog;
import com.example.fiaoRDITSC.Utility.MyEditTextDatePicker;
import com.example.fiaoRDITSC.ui.BaseFragment;

public class RealizarPago extends BaseFragment implements View.OnClickListener, OnEditTextDatePicker {

    private EditText fechaPago, montoTotal, montoADeber, txtMontoPago;
    private Button btnGuardar;

    public static RealizarPago newInstance() {
        return new RealizarPago();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        view = inflater.inflate(R.layout.realizar_pago_fragment, container, false);
        fechaPago = new MyEditTextDatePicker(this,view,mListener,R.id.txtFechaPago).get_editText();
        montoTotal = view.findViewById(R.id.txtMonto);
        montoADeber = view.findViewById(R.id.txtMontoADeber);
        txtMontoPago = view.findViewById(R.id.txtMontoPago);
        btnGuardar = view.findViewById(R.id.btnRealizarPago);

        CrearPrestamoViewModel vm = VerPrestamos.vm;

        if (vm != null) {
            montoTotal.setText(Integer.toString(vm.getTotal()));
            montoADeber.setText(Integer.toString(vm.getTotal() - vm.getTotalPagado()));
        }

        btnGuardar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(CrearPrestamoViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onClick(View v) {
        if (validarDatos()) {
            switch (v.getId()) {
                case R.id.btnRealizarPago:
                    realizarPago();
                    break;
            }
        } else {
            mListener.onMakeToast("Debe completar todos los campos", Toast.LENGTH_LONG);
        }
    }

    public void realizarPago() {
//        CrearPrestamoViewModel vm = new CrearPrestamoViewModel(
//                Integer.parseInt(monto.getText().toString()),
//                Integer.parseInt(interes.getText().toString()),
//                Integer.parseInt(periodos.getText().toString()),
//                TipoPago,
//                fechaInicio.getText().toString(),
//                fechaFin.getText().toString(),
//                Integer.parseInt(total.getText().toString()),
//                0,
//                LoginFragment.id
//        );
//
//        ArrayList<String> lista = new ArrayList<String>();
//        lista.add("Prestamos");
//        String key = HomeFragment.key;
//        lista.add(key);
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//        String tiempo = formatter.format(calendar.getTime());
//        lista.add("F" + vm.getFechaInicio().replaceAll("/", "_") + tiempo);
//        vm.setFechaInicio(vm.getFechaInicio() + tiempo);
//        String cadena = mListener.Save(vm, lista, "");
//
//        mListener.onMakeDialog(this,
//                new MessageDialog("Importante",
//                        "Prestamo registrado con exito. Pulse aceptar para ver el prestamo",
//                        "Aceptar",
//                        "Cancelar"),
//                null);

    }

    @Override
    public void DialogPositiveCallback(Object parameter) {
        mListener.onCallFragmentKey(this, R.id.nav_host_fragment, VerPrestamos.newInstance(), "Prestamos");
    }

    public boolean validarDatos() {
        return
                txtMontoPago.getText().toString().length() > 0 &&
                        fechaPago.getText().toString().length() > 0
                ;
    }

    @Override
    public void OnUpdateEditText(StringBuilder Fecha) {
        fechaPago.setText(Fecha);
    }
}
