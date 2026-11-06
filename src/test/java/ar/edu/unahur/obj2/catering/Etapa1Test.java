package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.Catalogo.Catalogo;
import ar.edu.unahur.obj2.catering.Excepciones.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.Excepciones.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.Menus.Menu;
import ar.edu.unahur.obj2.catering.Servicios.Servicio;

class Etapa1Test {
    private static final double DELTA = 0.01;
    private Catalogo catalogo;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
    }

    @Test
    void precioDeMenuParrilleroEs342000() {
        assertEquals(342000, catalogo.getMenuParrillero().getPrecio(), DELTA);
    }

    @Test
    void precioDePackFiestaEs256500() {
        assertEquals(256500, catalogo.getPackFiesta().getPrecio(), DELTA);
    }

    @Test
    void cantidadDeServiciosDeMenuParrilleroEs3() {
        assertEquals(3, catalogo.getMenuParrillero().getCantidadServicios());
    }

    @Test
    void cantidadDeServiciosDeServicioSueltoEs1() {
        assertEquals(1, catalogo.getDjYSonido().getCantidadServicios());
    }

    @Test
    void menuParrilleroTieneServicioParrilladaCompleta() {
        assertTrue(catalogo.getMenuParrillero().tieneServicio("Parrillada completa"));
    }

    @Test
    void menuParrilleroNoTieneServicioDjYSonido() {
        assertFalse(catalogo.getMenuParrillero().tieneServicio("DJ y sonido"));
    }

    @Test
    void servicioConPrecioNegativoLanzaPrecioNegativoException() {
        assertThrows(PrecioNegativoException.class, () -> new Servicio("Fotógrafo", -1));
    }

    @Test
    void menuConDescuentoMayorACienLanzaDescuentoInvalidoException() {
        assertThrows(DescuentoInvalidoException.class, () -> new Menu("Menú", 101));
    }

    @Test
    void menuConDescuentoNegativoLanzaDescuentoInvalidoException() {
        assertThrows(DescuentoInvalidoException.class, () -> new Menu("Menú", -1));
    }

    @Test
    void precioDeEventoCompletoEs518800() {
        assertEquals(518800, catalogo.getEventoCompleto().getPrecio(), DELTA);
    }

    @Test
    void cantidadDeServiciosDeEventoCompletoEs6() {
        assertEquals(6, catalogo.getEventoCompleto().getCantidadServicios());
    }

    @Test
    void eventoCompletoTieneServicioDjYSonido() {
        assertTrue(catalogo.getEventoCompleto().tieneServicio("DJ y sonido"));
    }

    @Test
    void eventoCompletoNoTieneServicioFotografo() {
        assertFalse(catalogo.getEventoCompleto().tieneServicio("Fotógrafo"));
    }

    @Test
    void descripcionDeServicioEsSuNombre() {
        assertEquals("Mesa dulce", catalogo.getMesaDulce().getNombre());
    }
}