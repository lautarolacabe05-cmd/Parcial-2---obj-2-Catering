package ar.edu.unahur.obj2.catering.Adicionales;

import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public class VajillaPremium extends Adicional {
    public VajillaPremium(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    protected String getNombreAdicional() {
        return "Vajilla premium";
    }

    @Override
    protected double calcularPrecio(double precioAcumulado) {
        return precioAcumulado + 12000;
    }
}
