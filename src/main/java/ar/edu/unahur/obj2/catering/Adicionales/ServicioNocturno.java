package ar.edu.unahur.obj2.catering.Adicionales;

import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public class ServicioNocturno extends Adicional {
    public ServicioNocturno(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    protected String getNombreAdicional() {
        return "Servicio nocturno";
    }

    @Override
    protected double calcularPrecio(double precioAcumulado) {
        return precioAcumulado * 1.20;
    }
}
