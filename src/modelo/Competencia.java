package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Competencia {
    private List<Jugador> jugadores;
    private List<RegistroPartidaxJugador> registros;

    public Competencia() {
        this.jugadores = new ArrayList<>();
        this.registros = new ArrayList<>();
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<RegistroPartidaxJugador> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroPartidaxJugador> registros) {
        this.registros = registros;
    }
}
