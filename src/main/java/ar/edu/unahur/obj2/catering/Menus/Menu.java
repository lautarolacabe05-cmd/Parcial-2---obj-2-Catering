package ar.edu.unahur.obj2.catering.Menus;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.catering.Excepciones.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.Excepciones.PropuestaNoIncluidaException;
import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public class Menu implements Propuesta {
    private final String nombre;
    private final double descuento;
    private final List<Propuesta> componentes;

    public Menu(String nombre, double descuento, Propuesta... componentes) {
        if (descuento < 0 || descuento > 100) {
            throw new DescuentoInvalidoException("El descuento debe estar entre 0 y 100");
        }
        this.nombre = nombre;
        this.descuento = descuento;
        this.componentes = new ArrayList<>(List.of(componentes));
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public double getDescuento() {
        return descuento;
    }

    @Override
    public double getPrecio() {
        double total = componentes.stream().mapToDouble(Propuesta::getPrecio).sum();
        return total * (100 - descuento) / 100;
    }

    @Override
    public int getCantidadServicios() {
        return componentes.stream().mapToInt(Propuesta::getCantidadServicios).sum();
    }

    @Override
    public boolean tieneServicio(String nombreServicio) {
        return componentes.stream().anyMatch(p -> p.tieneServicio(nombreServicio));
    }

    public void agregar(Propuesta propuesta) {
        componentes.add(propuesta);
    }

    public void quitar(Propuesta propuesta) {
        if (!componentes.remove(propuesta)) {
            throw new PropuestaNoIncluidaException(
                    propuesta.getNombre() + " no es componente directo de " + nombre);
        }
    }
}
