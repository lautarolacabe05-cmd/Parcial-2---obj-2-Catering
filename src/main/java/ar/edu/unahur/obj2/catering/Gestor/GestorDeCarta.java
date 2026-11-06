package ar.edu.unahur.obj2.catering.Gestor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import ar.edu.unahur.obj2.catering.Excepciones.NadaParaRevertirException;
import ar.edu.unahur.obj2.catering.Modificaciones.Modificacion;

public class GestorDeCarta {
    private final Deque<Modificacion> historial = new ArrayDeque<>();

    public void aplicar(Modificacion modificacion) {
        aplicarLote(List.of(modificacion));
    }

    public void aplicarLote(List<? extends Modificacion> modificaciones) {
        List<Modificacion> aplicadas = new ArrayList<>();
        try {
            for (Modificacion m : modificaciones) {
                m.aplicar();
                aplicadas.add(m);
            }
        } catch (RuntimeException e) {
            for (int i = aplicadas.size() - 1; i >= 0; i--) {
                aplicadas.get(i).revertir();
            }
            throw e;
        }
        aplicadas.forEach(historial::push);
    }

    public void revertirUltima() {
        if (historial.isEmpty()) {
            throw new NadaParaRevertirException("No hay modificaciones para revertir");
        }
        historial.pop().revertir();
    }
}
