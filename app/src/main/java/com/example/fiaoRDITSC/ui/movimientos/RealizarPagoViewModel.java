package com.example.fiaoRDITSC.ui.movimientos;

import androidx.lifecycle.ViewModel;

import com.example.fiaoRDITSC.Models.BaseModel;

public class RealizarPagoViewModel extends BaseModel {
    // TODO: Implement the ViewModel


    public RealizarPagoViewModel(int montoPago, String fechaPago) {
        MontoPago = montoPago;
        FechaPago = fechaPago;
    }
    public RealizarPagoViewModel() {
    }

    private int MontoPago;

    public int getMontoPago() {
        return MontoPago;
    }

    public void setMontoPago(int montoPago) {
        MontoPago = montoPago;
    }

    public String getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(String fechaPago) {
        FechaPago = fechaPago;
    }

    private String FechaPago;

    public String getDescripcion() {
        return "Monto: " +
                getMontoPago() +
                " Fecha Pago: " +
                getFechaPago();
    }


}
