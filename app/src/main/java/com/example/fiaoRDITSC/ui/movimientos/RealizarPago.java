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
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiaoRDITSC.Firebase;
import com.example.fiaoRDITSC.Interfaces.OnEditTextDatePicker;
import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.Utility.MessageDialog;
import com.example.fiaoRDITSC.Utility.MyEditTextDatePicker;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RealizarPago extends BaseFragment implements View.OnClickListener, OnEditTextDatePicker {

    private EditText fechaPago, montoTotal, montoADeber, txtMontoPago, txtTotalPagado;
    private TextView lblMontoPago, lblFechaPago;
    private Button btnGuardar;
    private CrearPrestamoViewModel crear_prestamo_vm;
    private TextView lblNombreCliente;

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
        txtTotalPagado= view.findViewById(R.id.txtTotalPagado);
        btnGuardar = view.findViewById(R.id.btnRealizarPago);

        crear_prestamo_vm = VerPrestamos.vm;

        lblNombreCliente = view.findViewById(R.id.lblNombrecliente);

        lblMontoPago = view.findViewById(R.id.lblMontoPago);
        lblFechaPago = view.findViewById(R.id.lblFechaPago);


        if (crear_prestamo_vm != null) {
            montoTotal.setText(Integer.toString(crear_prestamo_vm.getTotal()));
            montoADeber.setText(Integer.toString(crear_prestamo_vm.getTotal() - crear_prestamo_vm.getTotalPagado()));
            txtTotalPagado.setText(Integer.toString(crear_prestamo_vm.getTotalPagado()));
        }

        if (HomeFragment.model != null) {
            lblNombreCliente.setText(HomeFragment.model.getCliente());
        }

        if(HomeFragment.key != null) {
            btnGuardar.setOnClickListener(this);
        } else {
            txtMontoPago.setVisibility(View.GONE);
            fechaPago.setVisibility(View.GONE);
            lblMontoPago.setVisibility(View.GONE);
            lblFechaPago.setVisibility(View.GONE);
            btnGuardar.setVisibility(View.GONE);
        }

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
        int montoPagado = Integer.parseInt(txtMontoPago.getText().toString());

        RealizarPagoViewModel vm = new RealizarPagoViewModel(
                montoPagado,
                fechaPago.getText().toString()
        );


        ArrayList<String> lista = new ArrayList<String>();
        lista.add("MovimientosPrestamos");
        lista.add(crear_prestamo_vm.getId());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String tiempo = formatter.format(calendar.getTime());
        vm.setId("MP_" + tiempo);

        String cadena = _Firebase.SaveBaseModelPush(vm, lista, "");


        String key = "";
        if (HomeFragment.model != null) {
            key = HomeFragment.model.getId();
        }

        int nuevoTotalPagado = Integer.parseInt(txtTotalPagado.getText().toString()) + montoPagado;
        ArrayList<String> listaPrestamos = new ArrayList<String>();
        listaPrestamos.add("Prestamos");
        listaPrestamos.add(key);
        listaPrestamos.add(crear_prestamo_vm.getId());
        _Firebase.UpdateKey(listaPrestamos, "totalPagado",nuevoTotalPagado,"");

        mListener.onMakeDialog(this,
                new MessageDialog("Importante",
                        "Pago registrado con exito. Pulse aceptar para ver los movimientos del prestamo",
                        "Aceptar",
                        "Cancelar"),
                null);

    }

    @Override
    public void DialogPositiveCallback(Object parameter) {
        mListener.onCallFragmentKey(this, R.id.nav_host_fragment, VerMovimientos.newInstance(), "Movimientos");
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
