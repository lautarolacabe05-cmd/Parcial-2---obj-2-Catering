package ar.edu.unahur.obj2.catering.Adicionales;

import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public abstract class Adicional implements Propuesta {
    private final Propuesta propuesta;

    protected Adicional(Propuesta propuesta) {
        this.propuesta = propuesta;
    }

    @Override
    public double getPrecio() {
        return calcularPrecio(propuesta.getPrecio());
    }

    @Override
    public String getNombre() {
        return propuesta.getNombre() + " + " + getNombreAdicional();
    }

    @Override
    public int getCantidadServicios() {
        return propuesta.getCantidadServicios();
    }

    @Override
    public boolean tieneServicio(String nombreServicio) {
        return propuesta.tieneServicio(nombreServicio);
    }

    protected abstract String getNombreAdicional();

    protected abstract double calcularPrecio(double precioAcumulado);
}
