package ar.edu.unahur.obj2.catering.Modificaciones;

import ar.edu.unahur.obj2.catering.Servicios.Servicio;

public class ActualizarPrecio implements Modificacion {
    private final Servicio servicio;
    private final double nuevoPrecio;
    private double precioAnterior;

    public ActualizarPrecio(Servicio servicio, double nuevoPrecio) {
        this.servicio = servicio;
        this.nuevoPrecio = nuevoPrecio;
    }

    @Override
    public void aplicar() {
        double anterior = servicio.getPrecio();
        servicio.actualizarPrecio(nuevoPrecio);
        precioAnterior = anterior;
    }

    @Override
    public void revertir() {
        servicio.actualizarPrecio(precioAnterior);
    }
}
