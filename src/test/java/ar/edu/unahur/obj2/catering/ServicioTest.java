package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.Menus.MenuParrillero;
import ar.edu.unahur.obj2.catering.Menus.PackFiesta;

class Etapa1Test {
    @Test 
    void precioDeMenuParrilleroEs342000(){
        MenuParrillero menuParrillero = new MenuParrillero();
        assertEquals(342000, menuParrillero.getPrecio());
    }

    @Test 
    void precioDePackFiestaEs256500(){
        PackFiesta packFiesta = new PackFiesta();
        assertEquals(256500, packFiesta.getPrecio());
    }

    @Test
    void cantidadDeServiciosDeMenuParrilleroEs3(){
        MenuParrillero menuParrillero = new MenuParrillero();
        assertEquals(3, menuParrillero.getCantidadServicios());
    }
    @Test 
    void menuParrilleroTieneServicioParilladaCompleta(){
        MenuParrillero menuParrillero = new MenuParrillero();
        assertEquals(true, menuParrillero.tieneServicio("ParilladaCompleta"));
    }
    @Test
    void menuParrilleroNoTieneServicioDjYSonido(){
        MenuParrillero menuParrillero = new MenuParrillero();
        assertEquals(false, menuParrillero.tieneServicio("DJySonido"));
    }
}