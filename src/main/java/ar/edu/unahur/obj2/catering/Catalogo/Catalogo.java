package ar.edu.unahur.obj2.catering.Catalogo;

import ar.edu.unahur.obj2.catering.Menus.Menu;
import ar.edu.unahur.obj2.catering.Servicios.Servicio;

public class Catalogo {
    private final Servicio mesaDeEntradas = new Servicio("Mesa de entradas", 80000);
    private final Servicio parrilladaCompleta = new Servicio("Parrillada completa", 240000);
    private final Servicio mesaDulce = new Servicio("Mesa dulce", 60000);
    private final Servicio barraDeTragos = new Servicio("Barra de tragos", 150000);
    private final Servicio djYSonido = new Servicio("DJ y sonido", 120000);
    private final Servicio brindisDeBienvenida = new Servicio("Brindis de bienvenida", 50000);

    private final Menu menuParrillero = new Menu("Menú Parrillero", 10, mesaDeEntradas, parrilladaCompleta, mesaDulce);
    private final Menu packFiesta = new Menu("Pack Fiesta", 5, barraDeTragos, djYSonido);
    private final Menu eventoCompleto = new Menu("Evento Completo", 20, menuParrillero, packFiesta, brindisDeBienvenida);

    public Servicio getMesaDeEntradas() { return mesaDeEntradas; }
    public Servicio getParrilladaCompleta() { return parrilladaCompleta; }
    public Servicio getMesaDulce() { return mesaDulce; }
    public Servicio getBarraDeTragos() { return barraDeTragos; }
    public Servicio getDjYSonido() { return djYSonido; }
    public Servicio getBrindisDeBienvenida() { return brindisDeBienvenida; }
    public Menu getMenuParrillero() { return menuParrillero; }
    public Menu getPackFiesta() { return packFiesta; }
    public Menu getEventoCompleto() { return eventoCompleto; }
}
