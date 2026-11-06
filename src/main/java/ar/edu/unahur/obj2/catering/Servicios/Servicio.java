package ar.edu.unahur.obj2.catering.Servicios;

import ar.edu.unahur.obj2.catering.Excepciones.PrecioNegativoException;

public class Servicio {
    protected String nombre;
    protected double precio;

    public Servicio(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precioEsValido(precio);
    }

    private double precioEsValido(double precio) {
        if (precio < 0) {
            throw new PrecioNegativoException("El precio no puede ser negativo");
        }
        return precio;
    }

    public int cantidadServicios() {
        return 1;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
    
}
