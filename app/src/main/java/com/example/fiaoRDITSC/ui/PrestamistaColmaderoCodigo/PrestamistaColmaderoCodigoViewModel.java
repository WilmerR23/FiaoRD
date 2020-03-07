package com.example.fiaoRDITSC.ui.PrestamistaColmaderoCodigo;

import com.example.fiaoRDITSC.Models.BaseModel;

public class PrestamistaColmaderoCodigoViewModel extends BaseModel {
    // TODO: Implement the ViewModel


    private String Cliente;
    private String Descripcion;

    public PrestamistaColmaderoCodigoViewModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getCliente() {
        return this.Cliente;
    }
    public void setCliente(String Cliente) { this.Cliente = Cliente; }
    public String getDescripcion() {
        return this.Descripcion;
    }
    public void setDescripcion(String Descripcion) { this.Descripcion = Descripcion; }

    public PrestamistaColmaderoCodigoViewModel(String Cliente, String Descripcion) {
        this.setId(Cliente);
        this.Cliente = Cliente;
        this.Descripcion = Descripcion;
    }
}
