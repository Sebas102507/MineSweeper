package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private int cantidadMinas;
    private int numMarcasHechas;
    private int numMinasDescubiertas;
    private int numCasillasSeleccionadas;
    private List<List<Casilla>> casillas;

    public Tablero(int cantidadMinas, int numMarcasHechas, int numMinasDescubiertas) {
        this.cantidadMinas = cantidadMinas;
        this.numMarcasHechas = numMarcasHechas;
        this.numMinasDescubiertas = numMinasDescubiertas;
        this.casillas= new ArrayList<>();
        this.numCasillasSeleccionadas =0;
    }

    public int getNumCasillasSeleccionadas() {
        return numCasillasSeleccionadas;
    }

    public void setNumCasillasSeleccionadas(int numCasillasSeleccionadas) {
        this.numCasillasSeleccionadas = numCasillasSeleccionadas;
    }

    public int getCantidadMinas() {
        return cantidadMinas;
    }

    public void setCantidadMinas(int cantidadMinas) {
        this.cantidadMinas = cantidadMinas;
    }

    public int getNumMarcasHechas() {
        return numMarcasHechas;
    }

    public void setNumMarcasHechas(int numMarcasHechas) {
        this.numMarcasHechas = numMarcasHechas;
    }

    public int getNumMinasDescubiertas() {
        return numMinasDescubiertas;
    }

    public void setNumMinasDescubiertas(int numMinasDescubiertas) {
        this.numMinasDescubiertas = numMinasDescubiertas;
    }

    public List<List<Casilla>> getCasillas() {
        return casillas;
    }

    public void setCasillas(List<List<Casilla>> casillas) {
        this.casillas = casillas;
    }

}
