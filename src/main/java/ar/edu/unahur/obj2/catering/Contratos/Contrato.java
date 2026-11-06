package ar.edu.unahur.obj2.catering.Contratos;

import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public class Contrato {
    private final String cliente;
    private final double importe;
    private final String descripcion;

    public Contrato(String cliente, Propuesta propuesta) {
        this.cliente = cliente;
        this.importe = propuesta.getPrecio();
        this.descripcion = propuesta.getNombre();
    }

    public String getCliente() {
        return cliente;
    }

    public double getImporte() {
        return importe;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
