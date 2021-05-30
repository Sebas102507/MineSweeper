package modelo;

public abstract class Casilla {
    protected boolean tieneMarca;
    protected boolean seleccionada;
    protected int posicionX;
    protected int posicionY;

    public Casilla(int posicionX, int posicionY) {
        this.tieneMarca = false;
        this.seleccionada=false;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    public boolean tieneMarca() {
        return tieneMarca;
    }

    public void setTieneMarca(boolean tieneMarca) {
        this.tieneMarca = tieneMarca;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
}
