package com.example.fiaoRD.ui.register;

import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private String Nombre;
    private String Apellido;
    private String Cedula;
    private String Telefono;
    private String Direccion;
    private String Correo;
    private String Clave;
    private boolean primerIngreso;

    public RegisterViewModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getCorreo() {
        return this.Correo;
    }
    public String getNombre() {
        return this.Nombre;
    }
    public String getApellido() {
        return this.Apellido;
    }
    public String getCedula() {
        return this.Cedula;
    }
    public String getTelefono() {
        return this.Telefono;
    }
    public String getClave() {
        return this.Clave;
    }
    public String getDireccion() {
        return this.Direccion;
    }
    public boolean getPrimerIngreso() {
        return this.primerIngreso;
    }

    public void setCorreo(String Correo) { this.Correo = Correo; }
    public void setNombre(String Nombre) { this.Nombre = Nombre; }
    public void setApellido(String Apellido) { this.Apellido = Apellido; }
    public void setCedula(String Cedula) { this.Cedula = Cedula; }
    public void setTelefono(String Telefono) { this.Telefono = Telefono; }
    public void setClave(String Clave) { this.Clave = Clave; }
    public void setDireccion(String Direccion) { this.Direccion = Direccion; }
    public void setPrimerIngreso(boolean primerIngreso) { this.primerIngreso = primerIngreso; }

    public RegisterViewModel(String Nombre, String Apellido, String Cedula, String Telefono, String Direcicon, String Correo, String Clave, boolean primerIngreso) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Cedula = Cedula;
        this.Telefono = Telefono;
        this.Direccion = Direcicon;
        this.Correo = Correo;
        this.Clave = Clave;
        this.primerIngreso = primerIngreso;
    }


}

