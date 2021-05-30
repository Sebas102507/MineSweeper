package modelo;

import java.io.File;

public class CasillaMina extends Casilla {
    private final File imagenMina= new File("assets/explosives.png");
    public CasillaMina(int posicionX, int posicionY) {
        super(posicionX, posicionY);
    }
    public File getImagenMina() {
        return imagenMina;
    }
}
