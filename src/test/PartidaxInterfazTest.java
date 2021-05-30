package test;

import modelo.Dificultad;
import modelo.EstadoJuego;
import modelo.Idioma;
import modelo.PartidaxInterfaz;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartidaxInterfazTest {

    @Test
    public void crearTablero() {
        PartidaxInterfaz partida= new PartidaxInterfaz(Idioma.ESPANOL, Dificultad.Principiante, EstadoJuego.EN_CURSO);
        partida.crearTablero();
        //partida.verCasillas();
        partida.verTablero();
    }
}