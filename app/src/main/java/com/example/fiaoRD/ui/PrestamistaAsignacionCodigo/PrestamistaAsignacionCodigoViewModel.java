package com.example.fiaoRD.ui.PrestamistaAsignacionCodigo;

import androidx.lifecycle.ViewModel;

import com.example.fiaoRD.Models.BaseModel;

public class PrestamistaAsignacionCodigoViewModel extends BaseModel {
    // TODO: Implement the ViewModel

    private String Codigo;

    public PrestamistaAsignacionCodigoViewModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getCodigo() {
        return this.Codigo;
    }
    public void setCodigo(String Codigo) { this.Codigo = Codigo; }

    public PrestamistaAsignacionCodigoViewModel(String Codigo) {
        this.Codigo = Codigo;
    }
}
