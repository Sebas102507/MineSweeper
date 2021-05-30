package modelo;
import java.time.Duration;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.*;
public class Temporizador {
    private LocalTime horaComienzo;
    private LocalTime horaFinalizado;

    public Temporizador(LocalTime horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public LocalTime getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(LocalTime horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public LocalTime getHoraFinalizado() {
        return horaFinalizado;
    }

    public void setHoraFinalizado(LocalTime horaFinalizado) {
        this.horaFinalizado = horaFinalizado;
    }


    public LocalTime getTiempoEmpleado(){
        long difMili=MILLIS.between(this.horaComienzo,this.horaFinalizado);
        //System.out.println("LOS NANON SON: "+difMili);
        long dffSegundos = (difMili / 1000) % 60;
        long dffMinutos = (difMili / (1000 * 60))% 60;
        long dffSHoras = (difMili / (1000 * 60 * 60)) % 24;
        return LocalTime.of((int) dffSHoras,(int)dffMinutos,(int)dffSegundos);
    }

    @Override
    public String toString() {
        LocalTime tEmpleado= this.getTiempoEmpleado();
        return "HH: "+tEmpleado.getHour()+" MM: "+tEmpleado.getMinute()+" SS: "+tEmpleado.getSecond();
    }
}
