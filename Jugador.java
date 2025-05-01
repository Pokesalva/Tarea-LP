import java.util.List;
import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private List<Objeto> bolsa;
    private Javaling[] equipo = new Javaling[6];
    private Piso pisoActual;


    public Jugador(String nombre, List Objeto, List Javaling, Piso pisoActual) {
        this.nombre = nombre;
        this.Objeto = Objeto;
        this.Javaling = Javaling;
        this.pisoActual = pisoActual;
    }

    public String getNombre() {
        return nombre;
    }

    public Javaling getJavaling() {
        return Javaling;
    }

    public usarObjeto(Objeto item){
        
    }
}