package com.example.fiaoRDITSC.ui.PrestamistaCliente;

import androidx.lifecycle.ViewModel;

import com.example.fiaoRDITSC.Models.BaseModel;

public class PrestamistaClienteViewModel extends BaseModel {
    // TODO: Implement the ViewModel

    private String Prestamista;

    public PrestamistaClienteViewModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public PrestamistaClienteViewModel(String id,String prestamista) {
        Prestamista = prestamista;
        this.setId(id);
    }

    public String getPrestamista() {
        return Prestamista;
    }

    public void setPrestamista(String prestamista) {
        Prestamista = prestamista;
    }
}
