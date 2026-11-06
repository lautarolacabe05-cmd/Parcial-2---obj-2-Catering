package ar.edu.unahur.obj2.catering.Menus;

import ar.edu.unahur.obj2.catering.Servicios.MesaDeEntradas;
import ar.edu.unahur.obj2.catering.Servicios.MesaDulce;
import ar.edu.unahur.obj2.catering.Servicios.ParilladaCompleta;

public class MenuParrillero extends Menu {
    public MenuParrillero() {
        super("Menu Parrillero", 10, new ParilladaCompleta(), new MesaDeEntradas(), new MesaDulce());
    }

    public short getCantidadServicios() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object tieneServicio(String parilladaCompleta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
