package ar.edu.unahur.obj2.catering.Servicios;

import ar.edu.unahur.obj2.catering.Excepciones.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public class Servicio implements Propuesta {
    private final String nombre;
    private final double precio;

    public Servicio(String nombre, double precio) {
        if (precio < 0) {
            throw new PrecioNegativoException("El precio no puede ser negativo");
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public int getCantidadServicios() {
        return 1;
    }

    @Override
    public boolean tieneServicio(String nombreServicio) {
        return nombre.equals(nombreServicio);
    }
}
