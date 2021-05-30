package modelo;

public class CasillaNumero extends Casilla{

    private int numero;

    public CasillaNumero(int posicionX, int posicionY, int numero) {
        super(posicionX, posicionY);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
