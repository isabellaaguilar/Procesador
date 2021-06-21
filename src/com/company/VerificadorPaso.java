package com.company;

public class VerificadorPaso {
    private TipoUnidadFunctional Nombre;
    private boolean Revisado;

    public TipoUnidadFunctional getNombre() {
        return Nombre;
    }

    public void setNombre(TipoUnidadFunctional nombre) {
        Nombre = nombre;
    }

    public boolean isRevisado() {
        return Revisado;
    }

    public void setRevisado(boolean revisado) {
        Revisado = revisado;
    }
}
