package com.example.fiaoRDITSC.ui.movimientos;

import androidx.lifecycle.ViewModel;

import com.example.fiaoRDITSC.Models.BaseModel;

import java.util.Date;

public class CrearPrestamoViewModel extends BaseModel {
    // TODO: Implement the ViewModel

    public CrearPrestamoViewModel(int monto, int interes, int periodos, int tipoPago, String fechaInicio, String fechaFin, int total, int totalPagado, String nombrePrestamista, String prestamista, int cuota) {
        Monto = monto;
        Interes = interes;
        Periodos = periodos;
        TipoPago = tipoPago;
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
        Total = total;
        TotalPagado = totalPagado;
        NombrePrestamista = nombrePrestamista;
        Prestamista = prestamista;
        Cuota = cuota;
    }

    public String getNombrePrestamista() {
        return NombrePrestamista;
    }

    public void setNombrePrestamista(String nombrePrestamista) {
        NombrePrestamista = nombrePrestamista;
    }

    private int Monto;
    private int Interes;
    private int Periodos;
    private int TipoPago;
    private String FechaInicio;
    private String FechaFin;
    private String Prestamista, NombrePrestamista;
    private int Total;
    private int TotalPagado;
    private int Cuota;

    public int getCuota() {
        return Cuota;
    }

    public void setCuota(int cuota) {
        Cuota = cuota;
    }

    public void setPrestamista(String prestamista) {
        Prestamista = prestamista;
    }

    public void setMonto(int monto) {
        Monto = monto;
    }

    public void setInteres(int interes) {
        Interes = interes;
    }

    public void setPeriodos(int periodos) {
        Periodos = periodos;
    }

    public String getPrestamista() {
        return Prestamista;
    }

    public int getMonto() {
        return Monto;
    }

    public int getInteres() {
        return Interes;
    }

    public int getPeriodos() {
        return Periodos;
    }

    public int getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(int tipoPago) {
        TipoPago = tipoPago;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String fechaFin) {
        FechaFin = fechaFin;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public int getTotalPagado() {
        return TotalPagado;
    }

    public void setTotalPagado(int totalPagado) {
        TotalPagado = totalPagado;
    }


    public CrearPrestamoViewModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getDescripcion() {
        return "Monto: " +
                getMonto() +
                " Fecha Inicio: " +
                getFechaInicio() +
                " Fecha fin: " +
                getFechaFin() +
                " Prestamista: " +
                getNombrePrestamista();
    }


}
