package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.Adicionales.MozosAdicionales;
import ar.edu.unahur.obj2.catering.Adicionales.ServicioNocturno;
import ar.edu.unahur.obj2.catering.Adicionales.VajillaPremium;
import ar.edu.unahur.obj2.catering.Catalogo.Catalogo;
import ar.edu.unahur.obj2.catering.Contratos.Contrato;
import ar.edu.unahur.obj2.catering.Menus.Menu;
import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;
import ar.edu.unahur.obj2.catering.Servicios.Servicio;

class Etapa2Test {
    private static final double DELTA = 0.01;
    private Catalogo catalogo;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
    }

    @Test
    void precioDeMesaDulceConMozosAdicionalesEs78000() {
        Propuesta p = new MozosAdicionales(catalogo.getMesaDulce());
        assertEquals(78000, p.getPrecio(), DELTA);
        assertEquals("Mesa dulce + Mozos adicionales", p.getNombre());
    }

    @Test
    void precioDeMesaDulceConVajillaPremiumEs72000() {
        Propuesta p = new VajillaPremium(catalogo.getMesaDulce());
        assertEquals(72000, p.getPrecio(), DELTA);
        assertEquals("Mesa dulce + Vajilla premium", p.getNombre());
    }

    @Test
    void precioDeMesaDulceConServicioNocturnoEs72000() {
        Propuesta p = new ServicioNocturno(catalogo.getMesaDulce());
        assertEquals(72000, p.getPrecio(), DELTA);
        assertEquals("Mesa dulce + Servicio nocturno", p.getNombre());
    }

    @Test
    void precioDeMenuParrilleroConMozosYServicioNocturnoEs432000() {
        Propuesta p = new ServicioNocturno(new MozosAdicionales(catalogo.getMenuParrillero()));
        assertEquals(432000, p.getPrecio(), DELTA);
        assertEquals("Menú Parrillero + Mozos adicionales + Servicio nocturno", p.getNombre());
    }

    @Test
    void precioDeMenuParrilleroConServicioNocturnoYMozosEs428400() {
        Propuesta p = new MozosAdicionales(new ServicioNocturno(catalogo.getMenuParrillero()));
        assertEquals(428400, p.getPrecio(), DELTA);
        assertEquals("Menú Parrillero + Servicio nocturno + Mozos adicionales", p.getNombre());
    }

    @Test
    void precioConAdicionalesRepetidosSeAplicaCadaVez() {
        Propuesta vajilla = new VajillaPremium(new VajillaPremium(catalogo.getBrindisDeBienvenida()));
        assertEquals(74000, vajilla.getPrecio(), DELTA);
        assertEquals("Brindis de bienvenida + Vajilla premium + Vajilla premium", vajilla.getNombre());

        Propuesta nocturno = new ServicioNocturno(new ServicioNocturno(catalogo.getMesaDulce()));
        assertEquals(86400, nocturno.getPrecio(), DELTA);
        assertEquals("Mesa dulce + Servicio nocturno + Servicio nocturno", nocturno.getNombre());
    }

    @Test
    void precioDeEventoCompletoConVajillaPremiumEs530800() {
        Propuesta p = new VajillaPremium(catalogo.getEventoCompleto());
        assertEquals(530800, p.getPrecio(), DELTA);
        assertEquals("Evento Completo + Vajilla premium", p.getNombre());
    }

    @Test
    void cantidadDeServiciosEInclusionNoCambianConAdicionales() {
        Propuesta p = new ServicioNocturno(new MozosAdicionales(catalogo.getEventoCompleto()));
        assertEquals(6, p.getCantidadServicios());
        assertTrue(p.tieneServicio("DJ y sonido"));
        assertFalse(p.tieneServicio("Fotógrafo"));
    }

    @Test
    void propuestaOriginalNoCambiaAlAgregarleAdicional() {
        new MozosAdicionales(catalogo.getMenuParrillero());
        assertEquals(342000, catalogo.getMenuParrillero().getPrecio(), DELTA);
        assertEquals("Menú Parrillero", catalogo.getMenuParrillero().getNombre());
    }

    @Test
    void contratoDeLuciaConMozosAdicionalesTieneImporte360000YDescripcion() {
        Contrato c = new Contrato("Lucía", new MozosAdicionales(catalogo.getMenuParrillero()));
        assertEquals(360000, c.getImporte(), DELTA);
        assertEquals("Menú Parrillero + Mozos adicionales", c.getDescripcion());
        assertEquals("Lucía", c.getCliente());
    }

    @Test
    void importeDeContratoNoCambiaSiCambiaLaPropuesta() {
        Menu menu = new Menu("Mini", 0, new Servicio("A", 100));
        Contrato c = new Contrato("Lucía", menu);
        menu.agregar(new Servicio("B", 50));
        assertEquals(150, menu.getPrecio(), DELTA);
        assertEquals(100, c.getImporte(), DELTA);
    }
}