package com.example.fiaoRD.ui.TipoUsuario;

import androidx.lifecycle.ViewModel;

public class TipoUsuarioViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private String Usuario;
    private String Tipo;

    public TipoUsuarioViewModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getUsuario() {
        return this.Usuario;
    }
    public String getTipo() {
        return this.Tipo;
    }

    public void setUsuario(String Correo) { this.Usuario = Usuario; }
    public void setTipo(String Nombre) { this.Tipo = Tipo; }

    public TipoUsuarioViewModel(String Usuario, String Tipo) {
        this.Usuario = Usuario;
        this.Tipo = Tipo;
    }

}
