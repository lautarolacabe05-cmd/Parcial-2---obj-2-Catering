package ar.edu.unahur.obj2.catering.Menus;

import ar.edu.unahur.obj2.catering.Servicios.BarraDeTragos;
import ar.edu.unahur.obj2.catering.Servicios.DJySonido;

public class PackFiesta extends Menu {
    public PackFiesta() { 
        super("Pack Fiesta", 5, new BarraDeTragos(), new DJySonido());
    }
}
