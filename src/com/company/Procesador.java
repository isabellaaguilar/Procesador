package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Procesador {

    private final boolean _segmentacion;

    public Procesador(boolean segmentacion){
        _segmentacion = segmentacion;
    }

    private int GeneracionRandom(int min, int max){
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }

    private void AsigancionTiempo(UnidadFuncional unidadFuncional){
        switch (unidadFuncional.getNombre()){
            case FETCH:
                unidadFuncional.setTiempo(5);
                break;
            case ACESSOAMEMORIA:
            case RETROESCRITURA:
                unidadFuncional.setTiempo(GeneracionRandom(10, 20));
                break;
            case EJECUCION:
                unidadFuncional.setTiempo(GeneracionRandom(20, 45));
                break;
            case ACTUALIZACIONDELPROCESADOR:
                unidadFuncional.setTiempo(10);
                break;
        }
    }

    private ArrayList<Proceso> obtenerProcesosFaltantes(Stack<Proceso> colaProceso, TipoUnidadFunctional valorFiltrado){
        ArrayList<Proceso> procesosRegistrados = new ArrayList<Proceso>();
        for (Proceso proceso : colaProceso) {
            if (!proceso.tipoUnidadFunctionalRegistrada.contains(valorFiltrado)){
                procesosRegistrados.add(proceso);
            }
        }
        return procesosRegistrados;
    }

    private void AsignarAEjecucion(Stack<Proceso> colaProceso, ArrayList<Proceso> enEjecucion){

    }

    private void Segmentacion(Stack<Proceso> colaProceso){
        int clock = 0;
        ArrayList<UnidadFuncional> listaPasosUnidadFunctional = new ArrayList();
        ArrayList<Proceso> procesosEnEjecucion = new ArrayList();

        for (TipoUnidadFunctional tipoUnidadFunctional : TipoUnidadFunctional.values()) {
            UnidadFuncional unidadFuncional = new UnidadFuncional();
            unidadFuncional.setNombre(tipoUnidadFunctional);
            unidadFuncional.setOrder(tipoUnidadFunctional.ordinal());
            listaPasosUnidadFunctional.add(unidadFuncional);
        }


        while(true){
            clock++;

            UnidadFuncional fetch = obtenerStepsLibres(listaPasosUnidadFunctional, TipoUnidadFunctional.FETCH);
            UnidadFuncional accesoMemoria = obtenerStepsLibres(listaPasosUnidadFunctional, TipoUnidadFunctional.ACESSOAMEMORIA);
            UnidadFuncional ejecucion = obtenerStepsLibres(listaPasosUnidadFunctional, TipoUnidadFunctional.EJECUCION);
            UnidadFuncional actualizacionProcesador = obtenerStepsLibres(listaPasosUnidadFunctional, TipoUnidadFunctional.ACTUALIZACIONDELPROCESADOR);
            UnidadFuncional retroEscritura = obtenerStepsLibres(listaPasosUnidadFunctional, TipoUnidadFunctional.RETROESCRITURA);


            if (!fetch.isEstaSiendoUsado()){
            }

            if (!accesoMemoria.isEstaSiendoUsado()){

            }

            if (!ejecucion.isEstaSiendoUsado()){

            }

            if (!actualizacionProcesador.isEstaSiendoUsado()){

            }

            if (!retroEscritura.isEstaSiendoUsado()){

            }

            System.out.println(clock);
        }
    }

    private UnidadFuncional obtenerStepsLibres(ArrayList<UnidadFuncional> listaPasosUnidadFunctional, TipoUnidadFunctional valorFiltrado){
        UnidadFuncional result = listaPasosUnidadFunctional.stream()
                .filter(customer -> valorFiltrado.equals(customer.getNombre()))
                .findAny()
                .orElse(null);

        return result;
    }

    private ArrayList<Float> NoSegmentacion(Stack<Proceso> colaProceso){
        ArrayList<UnidadFuncional> listaPasosUnidadFunctional = new ArrayList();
        ArrayList<Float> tiempoPorProcesos = new ArrayList<Float>();

        for (Proceso proceso: colaProceso) {
            System.out.println("<--------- El id del nuevo proceso que va a entrar es : " + proceso.getId() + " ------------->");

            for (TipoUnidadFunctional tipoUnidadFunctional : TipoUnidadFunctional.values()) {
                UnidadFuncional unidadFuncional = new UnidadFuncional();
                unidadFuncional.setNombre(tipoUnidadFunctional);
                unidadFuncional.setOrder(tipoUnidadFunctional.ordinal());
                AsigancionTiempo(unidadFuncional);
                unidadFuncional.setEstaSiendoUsado(false);
                listaPasosUnidadFunctional.add(unidadFuncional);
            }

            float acumuladorTiempoPorProceso = 0;
            for (UnidadFuncional step: listaPasosUnidadFunctional) {
                System.out.println(" La unidad actual es : " + step.getNombre());
                step.setEstaSiendoUsado(true);
                System.out.println("  El tiempo del proceso es : " + step.getTiempo());
                acumuladorTiempoPorProceso = acumuladorTiempoPorProceso + step.getTiempo();
                step.setTiempo(0);
                step.setEstaSiendoUsado(false);
            }
            System.out.println("<--------- Aqui sale el proceso que tiene el Id : " + proceso.getId() + " ------------->");
            tiempoPorProcesos.add(acumuladorTiempoPorProceso);
            listaPasosUnidadFunctional = new ArrayList();
        }
        return tiempoPorProcesos;
    }

    public void Ejecutar(Stack<Proceso> colaProceso){
        if (!_segmentacion){
            float acumulador = 0;
            ArrayList<Float> lista = NoSegmentacion(colaProceso);
            for (float elemento: lista) {
                acumulador = acumulador + elemento;
            };
            float valor = acumulador/lista.size();
            System.out.println("El promedio de tiempo es: " + valor);

        }else{
            float acumulador = 0;
            ArrayList<Float> lista = Segmentacion(colaProceso);
        }
    }
}
