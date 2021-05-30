package view_Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import modelo.*;
import persistencia.ManejadorDeArchivos;
import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public GridPane gridTablero;
    public ComboBox dificultadChooser;
    public ComboBox idiomaChooser;
    public Button ayudaButton;
    public TextField nombreInput;
    public Label textoNombreVacio;
    public Pane imagenEstadoJuego;
    final int tamCasilla=40;
    public Pane paneIdicadorBanderas;
    public Label labelNumBanderas;
    public Label labelTiempoEmpleado;
    Jugador jugadorActual;
    Competencia competencia;

    final ImageView imagenPartidaInicio=new ImageView(new File("assets/feliz.png").toURI().toString());
    final ImageView imagenPartidaPerdida=new ImageView(new File("assets/perdido.png").toURI().toString());
    final ImageView imagenPartidaGanada=new ImageView(new File("assets/gana.png").toURI().toString());
    final ImageView imagenBomba=new ImageView(new File("assets/explosives.png").toURI().toString());
    final ImageView imagenMarca=new ImageView(new File("assets/marca.png").toURI().toString());
    ManejadorDeArchivos manejadorDeArchivos;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            manejadorDeArchivos= new ManejadorDeArchivos();

            this.imagenPartidaInicio.setFitHeight(30);
            this.imagenPartidaInicio.setFitWidth(30);

            this.imagenPartidaPerdida.setFitHeight(30);
            this.imagenPartidaPerdida.setFitWidth(30);

            this.imagenPartidaGanada.setFitHeight(30);
            this.imagenPartidaGanada.setFitWidth(30);

            this.imagenBomba.setFitHeight(25);
            this.imagenBomba.setFitWidth(25);

            this.imagenMarca.setFitHeight(30);
            this.imagenMarca.setFitWidth(30);


            this.dificultadChooser.setItems(FXCollections.observableArrayList(Dificultad.values()));
            this.idiomaChooser.setItems(FXCollections.observableArrayList(Idioma.values()));
            this.textoNombreVacio.setVisible(false);
            this.competencia= new Competencia();
            jugadorActual= new Jugador();
            this.imagenEstadoJuego.getChildren().clear();
            this.imagenEstadoJuego.getChildren().add(this.imagenPartidaInicio);
            this.paneIdicadorBanderas.getChildren().add(this.imagenMarca);
        }catch (Exception e){
            System.err.println("EL ERROR DICE: "+e);
        }

        //this.casilla.setStyle("-fx-background-color: #ffe1b5;");
        //this.gridTablero.add(this.casilla,1,1);
    }

    public void comezarPartida(){
        String nombreJugador= nombreInput.getText();
        if(nombreJugador.length()==0){
            this.textoNombreVacio.setVisible(true);
        }else{
            this.textoNombreVacio.setVisible(false);
            if(this.dificultadChooser.getSelectionModel().getSelectedItem()==Dificultad.Principiante){
                if(this.jugadorActual.getPartida()!=null){
                    if(jugadorActual.getPartida().getEstadoJuego()==EstadoJuego.GANADO || jugadorActual.getPartida().getEstadoJuego()==EstadoJuego.PERDIDO){
                        this.gridTablero.getChildren().clear();
                        this.imagenEstadoJuego.getChildren().clear();
                        //System.out.println("ENTRÉ ACÁ");
                        this.imagenEstadoJuego.getChildren().add(this.imagenPartidaInicio);
                    }
                }

                if(jugadorActual.getPartida()==null || jugadorActual.getPartida().getEstadoJuego()==EstadoJuego.GANADO || jugadorActual.getPartida().getEstadoJuego()==EstadoJuego.PERDIDO){
                    jugadorActual.setNombre(nombreJugador);
                    jugadorActual.setCodigo("0001");
                    jugadorActual.setPartida(new PartidaxInterfaz(Idioma.ESPANOL,Dificultad.Principiante,EstadoJuego.EN_CURSO));
                    jugadorActual.getPartida().crearTablero();
                    this.competencia.getJugadores().add(this.jugadorActual);
                    this.labelTiempoEmpleado.setText("");
                    System.out.println("----------AQUÍ SE CREA EL TABLERO EN LA INTERFAZ--------");
                    for(int i=0; i<9; i++){
                        for(int j=0; j<9; j++){

                            if(jugadorActual.getPartida().getTablero().getCasillas().get(i).get(j) instanceof CasillaMina){
                                System.out.print("| * ");
                            }else if(jugadorActual.getPartida().getTablero().getCasillas().get(i).get(j) instanceof CasillaNumero){
                                System.out.print("| "+((CasillaNumero) jugadorActual.getPartida().getTablero().getCasillas().get(i).get(j)).getNumero()+" ");
                            }else{
                                System.out.print("| _ ");
                            }
                            this.gridTablero.add(this.crearCasilla(jugadorActual,jugadorActual.getPartida().getTablero().getCasillas().get(i).get(j)),j,i);
                        }
                        System.out.println("");
                    }
                    this.labelNumBanderas.setText(""+(jugadorActual.getPartida().getTablero().getCantidadMinas()-jugadorActual.getPartida().getTablero().getNumMarcasHechas()));
                }else  if( jugadorActual.getPartida().getEstadoJuego()==EstadoJuego.EN_CURSO){
                    System.out.println("EL JUEGO SIGUE EN CURSO Y NO PUEDO HACER NADA");
                }
            }
        }

    }

    private Button crearCasilla(Jugador jugadorActual,Casilla casilla){
        Button nuevaCasilla= new Button("");
        nuevaCasilla.setPrefHeight(this.tamCasilla);
        nuevaCasilla.setPrefWidth(this.tamCasilla);
        nuevaCasilla.setStyle("-fx-background-color:#ffae38");
        nuevaCasilla.setOnMouseClicked(event -> {
            if(event.getButton()== MouseButton.PRIMARY){
                if(!casilla.isSeleccionada()){
                    if(jugadorActual.getPartida().getEstadoJuego()!=EstadoJuego.GANADO && jugadorActual.getPartida().getEstadoJuego()!=EstadoJuego.PERDIDO){
                        if(casilla instanceof CasillaMina){
                            nuevaCasilla.setStyle("-fx-background-color:#ff7438;");
                            nuevaCasilla.setGraphic(this.imagenBomba);
                            this.imagenEstadoJuego.getChildren().clear();
                            this.imagenEstadoJuego.getChildren().add(this.imagenPartidaPerdida);
                            this.jugadorActual.getPartida().setEstadoJuego(EstadoJuego.PERDIDO);
                            this.jugadorActual.getPartida().getTemporizador().setHoraFinalizado(LocalTime.now());
                            this.labelTiempoEmpleado.setText(this.jugadorActual.getPartida().getTemporizador().toString());

                        }else  if(casilla instanceof CasillaNumero){
                            nuevaCasilla.setStyle("-fx-background-color:#ffae38");
                            nuevaCasilla.setGraphic(null);
                            nuevaCasilla.setText(""+ ((CasillaNumero) casilla).getNumero());
                            jugadorActual.getPartida().getTablero().setNumCasillasSeleccionadas(jugadorActual.getPartida().getTablero().getNumCasillasSeleccionadas()+1);

                        }else if(casilla instanceof  CasillaVacia){
                            nuevaCasilla.setStyle("-fx-background-color:#fccb7c;");
                            nuevaCasilla.setGraphic(null);
                            nuevaCasilla.setText("");

                            jugadorActual.getPartida().getTablero().setNumCasillasSeleccionadas(jugadorActual.getPartida().getTablero().getNumCasillasSeleccionadas()+1);

                            this.abrirCasillasVaciasIzquierda(jugadorActual.getPartida().getTablero(),casilla,casilla);
                            this.abrirCasillasVaciasDerecha(jugadorActual.getPartida().getTablero(),casilla,casilla);

                            this.abrirCasillasVaciasArriba(jugadorActual.getPartida().getTablero(),casilla,casilla);
                            this.abrirCasillasVaciasAbajo(jugadorActual.getPartida().getTablero(),casilla,casilla);

                            this.abrirCasillasVaciasDiagonalSuperiorIzq(jugadorActual.getPartida().getTablero(),casilla,casilla);
                            this.abrirCasillasVaciasDiagonalSuperiorDer(jugadorActual.getPartida().getTablero(),casilla,casilla);

                            this.abrirCasillasVaciasDiagonalInferiorIzq(jugadorActual.getPartida().getTablero(),casilla,casilla);
                            this.abrirCasillasVaciasDiagonalInferiorDer(jugadorActual.getPartida().getTablero(),casilla,casilla);


                        }
                        if(casilla.tieneMarca()){
                            if(jugadorActual.getPartida().getTablero().getNumMarcasHechas()>0) jugadorActual.getPartida().getTablero().setNumMarcasHechas(jugadorActual.getPartida().getTablero().getNumMarcasHechas()-1);
                            this.labelNumBanderas.setText(""+(jugadorActual.getPartida().getTablero().getCantidadMinas()-jugadorActual.getPartida().getTablero().getNumMarcasHechas()));
                        }
                    }
                    if(jugadorActual.getPartida().validarPartidaGanada()){
                        this.imagenEstadoJuego.getChildren().clear();
                        this.imagenEstadoJuego.getChildren().add(this.imagenPartidaGanada);
                        this.jugadorActual.getPartida().getTemporizador().setHoraFinalizado(LocalTime.now());
                        this.labelTiempoEmpleado.setText(this.jugadorActual.getPartida().getTemporizador().toString());
                        this.competencia.getRegistros().add(new RegistroPartidaxJugador(this.jugadorActual.getNombre(),this.jugadorActual.getCodigo(),this.jugadorActual.getPartida().getTemporizador().getTiempoEmpleado()));
                        System.out.println("EL JUGADOR DURANTE PARTIDA GANADA: "+this.competencia.getJugadores().get(0).getPartida().getEstadoJuego());
                    }

                }

            }else if(event.getButton()== MouseButton.SECONDARY){
               if(!casilla.isSeleccionada()){
                    if(casilla.tieneMarca()){
                        if(jugadorActual.getPartida().getTablero().getNumMarcasHechas()>0){
                            jugadorActual.getPartida().getTablero().setNumMarcasHechas(jugadorActual.getPartida().getTablero().getNumMarcasHechas()-1);
                            casilla.setTieneMarca(false);
                            this.labelNumBanderas.setText(""+(jugadorActual.getPartida().getTablero().getCantidadMinas()-jugadorActual.getPartida().getTablero().getNumMarcasHechas()));
                            nuevaCasilla.setStyle("-fx-background-color:#ffae38");
                            nuevaCasilla.setGraphic(null);
                        }
                        //nuevaCasilla.setGraphic(null);
                    }else{
                        if(jugadorActual.getPartida().getTablero().getNumMarcasHechas()<jugadorActual.getPartida().getTablero().getCantidadMinas()) {
                            casilla.setTieneMarca(true);
                            jugadorActual.getPartida().getTablero().setNumMarcasHechas(jugadorActual.getPartida().getTablero().getNumMarcasHechas()+1);
                            this.labelNumBanderas.setText(""+(jugadorActual.getPartida().getTablero().getCantidadMinas()-jugadorActual.getPartida().getTablero().getNumMarcasHechas()));
                            nuevaCasilla.setStyle("-fx-background-color:#ff7438;");
                            ImageView img=new ImageView(new File("assets/marca.png").toURI().toString());
                            img.setFitHeight(20);
                            img.setFitWidth(20);
                            nuevaCasilla.setGraphic(img);

                        }
                    }
                }else{
                    System.out.println("YA HA SIDO SELECCIONADA");
                }
            }
        });
        return nuevaCasilla;
    }



    public void guardarFiguraCSV(ActionEvent actionEvent) {
        manejadorDeArchivos.setNombreArchivo("FigurasPersistencia.csv");
        //manejadorDeArchivos.escribirArchivoCSV(figuraActual);
    }

    public void leerFigurasCSV(ActionEvent actionEvent) {
        manejadorDeArchivos.leerArchivoCSV();
    }




    public void abrirCasillasVaciasDerecha(Tablero tablero,Casilla casillaPresionada,Casilla casillaOriginal){
        try{
            if(!(tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()) instanceof  CasillaVacia)){
                return;
            }else{
                for (Node nodo: this.gridTablero.getChildren()) {
                    if(GridPane.getRowIndex(nodo)==casillaPresionada.getPosicionX() && GridPane.getColumnIndex(nodo)==casillaPresionada.getPosicionY() && (!casillaPresionada.isSeleccionada() || casillaPresionada==casillaOriginal)){
                        if(casillaPresionada!=casillaOriginal){
                            tablero.setNumCasillasSeleccionadas(tablero.getNumCasillasSeleccionadas()+1);
                        }
                        nodo.setStyle("-fx-background-color:#fccb7c;");
                        tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()).setSeleccionada(true);
                        abrirCasillasVaciasDerecha(tablero,tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()+1),casillaOriginal);
                    }
                }
            }
        }catch (Exception e){}
    }

    public void abrirCasillasVaciasIzquierda(Tablero tablero,Casilla casillaPresionada, Casilla casillaOriginal){
        try{
            if(!(tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()) instanceof  CasillaVacia)){
                return;
            }else{
                for (Node nodo: this.gridTablero.getChildren()) {
                    if(GridPane.getRowIndex(nodo)==casillaPresionada.getPosicionX() && GridPane.getColumnIndex(nodo)==casillaPresionada.getPosicionY() && (!casillaPresionada.isSeleccionada() || casillaPresionada==casillaOriginal)){
                        if(casillaPresionada!=casillaOriginal){
                            tablero.setNumCasillasSeleccionadas(tablero.getNumCasillasSeleccionadas()+1);
                        }
                        nodo.setStyle("-fx-background-color:#fccb7c;");
                        tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()).setSeleccionada(true);
                        abrirCasillasVaciasIzquierda(tablero,tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()-1),casillaOriginal);
                    }
                }
            }
        }catch (Exception e){}
    }

    public void abrirCasillasVaciasArriba(Tablero tablero,Casilla casillaPresionada,Casilla casillaOriginal){
        try{
            if(!(tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()) instanceof  CasillaVacia)){
                return;
            }else{
                for (Node nodo: this.gridTablero.getChildren()) {
                    if(GridPane.getRowIndex(nodo)==casillaPresionada.getPosicionX() && GridPane.getColumnIndex(nodo)==casillaPresionada.getPosicionY() && (!casillaPresionada.isSeleccionada() || casillaPresionada==casillaOriginal)){
                        if(casillaPresionada!=casillaOriginal){
                            tablero.setNumCasillasSeleccionadas(tablero.getNumCasillasSeleccionadas()+1);
                        }
                        nodo.setStyle("-fx-background-color:#fccb7c;");
                        tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()).setSeleccionada(true);
                        abrirCasillasVaciasArriba(tablero,tablero.getCasillas().get(casillaPresionada.getPosicionX()-1).get(casillaPresionada.getPosicionY()),casillaOriginal);
                    }
                }
            }
        }catch (Exception e){}
    }

    public void abrirCasillasVaciasAbajo(Tablero tablero,Casilla casillaPresionada,Casilla casillaOriginal){
        try{
            if(!(tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()) instanceof  CasillaVacia)){
                return;
            }else{
                for (Node nodo: this.gridTablero.getChildren()) {
                    if(GridPane.getRowIndex(nodo)==casillaPresionada.getPosicionX() && GridPane.getColumnIndex(nodo)==casillaPresionada.getPosicionY() && (!casillaPresionada.isSeleccionada() || casillaPresionada==casillaOriginal)){
                        if(casillaPresionada!=casillaOriginal){
                            tablero.setNumCasillasSeleccionadas(tablero.getNumCasillasSeleccionadas()+1);
                        }
                        nodo.setStyle("-fx-background-color:#fccb7c;");
                        tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()).setSeleccionada(true);
                        abrirCasillasVaciasAbajo(tablero,tablero.getCasillas().get(casillaPresionada.getPosicionX()+1).get(casillaPresionada.getPosicionY()),casillaOriginal);
                    }
                }
            }
        }catch (Exception e){}
    }

    public void abrirCasillasVaciasDiagonalSuperiorIzq(Tablero tablero,Casilla casillaPresionada,Casilla casillaOriginal){
        try{
            if(!(tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()) instanceof  CasillaVacia)){
                return;
            }else{
                for (Node nodo: this.gridTablero.getChildren()) {
                    if(GridPane.getRowIndex(nodo)==casillaPresionada.getPosicionX() && GridPane.getColumnIndex(nodo)==casillaPresionada.getPosicionY() && (!casillaPresionada.isSeleccionada() || casillaPresionada==casillaOriginal)){
                        if(casillaPresionada!=casillaOriginal){
                            tablero.setNumCasillasSeleccionadas(tablero.getNumCasillasSeleccionadas()+1);
                        }
                        nodo.setStyle("-fx-background-color:#fccb7c;");
                        tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()).setSeleccionada(true);
                        abrirCasillasVaciasDiagonalSuperiorIzq(tablero,tablero.getCasillas().get(casillaPresionada.getPosicionX()-1).get(casillaPresionada.getPosicionY()-1),casillaOriginal);
                    }
                }
            }
        }catch (Exception e){}
    }

    public void abrirCasillasVaciasDiagonalSuperiorDer(Tablero tablero,Casilla casillaPresionada,Casilla casillaOriginal){
        try{
            if(!(tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()) instanceof  CasillaVacia)){
                return;
            }else{
                for (Node nodo: this.gridTablero.getChildren()) {
                    if(GridPane.getRowIndex(nodo)==casillaPresionada.getPosicionX() && GridPane.getColumnIndex(nodo)==casillaPresionada.getPosicionY() && (!casillaPresionada.isSeleccionada() || casillaPresionada==casillaOriginal)){
                        if(casillaPresionada!=casillaOriginal){
                            tablero.setNumCasillasSeleccionadas(tablero.getNumCasillasSeleccionadas()+1);
                        }
                        nodo.setStyle("-fx-background-color:#fccb7c;");
                        tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()).setSeleccionada(true);
                        abrirCasillasVaciasDiagonalSuperiorDer(tablero,tablero.getCasillas().get(casillaPresionada.getPosicionX()-1).get(casillaPresionada.getPosicionY()+1),casillaOriginal);
                    }
                }
            }
        }catch (Exception e){}
    }

    public void abrirCasillasVaciasDiagonalInferiorIzq(Tablero tablero,Casilla casillaPresionada,Casilla casillaOriginal){
        try{
            if(!(tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()) instanceof  CasillaVacia)){
                return;
            }else{
                for (Node nodo: this.gridTablero.getChildren()) {
                    if(GridPane.getRowIndex(nodo)==casillaPresionada.getPosicionX() && GridPane.getColumnIndex(nodo)==casillaPresionada.getPosicionY() && (!casillaPresionada.isSeleccionada() || casillaPresionada==casillaOriginal)){
                        if(casillaPresionada!=casillaOriginal){
                            tablero.setNumCasillasSeleccionadas(tablero.getNumCasillasSeleccionadas()+1);
                        }
                        nodo.setStyle("-fx-background-color:#fccb7c;");
                        tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()).setSeleccionada(true);
                        abrirCasillasVaciasDiagonalInferiorIzq(tablero,tablero.getCasillas().get(casillaPresionada.getPosicionX()+1).get(casillaPresionada.getPosicionY()-1),casillaOriginal);
                    }
                }
            }
        }catch (Exception e){}
    }

    public void abrirCasillasVaciasDiagonalInferiorDer(Tablero tablero,Casilla casillaPresionada,Casilla casillaOriginal){
        try{
            if(!(tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()) instanceof  CasillaVacia)){
                return;
            }else{
                for (Node nodo: this.gridTablero.getChildren()) {
                    if(GridPane.getRowIndex(nodo)==casillaPresionada.getPosicionX() && GridPane.getColumnIndex(nodo)==casillaPresionada.getPosicionY() && (!casillaPresionada.isSeleccionada() || casillaPresionada==casillaOriginal)){
                        if(casillaPresionada!=casillaOriginal){
                            tablero.setNumCasillasSeleccionadas(tablero.getNumCasillasSeleccionadas()+1);
                        }
                        nodo.setStyle("-fx-background-color:#fccb7c;");
                        tablero.getCasillas().get(casillaPresionada.getPosicionX()).get(casillaPresionada.getPosicionY()).setSeleccionada(true);
                        abrirCasillasVaciasDiagonalInferiorDer(tablero,tablero.getCasillas().get(casillaPresionada.getPosicionX()+1).get(casillaPresionada.getPosicionY()+1),casillaOriginal);
                    }
                }
            }
        }catch (Exception e){}
    }

}
