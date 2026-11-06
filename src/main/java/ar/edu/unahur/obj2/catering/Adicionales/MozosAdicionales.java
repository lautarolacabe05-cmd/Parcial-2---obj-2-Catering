package ar.edu.unahur.obj2.catering.Adicionales;

import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public class MozosAdicionales extends Adicional {
    public MozosAdicionales(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    protected String getNombreAdicional() {
        return "Mozos adicionales";
    }

    @Override
    protected double calcularPrecio(double precioAcumulado) {
        return precioAcumulado + 18000;
    }
}
