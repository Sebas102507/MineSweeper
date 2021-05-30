package persistencia;

import modelo.*;

import java.io.*;
import java.util.Scanner;


public class ManejadorDeArchivos {
    private String nombreArchivo;

    public void escribirArchivoCSV(){
        try {
            File file= new File("/Users/juansebastianvargastorres/IdeaProjects/FigurasFx/archivos/"+nombreArchivo);
            boolean existe= file.exists();
            BufferedWriter bw= new BufferedWriter(new FileWriter("/Users/juansebastianvargastorres/IdeaProjects/FigurasFx/archivos/"+nombreArchivo,true));
            bw.close();
        }catch (Exception e){
            return;
        }
    }


    public void leerArchivoCSV(){
        try {
            Scanner sc = new Scanner(new File("/Users/juansebastianvargastorres/IdeaProjects/FigurasFx/archivos/"+nombreArchivo));
            sc.useDelimiter(",");
            while (sc.hasNext())
            {
                System.out.print(sc.next()+" ");
            }
        }catch (Exception e){
            return;
        }

    }


    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}
