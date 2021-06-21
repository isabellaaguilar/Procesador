package com.company;

public class UnidadFuncional {

    private TipoUnidadFunctional nombre;
    private float tiempo = 0;
    private boolean estaSiendoUsado = false;
    private int order;


    public TipoUnidadFunctional getNombre() {
        return nombre;
    }

    public void setNombre(TipoUnidadFunctional nombre) {
        this.nombre = nombre;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isEstaSiendoUsado() {
        return estaSiendoUsado;
    }

    public void setEstaSiendoUsado(boolean estaSiendoUsado) {
        this.estaSiendoUsado = estaSiendoUsado;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
