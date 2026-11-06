package ar.edu.unahur.obj2.catering.Menus;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.catering.Excepciones.DescuentoEsInvalidoException;
import ar.edu.unahur.obj2.catering.Servicios.Servicio;

public class Menu {
    protected String nombre;
    protected double descuento;
    protected List<Servicio> contiene;

    public Menu(String nombre, double descuento, Servicio ... contiene) {
        this.nombre = nombre;
        this.descuento = descuentoEsValido(descuento);
        this.contiene = new ArrayList<>(List.of(contiene));
    }

    private double descuentoEsValido(double descuento) {
        if (descuento < 0 || descuento > 100) {
            throw new DescuentoEsInvalidoException("El descuento debe estar entre 0 y 100");
        }
        return descuento;
    }

    public String getNombre() {
        return nombre;
    }
    public double getDescuento() {
        return descuento;
    }

    public int cantidadServicios() {
        return contiene.size();
    }

    public void agregarServicio(Servicio servicio) {
        contiene.add(servicio);
    }

    public double getPrecio() {
        double precioTotal = 0;
        for (Servicio servicio : contiene) {
            precioTotal += servicio.getPrecio();
        }
        return precioTotal * (1 - descuento / 100);
    }
    public boolean contieneServicio(String nombreServicio) {
        for (Servicio servicio : contiene) {
            if (servicio.getNombre().equals(nombreServicio)) {
                return true;
            }
        }
        return false;
    }
}
