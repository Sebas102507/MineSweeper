package modelo;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutionException;


public class PartidaxInterfaz implements  Partida{

    private Idioma idioma;
    private Dificultad dificultad;
    private  Tablero tablero;
    private EstadoJuego estadoJuego;
    private Temporizador temporizador;

    public PartidaxInterfaz(Idioma idioma, Dificultad dificultad,EstadoJuego estadoJuego) {
        this.idioma = idioma;
        this.dificultad = dificultad;
        this.estadoJuego=estadoJuego;
        this.temporizador= new Temporizador(LocalTime.now());
    }



    @Override
    public void crearTablero() {
        System.out.println("CREAR EL TABLERO POR INTERFAZ");
        int rand;
        if(this.dificultad==Dificultad.Principiante){
            this.tablero=new Tablero(10,0,0);
            for(int i=0; i<9;i++){
                this.tablero.getCasillas().add(new ArrayList<Casilla>());
                for(int j=0; j<9;j++){
                    this.tablero.getCasillas().get(i).add(new CasillaVacia(i,j));
                }
            }
            this.agregarMinas();
            this.agregarCasillasNumeros();
            this.verTablero();
        }
    }



    public void verCasillas(){
        System.out.println("VER CASILLAS--------------------------------");
        for(int i=0; i<9;i++){
            for(int j=0; j<9;j++){
                System.out.println("CoordenadaX: "+ this.tablero.getCasillas().get(i).get(j).getPosicionX()+
                        " CoordenadaY: "+ this.tablero.getCasillas().get(i).get(j).getPosicionY()+
                        " TIPO: "+ this.tablero.getCasillas().get(i).get(j).getClass()
                );
            }
        }
    }

    public void verTablero(){
        System.out.println("VER CASILLAS--------------------------------");
        for(int i=0; i<9;i++){
            for(int j=0; j<9;j++){
                if(this.tablero.getCasillas().get(i).get(j) instanceof CasillaMina){
                    System.out.print("| * ");
                }else if(this.tablero.getCasillas().get(i).get(j) instanceof CasillaNumero){
                    System.out.print("| "+((CasillaNumero) this.tablero.getCasillas().get(i).get(j)).getNumero()+" ");
                }else{
                    System.out.print("| _ ");
                }
            }
            System.out.println("");
        }
    }

    private void agregarMinas(){
        if(this.dificultad==Dificultad.Principiante){
            Random random= new Random();
            for(int i=0; i<10;i++){
                int x= random.nextInt(8);
                int y= random.nextInt(8);
                System.out.println("X: "+x+" Y: "+y);
                if(this.tablero.getCasillas().get(x).get(y) instanceof  CasillaMina){
                    i--;
                }else{
                    this.tablero.getCasillas().get(x).set(y,new CasillaMina(x,y));
                }
            }
        }
    }


    private void agregarCasillasNumeros(){
        if(this.dificultad==Dificultad.Principiante){
            for(int i=0; i<9;i++){
                for(int j=0; j<9;j++){
                    int numMinasAdy=getNumMinasAdyacentes(i,j);
                    if(numMinasAdy>0 && !(this.tablero.getCasillas().get(i).get(j) instanceof CasillaMina)){
                        this.tablero.getCasillas().get(i).set(j,new CasillaNumero(i,j,numMinasAdy));
                    }
                }
            }
        }
    }


    private int getNumMinasAdyacentes(int x, int y){
        int contador=0;

        try{
            if(this.tablero.getCasillas().get(x-1).get(y) instanceof CasillaMina){ //Superior
                contador++;
            }
        }catch (Exception e){}

        try{
            if(this.tablero.getCasillas().get(x+1).get(y) instanceof CasillaMina){ //Inferior
                contador++;
            }
        }catch (Exception e){}

        try{
            if(this.tablero.getCasillas().get(x).get(y-1) instanceof CasillaMina){ //Izquierda
                contador++;
            }
        }catch (Exception e){}


        try{
            if(this.tablero.getCasillas().get(x).get(y+1) instanceof CasillaMina){ //Derecha
                contador++;
            }
        }catch (Exception e){}

        ////////////////////////

        try{
            if(this.tablero.getCasillas().get(x-1).get(y-1) instanceof CasillaMina){ //Diagonal Superior Izquierdo
                contador++;
            }
        }catch (Exception e){}

        try{
            if(this.tablero.getCasillas().get(x-1).get(y+1) instanceof CasillaMina){ //Diagonal Superior Derecho
                contador++;
            }
        }catch (Exception e){}


        try{
            if(this.tablero.getCasillas().get(x+1).get(y-1) instanceof CasillaMina){ //Diagonal Inferior Izquierdo
                contador++;
            }
        }catch (Exception e){}

        try{
            if(this.tablero.getCasillas().get(x+1).get(y+1) instanceof CasillaMina){ //Diagonal Inferior Derecho
                contador++;
            }
        }catch (Exception e){}

        return contador;
    }

    @Override
    public boolean validarPartidaGanada(){
        //System.out.println("TOTAL DE CASILLAS: "+(tablero.getCasillas().size()*tablero.getCasillas().get(0).size()));
        //System.out.println("TOTAL DE CASILLAS SIN MINAS: "+((tablero.getCasillas().size()*tablero.getCasillas().get(0).size())-tablero.getCantidadMinas()));
        //System.out.println("TOTAL DE CASILLAS SELECCIONADAS: "+tablero.getNumCasillasSeleccionadas());
        if((tablero.getCasillas().size()*tablero.getCasillas().get(0).size())-tablero.getCantidadMinas()==tablero.getNumCasillasSeleccionadas()){
            //System.out.println("SE GANOOOOOO EL JUEGO!!");
            this.setEstadoJuego(EstadoJuego.GANADO);
            return  true;
        }else{
            //System.out.println("NO SE HAA GANADO EL JUEGO!!");
            return false;
        }
    }


    @Override
    public Temporizador getTemporizador() { return temporizador; }

    @Override
    public void setTemporizador(Temporizador temporizador) { this.temporizador = temporizador;}

    @Override
    public Idioma getIdioma() {
        return this.idioma;
    }

    @Override
    public void setIdioma(Idioma idioma) {
        this.idioma=idioma;
    }

    @Override
    public EstadoJuego getEstadoJuego() {
        return this.estadoJuego;
    }

    @Override
    public void setEstadoJuego(EstadoJuego estado) {
        this.estadoJuego=estado;
    }

    @Override
    public Dificultad getDificultad() {
        return this.dificultad;
    }

    @Override
    public void setDificultad(Dificultad dificultad) {
        this.dificultad=dificultad;
    }

    @Override
    public Tablero getTablero() {
        return this.tablero;
    }

    @Override
    public void setTablero(Tablero tablero) {
        this.tablero=tablero;
    }
}
