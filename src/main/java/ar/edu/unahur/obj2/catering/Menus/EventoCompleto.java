package ar.edu.unahur.obj2.catering.Menus;

import ar.edu.unahur.obj2.catering.Servicios.BrindisDeBienvenida;

public class EventoCompleto extends Menu {
    public EventoCompleto() {
        super("Evento Completo", 20, /*new MenuParrillero(), new PackFiesta()*/ new BrindisDeBienvenida());
    }
    
}
