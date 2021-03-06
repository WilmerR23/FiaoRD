package com.example.fiaoRDITSC.ui.movimientos;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiaoRDITSC.Interfaces.OnEditTextDatePicker;
import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.Utility.MessageDialog;
import com.example.fiaoRDITSC.Utility.MyEditTextDatePicker;
import com.example.fiaoRDITSC.Utility.StringSpinnerMap;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.home.HomeFragment;
import com.example.fiaoRDITSC.ui.login.LoginFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class CrearPrestamo extends BaseFragment implements View.OnClickListener,OnEditTextDatePicker, AdapterView.OnItemSelectedListener {

    private CrearPrestamoViewModel mViewModel;
    private EditText fechaInicio,monto,interes,total,periodos,fechaFin, montoCuota;
    private int TipoPago = 1, dias;
    private Button btnCalcular, btnGuardar;
    private TextView lblNombreCliente;

    public static CrearPrestamo newInstance() {
        return new CrearPrestamo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        view = inflater.inflate(R.layout.crear_prestamo_fragment, container, false);
        fechaInicio = new MyEditTextDatePicker(this,view,mListener,R.id.txtFechaInicio).get_editText();
        monto    = view.findViewById(R.id.txtMonto);
        interes  = view.findViewById(R.id.txtInteres);
        total    = view.findViewById(R.id.txtTotal);
        periodos = view.findViewById(R.id.txtPeriodoPago);
        fechaFin = view.findViewById(R.id.txtFechaFin);
        montoCuota = view.findViewById(R.id.txtMontoCuota);

        btnCalcular = view.findViewById(R.id.btnCalcularPrestamo);
        btnGuardar = view.findViewById(R.id.btnCrearPrestamo);

        lblNombreCliente = view.findViewById(R.id.lblNombrecliente);
        if (HomeFragment.model != null) {
            lblNombreCliente.setText(HomeFragment.model.getCliente());
        }

        btnCalcular.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        Spinner spinner = (Spinner) view.findViewById(R.id.txtTipoPago);

        List<StringSpinnerMap> itemList = new ArrayList<StringSpinnerMap>();
        itemList.add(new StringSpinnerMap(1,"Diario"));
        itemList.add(new StringSpinnerMap(7,"Semanal"));
        itemList.add(new StringSpinnerMap(15,"Quincenal"));
        itemList.add(new StringSpinnerMap(30,"Mensual"));
        itemList.add(new StringSpinnerMap(92,"Trimestral"));
        itemList.add(new StringSpinnerMap(183,"Semestral"));
        itemList.add(new StringSpinnerMap(365,"Anual"));

        ArrayAdapter<StringSpinnerMap> adapter = new ArrayAdapter<StringSpinnerMap>(mListener.getActivityContext(),
                android.R.layout.simple_spinner_item, itemList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (VerPrestamos.vm != null) {
            int pos = findSpinnerItem(VerPrestamos.vm.getTipoPago(),itemList);
            spinner.setSelection(pos, true);

            setEditMode();

        } else {
            spinner.setSelection(Adapter.NO_SELECTION, true);
        }
        spinner.setOnItemSelectedListener(this);



        return view;
    }

    public void setEditMode() {

        monto.setText(String.format(Locale.getDefault(),"%d",VerPrestamos.vm.getMonto()));
        interes.setText(String.format(Locale.getDefault(),"%d",VerPrestamos.vm.getInteres()));
        periodos.setText(String.format(Locale.getDefault(),"%d",VerPrestamos.vm.getPeriodos()));
        fechaFin.setText(VerPrestamos.vm.getFechaFin());
        total.setText(String.format(Locale.getDefault(),"%d",VerPrestamos.vm.getTotal()));
        montoCuota.setText(String.format(Locale.getDefault(),"%d",VerPrestamos.vm.getCuota()));
        String fech = VerPrestamos.vm.getFechaInicio();
        fechaInicio.setText(fech);
    }

    public int findSpinnerItem(
            int key, List<StringSpinnerMap> tipoPago) {

        for (int x = 0; x < tipoPago.size(); x++) {
            if (tipoPago.get(x).key == key) {
                return x;
            }
        }
        return -1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CrearPrestamoViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void OnUpdateEditText(StringBuilder Fecha) {
            fechaInicio.setText(Fecha);
            CalcularFecha();
    }

    private void CalcularFecha() {
        if (periodos.getText().toString().length() > 0 && fechaInicio.getText().toString().length() > 0) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date d = dateFormat.parse(fechaInicio.getText().toString());

                Calendar c = Calendar.getInstance();
                c.setTime(d);
                dias = TipoPago * Integer.parseInt(periodos.getText().toString());
                c.add(Calendar.DATE, dias);
                Date resultdate = new Date(c.getTimeInMillis());
                String fechaFinal = dateFormat.format(resultdate);

                fechaFin.setText(fechaFinal);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {
            mListener.onMakeToast("Debe seleccionar el periodo de pago y luego una fecha de inicio", Toast.LENGTH_LONG);
        }

    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        StringSpinnerMap valor = (StringSpinnerMap) parent.getItemAtPosition(pos);
        TipoPago = valor.key;
        if (fechaInicio.getText().toString().length() > 0) {
            CalcularFecha();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public void onClick(View v) {
        if (validarDatos()) {
            switch (v.getId()) {
                case R.id.btnCrearPrestamo:
                    guardarPrestamo();
                    break;
                case R.id.btnCalcularPrestamo:
                    calcularPrestamo();
                    break;
            }
        } else {
            mListener.onMakeToast("Debe completar todos los campos", Toast.LENGTH_LONG);
        }
    }

    public void guardarPrestamo() {
         CrearPrestamoViewModel vm = new CrearPrestamoViewModel(
                Integer.parseInt(monto.getText().toString()),
                 interes.getText().toString().length() > 0 ? Integer.parseInt(interes.getText().toString()) : 0,
                 Integer.parseInt(periodos.getText().toString()),
                 TipoPago,
                 fechaInicio.getText().toString(),
                 fechaFin.getText().toString(),
                 Integer.parseInt(total.getText().toString()),
                 0,
                 LoginFragment.UserVm.getNombre() + " " + LoginFragment.UserVm.getApellido(),
                 LoginFragment.id,
                 Integer.parseInt(montoCuota.getText().toString())
         );

        ArrayList<String> lista = new ArrayList<String>();
        lista.add("Prestamos");
        String key = HomeFragment.key;
        lista.add(key);

        String mensaje = null;

        //Si esta editando el prestamo, generar movimiento y actualizar prestamo
        if (VerPrestamos.vm != null) {
            lista.add(VerPrestamos.vm.getId());
            vm.setTotalPagado(VerPrestamos.vm.getTotalPagado());


            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, -4);
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String tiempo = formatter.format(calendar.getTime());

            int montoPagado = Math.abs(VerPrestamos.vm.getMonto() - vm.getMonto());

            if (montoPagado > 0) {


                RealizarPagoViewModel rpVm = new RealizarPagoViewModel(
                        montoPagado,
                        calendar.getTimeInMillis(),
                        "Añadir a deuda",
                        Math.abs(vm.getMonto() - vm.getTotalPagado())
                );

                ArrayList<String> listaMovimientos = new ArrayList<String>();
                listaMovimientos.add("MovimientosPrestamos");
                listaMovimientos.add(VerPrestamos.vm.getId());

                rpVm.setId("MP_" + tiempo);

                _Firebase.SaveBaseModelPush(rpVm, listaMovimientos, "");
            }
            mensaje = "Prestamo actualizado con exito. Pulse aceptar para ver el prestamo";
        } else {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String tiempo = formatter.format(calendar.getTime());

            lista.add("F" + vm.getFechaInicio().replaceAll("/", "_") + tiempo);
            vm.setFechaInicio(vm.getFechaInicio() + tiempo);
            mensaje = "Prestamo registrado con exito. Pulse aceptar para ver el prestamo";
        }

        mListener.Save(vm, lista, "");

        mListener.onMakeDialog(this,
                new MessageDialog("Importante",
                        mensaje,
                                 "Aceptar",
                        "Cancelar"),
                null);

        clearValues();
    }

    @Override
    public void DialogPositiveCallback(Object parameter) {
            mListener.onCallFragmentKey(this,R.id.nav_host_fragment, VerPrestamos.newInstance(),"Prestamos");
    }

    public void clearValues() {
        monto.setText("");
        interes.setText("");
        periodos.setText("");
        fechaFin.setText("");
        total.setText("");
        montoCuota.setText("");
        fechaInicio.setText("");
    }

    public boolean validarDatos() {
        return
                monto.getText().toString().length() > 0 &&
                periodos.getText().toString().length() > 0 &&
                fechaFin.getText().toString().length() > 0
        ;
    }

    public void calcularPrestamo() {
            double interesValue = interes.getText().toString().length() > 0 ? Double.parseDouble(interes.getText().toString()) : 0;
            int montoValue = Integer.parseInt(monto.getText().toString());
            double totalInteres = (interesValue / 100) * montoValue;
            int MontoTotal = (int) (montoValue + totalInteres);
            String totalValue = Integer.toString(MontoTotal);
            total.setText(totalValue);

            int cuota = MontoTotal / Integer.parseInt(periodos.getText().toString());
            montoCuota.setText(Integer.toString(cuota));
    }
}
