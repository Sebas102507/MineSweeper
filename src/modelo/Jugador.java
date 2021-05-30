package modelo;

import com.sun.org.apache.bcel.internal.classfile.EnumElementValue;

public class Jugador {

    private String nombre;
    private String codigo;
    private Partida partida;

    public Jugador(){}

    public Jugador(String nombre, String codigo){
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
