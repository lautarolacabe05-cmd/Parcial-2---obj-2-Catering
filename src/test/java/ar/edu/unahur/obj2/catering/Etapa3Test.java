package ar.edu.unahur.obj2.catering;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.Catalogo.Catalogo;
import ar.edu.unahur.obj2.catering.Contratos.Contrato;
import ar.edu.unahur.obj2.catering.Excepciones.NadaParaRevertirException;
import ar.edu.unahur.obj2.catering.Excepciones.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.Excepciones.PropuestaNoIncluidaException;
import ar.edu.unahur.obj2.catering.Gestor.GestorDeCarta;
import ar.edu.unahur.obj2.catering.Modificaciones.ActualizarPrecio;
import ar.edu.unahur.obj2.catering.Modificaciones.AgregarPropuesta;
import ar.edu.unahur.obj2.catering.Modificaciones.Modificacion;
import ar.edu.unahur.obj2.catering.Modificaciones.QuitarPropuesta;
import ar.edu.unahur.obj2.catering.Servicios.Servicio;

class Etapa3Test {
    private static final double DELTA = 0.01;

    private final Catalogo catalogo = new Catalogo();
    private final GestorDeCarta gestor = new GestorDeCarta();
    private final Servicio fotografo = new Servicio("Fotógrafo", 100000);

    @Test
    void agregarFotografoAMenuParrilleroYRevertirVuelveAlPrecioOriginal() {
        gestor.aplicar(new AgregarPropuesta(catalogo.getMenuParrillero(), fotografo));
        assertEquals(432000, catalogo.getMenuParrillero().getPrecio(), DELTA);
        assertTrue(catalogo.getMenuParrillero().tieneServicio("Fotógrafo"));

        gestor.revertirUltima();
        assertEquals(342000, catalogo.getMenuParrillero().getPrecio(), DELTA);
        assertFalse(catalogo.getMenuParrillero().tieneServicio("Fotógrafo"));
    }

    @Test
    void quitarMesaDulceDeMenuParrilleroYRevertirVuelveAlPrecioOriginal() {
        gestor.aplicar(new QuitarPropuesta(catalogo.getMenuParrillero(), catalogo.getMesaDulce()));
        assertEquals(288000, catalogo.getMenuParrillero().getPrecio(), DELTA);
        assertFalse(catalogo.getMenuParrillero().tieneServicio("Mesa dulce"));

        gestor.revertirUltima();
        assertEquals(342000, catalogo.getMenuParrillero().getPrecio(), DELTA);
        assertTrue(catalogo.getMenuParrillero().tieneServicio("Mesa dulce"));
    }

    @Test
    void actualizarPrecioDeParrilladaCompletaYRevertirDevuelveElPrecioAnterior() {
        gestor.aplicar(new ActualizarPrecio(catalogo.getParrilladaCompleta(), 260000));
        assertEquals(260000, catalogo.getParrilladaCompleta().getPrecio(), DELTA);

        gestor.revertirUltima();
        assertEquals(240000, catalogo.getParrilladaCompleta().getPrecio(), DELTA);
    }

    @Test
    void secuenciaInmediataSeRevierteEnOrdenInversoHastaQuedarSinHistorial() {
        var menu = catalogo.getMenuParrillero();

        gestor.aplicar(new AgregarPropuesta(menu, fotografo));
        assertEquals(432000, menu.getPrecio(), DELTA);
        gestor.aplicar(new ActualizarPrecio(catalogo.getParrilladaCompleta(), 260000));
        assertEquals(450000, menu.getPrecio(), DELTA);
        gestor.aplicar(new QuitarPropuesta(menu, catalogo.getMesaDulce()));
        assertEquals(396000, menu.getPrecio(), DELTA);

        gestor.revertirUltima();
        assertEquals(450000, menu.getPrecio(), DELTA);
        gestor.revertirUltima();
        assertEquals(432000, menu.getPrecio(), DELTA);
        gestor.revertirUltima();
        assertEquals(342000, menu.getPrecio(), DELTA);

        assertThrows(NadaParaRevertirException.class, () -> gestor.revertirUltima());
    }

    @Test
    void dosActualizacionesCreadasAntesDeAplicarseSeRevierteUnaAUna() {
        Modificacion primera = new ActualizarPrecio(catalogo.getParrilladaCompleta(), 260000);
        Modificacion segunda = new ActualizarPrecio(catalogo.getParrilladaCompleta(), 300000);

        gestor.aplicar(primera);
        gestor.aplicar(segunda);
        assertEquals(300000, catalogo.getParrilladaCompleta().getPrecio(), DELTA);

        gestor.revertirUltima();
        assertEquals(260000, catalogo.getParrilladaCompleta().getPrecio(), DELTA);
        gestor.revertirUltima();
        assertEquals(240000, catalogo.getParrilladaCompleta().getPrecio(), DELTA);
    }

    @Test
    void loteAplicadoDejaCadaModificacionEnElHistorialEnOrden() {
        var menu = catalogo.getMenuParrillero();
        gestor.aplicarLote(List.of(
                new AgregarPropuesta(menu, fotografo),
                new ActualizarPrecio(catalogo.getParrilladaCompleta(), 260000),
                new QuitarPropuesta(menu, catalogo.getMesaDulce())));
        assertEquals(396000, menu.getPrecio(), DELTA);

        gestor.revertirUltima();
        assertEquals(450000, menu.getPrecio(), DELTA);
        gestor.revertirUltima();
        gestor.revertirUltima();
        assertEquals(342000, menu.getPrecio(), DELTA);

        assertThrows(NadaParaRevertirException.class, () -> gestor.revertirUltima());
    }

    @Test
    void loteConUnaModificacionInvalidaDejaElCatalogoComoEstabaYNoRegistraNada() {
        var menu = catalogo.getMenuParrillero();
        gestor.aplicar(new AgregarPropuesta(menu, fotografo));

        List<Modificacion> lote = List.of(
                new ActualizarPrecio(catalogo.getParrilladaCompleta(), 260000),
                new QuitarPropuesta(menu, catalogo.getBarraDeTragos()));
        assertThrows(PropuestaNoIncluidaException.class, () -> gestor.aplicarLote(lote));

        assertEquals(432000, menu.getPrecio(), DELTA);
        assertEquals(240000, catalogo.getParrilladaCompleta().getPrecio(), DELTA);

        gestor.revertirUltima();
        assertEquals(342000, menu.getPrecio(), DELTA);
        assertThrows(NadaParaRevertirException.class, () -> gestor.revertirUltima());
    }

    @Test
    void quitarUnaPropuestaQueNoEsComponenteLanzaExcepcionNoAlteraElMenuYNoSeRegistra() {
        var menu = catalogo.getMenuParrillero();
        assertThrows(PropuestaNoIncluidaException.class,
                () -> gestor.aplicar(new QuitarPropuesta(menu, catalogo.getBarraDeTragos())));

        assertEquals(342000, menu.getPrecio(), DELTA);
        assertEquals(3, menu.getCantidadServicios());
        assertThrows(NadaParaRevertirException.class, () -> gestor.revertirUltima());
    }

    @Test
    void actualizarUnPrecioANegativoLanzaPrecioNegativoExceptionYNoCambiaElPrecio() {
        assertThrows(PrecioNegativoException.class,
                () -> gestor.aplicar(new ActualizarPrecio(catalogo.getParrilladaCompleta(), -1)));
        assertEquals(240000, catalogo.getParrilladaCompleta().getPrecio(), DELTA);
    }

    @Test
    void importeDeContratoNoCambiaAlActualizarElPrecioDeUnServicio() {
        Contrato contrato = new Contrato("Lucía", catalogo.getMenuParrillero());
        gestor.aplicar(new ActualizarPrecio(catalogo.getParrilladaCompleta(), 260000));

        assertEquals(360000, catalogo.getMenuParrillero().getPrecio(), DELTA);
        assertEquals(342000, contrato.getImporte(), DELTA);
    }
}