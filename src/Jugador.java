import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {
    private String nombre;
    private List<Objeto> bolsa;
    private Javaling[] equipo = new Javaling[6];
    private Piso pisoActual;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.bolsa = new ArrayList<>();
        this.equipo = new Javaling[6];
        this.pisoActual = new Piso(1);
    }
    public Jugador(String nombre, List<Objeto> bolsa, Javaling[] equipo, Piso pisoActual) {
        this.nombre = nombre;
        this.bolsa = bolsa != null ? bolsa : new ArrayList<>();
        this.equipo = equipo != null ? equipo : new Javaling[6];
        this.pisoActual = pisoActual != null ? pisoActual : new Piso(1);
    }
    public String getNombre() {
        return nombre;
    }
    public List<Objeto> getBolsa() {
        return bolsa;
    }
    public Javaling[] getEquipo() {
        return equipo;
    }
    public Piso getPisoActual() {
        return pisoActual;
    }
    public void agregarObjeto(Objeto item) {
        if (this.bolsa == null) {
            this.bolsa = new ArrayList<>();
        }
        for (int i=0; i< bolsa.size();i++ ){
            if (bolsa.get(i).getNombre() == item.getNombre()){
                bolsa.get(i).aumentarCantidad();
                return;
            }
        }
        item.aumentarCantidad();
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
            System.out.println("    BOLSA DE " + nombre.toUpperCase());
            for (Objeto objeto : bolsa) {
                System.out.println("    > 1. " + objeto.getNombre() + " x" + objeto.getCantidad());
            }
        } else {
            System.out.println("    >>> La bolsa está vacía.");
        }
    }
    public void agregarJavaling(Javaling javaling, Scanner scanner) {
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] == null) {
                equipo[i] = javaling;
                System.out.println(javaling.getNombre() + " agregado al equipo.");
                
                return;
            }
        }
        System.out.println("No puedes agregar más Javalings a tu equipo.");
        System.out.println("Deseas eliminar alguno? (1.Si 2.No\n");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Elige el número del Javaling que deseas eliminar:");
            for (int i = 0; i < equipo.length; i++) {
                if (equipo[i] != null) {
                    System.out.println((i + 1) + ". " + equipo[i].getNombre());
                }
            }
            int index = scanner.nextInt() - 1;
            if (index >= 0 && index < equipo.length && equipo[index] != null) {
                eliminarJavaling(index);
                equipo[index] = javaling;
                System.out.println("Javaling agregado al equipo.");
                return;
            } else {
                System.out.println("Número inválido.");
            }
        }
        
    }
    public void eliminarJavaling(Javaling javaling) {
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] == javaling) {
                equipo[i] = null;
                return;
            }
        }
        System.out.println("No tienes ese Javaling en tu equipo");
    }
    public void eliminarJavaling(int index) {
        if (index >= 0 && index < equipo.length) {
            equipo[index] = null;
        } else {
            System.out.println("Índice inválido.");
        }
    }
    public void mostrarEquipo() {
        System.out.println("Equipo de " + nombre);
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] != null) {
                System.out.println((i + 1) + ". " + equipo[i].getNombre() + "(lvl: " + equipo[i].getNivel()+ ")" + "vida" + equipo[i].getHpActual());
            } else {
                System.out.println((i + 1) + ". Vacío");
            }
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