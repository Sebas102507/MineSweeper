package test;


import modelo.Temporizador;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class TemporizadorTest {

    @Test
    public void testGetTiempoEmpleadoHora() {
        Temporizador temp= new Temporizador(LocalTime.of(12,22,10));
        temp.setHoraFinalizado(LocalTime.of(12,25,56));
        LocalTime tiempoEmpleado= temp.getTiempoEmpleado();
        Assert.assertEquals(0,tiempoEmpleado.getHour());
    }

    @Test
    public void testGetTiempoEmpleadoMinutos() {
        Temporizador temp= new Temporizador(LocalTime.of(12,22,10));
        temp.setHoraFinalizado(LocalTime.of(12,25,56));
        LocalTime tiempoEmpleado= temp.getTiempoEmpleado();
        Assert.assertEquals(3,tiempoEmpleado.getMinute());
    }

    @Test
    public void testGetTiempoEmpleadoSegundos() {
        Temporizador temp= new Temporizador(LocalTime.of(12,22,10));
        temp.setHoraFinalizado(LocalTime.of(12,25,56));
        LocalTime tiempoEmpleado= temp.getTiempoEmpleado();
        Assert.assertEquals(46,tiempoEmpleado.getSecond());
    }

}
