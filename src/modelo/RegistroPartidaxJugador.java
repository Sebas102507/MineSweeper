package modelo;

import java.time.LocalTime;

public class RegistroPartidaxJugador {
    private  String nombreJugador;
    private  String codigoJugador;
    private LocalTime tiempoEmpleado;

    public RegistroPartidaxJugador(String nombreJugador, String codigoJugador, LocalTime tiempoEmpleado) {
        this.nombreJugador = nombreJugador;
        this.codigoJugador = codigoJugador;
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getCodigoJugador() {
        return codigoJugador;
    }

    public void setCodigoJugador(String codigoJugador) {
        this.codigoJugador = codigoJugador;
    }

    public LocalTime getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(LocalTime tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }
}
