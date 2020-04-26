package com.example.fiaoRDITSC.ui.PrestamistaAsignacionCodigo;

import com.example.fiaoRDITSC.Models.BaseModel;

public class PrestamistaAsignacionCodigoViewModel extends BaseModel {
    // TODO: Implement the ViewModel

    private String Codigo, NombreCompleto;

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public PrestamistaAsignacionCodigoViewModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getCodigo() {
        return this.Codigo;
    }
    public void setCodigo(String Codigo) { this.Codigo = Codigo; }

    public PrestamistaAsignacionCodigoViewModel(String Codigo, String nombreCompleto) {
        this.Codigo = Codigo;
        this.NombreCompleto = nombreCompleto;
    }
}
