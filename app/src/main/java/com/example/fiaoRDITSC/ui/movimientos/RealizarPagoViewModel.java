package com.example.fiaoRDITSC.ui.movimientos;

import androidx.lifecycle.ViewModel;

import com.example.fiaoRDITSC.Models.BaseModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RealizarPagoViewModel extends BaseModel {
    // TODO: Implement the ViewModel


    public RealizarPagoViewModel(int montoPago, long fechaPago, String tipoMovimiento, int deuda) {
        MontoPago = montoPago;
        FechaPago = fechaPago;
        TipoMovimiento = tipoMovimiento;
        Deuda = deuda;
    }

    public RealizarPagoViewModel() {
    }

    private int MontoPago;

    public int getDeuda() {
        return Deuda;
    }

    public void setDeuda(int deuda) {
        Deuda = deuda;
    }

    private int Deuda;
    private String TipoMovimiento;

    public String getTipoMovimiento() {
        return TipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        TipoMovimiento = tipoMovimiento;
    }

    public int getMontoPago() {
        return MontoPago;
    }

    public void setMontoPago(int montoPago) {
        MontoPago = montoPago;
    }

    public long getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(long fechaPago) {
        FechaPago = fechaPago;
    }

    private long FechaPago;

    public String getDescripcion() {


        Date date = new Date(getFechaPago());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
        String fecha = sdf.format(date);


        return "Monto: " +
                getMontoPago()  +
                " Deuda actual: " +
                getDeuda() +
                " Fecha Pago: " +
                fecha +
                " Concepto: " +
                getTipoMovimiento();
    }


}
