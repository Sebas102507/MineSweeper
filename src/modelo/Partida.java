package modelo;

import java.time.LocalTime;

public interface Partida {

    public void crearTablero();

    public Idioma getIdioma();

    public void setIdioma(Idioma idioma);

    public EstadoJuego getEstadoJuego();

    public void setEstadoJuego(EstadoJuego estadoJuego);

    public Dificultad getDificultad();

    public void setDificultad(Dificultad dificultad);

    public Tablero getTablero();

    public void setTablero(Tablero tablero);

    public Temporizador getTemporizador();

    public void setTemporizador(Temporizador temporizador);

    public boolean validarPartidaGanada();
}
