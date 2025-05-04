import java.util.List;
import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private List<Objeto> bolsa;
    private Javaling[] equipo = new Javaling[6];
    private Piso pisoActual;


    public Jugador(String nombre, List<Objeto> bolsa, Javaling[] equipo, Piso pisoActual) {
        this.nombre = nombre;
        this.bolsa = bolsa;
        this.equipo = equipo;
        this.pisoActual = pisoActual;
    }
    public void agregarObjeto(Objeto item) {
        if (this.bolsa == null) {
            this.bolsa = new ArrayList<>();
        }
        this.bolsa.add(item);
    }
    public void eliminarObjeto(Objeto item) {
        if (this.bolsa != null) {
            this.bolsa.remove(item);
        }
    }
    public void usarObjeto(Objeto objeto, Javaling... javaling) {
        /**
         * @param Usa un objeto de la bolsa. Si el objeto es curativo, se aplica a todos los
         * @param Javalings en el equipo. Si el objeto no es curativo, se aplica al primer
         */
        if (bolsa.contains(objeto)) {
            for (Javaling j : javaling) {
                objeto.usar(j);
            }
            objeto.setCantidad(objeto.getCantidad() - 1);
            if (objeto.getCantidad() <= 0) {
                bolsa.remove(objeto);
            }
        } else {
            System.out.println("No tienes ese objeto en tu bolsa");
        }
    }
    public void mostrarBolsa() {
        if (bolsa != null && !bolsa.isEmpty()) {
            System.out.println("Bolsa de " + nombre);
            for (Objeto objeto : bolsa) {
                System.out.println(objeto.getNombre() + " x" + objeto.getCantidad());
            }
        } else {
            System.out.println("La bolsa está vacía.");
        }
    }
    // public String getNombre() {
    //     return nombre;
    // }

    // public Javaling getJavaling() {
    //     return Javaling;
    // }

    // public void usarObjeto(Objeto item){
        
    // }
}