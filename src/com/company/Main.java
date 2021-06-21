package com.company;


import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Proceso> listaProcesos = new Stack<Proceso>();

        for (int i = 0; i < 1000; i++) {
            Proceso proceso = new Proceso();
            proceso.setId(i);

            VerificadorPaso verificadorPaso = new VerificadorPaso();

            for (TipoUnidadFunctional tipoUnidadFunctional : TipoUnidadFunctional.values()) {
                verificadorPaso.setNombre(tipoUnidadFunctional);
                verificadorPaso.setRevisado(false);
                proceso.tipoUnidadFunctionalRegistrada.add(verificadorPaso);
            }

            listaProcesos.add(proceso);
        }
        Procesador procesador = new Procesador(false);
        procesador.Ejecutar(listaProcesos);
    }
}

