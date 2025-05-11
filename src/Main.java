import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataManager dataManager = new DataManager();
        dataManager.imprimirListas();
        dataManager.imprimirListasMovimientoTipo(Tipo.AGUA);
        dataManager.imprimirListasMovimientoTipo(Tipo.FUEGO);
        dataManager.imprimirListasMovimientoTipo(Tipo.PLANTA);
        dataManager.imprimirListasMovimientoTipo(Tipo.DRAGON);
        System.out.println("Cuál es tú nombre entrenador: ");
        String nombre = scanner.nextLine();
        Jugador yo = new Jugador(nombre);
        yo.mostrarEquipo();

        int numIteraciones = 5; // Número de iteraciones que deseas
        int i = 0;
        while (numIteraciones > i){
            decidir(yo,scanner);
            i++;
        }


        scanner.close();
    }
        

    public static void decidir(Jugador jugador, Scanner scanner){

        System.out.println("Te encuentras en el piso " + jugador.getPisoActual().getPiso());
        System.out.println("¿Qué quieres hacer?");
        System.out.println("(1) Batalla (2) Captura (3) Item Random");
        int decision = 0;
        while (true) {
            try {
                System.out.print("Ingrese su decisión: ");
                decision = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer después de leer un entero
                if (decision >= 1 && decision <= 3) {
                    break;
                } else {
                    System.out.println("Ingrese un valor válido (1, 2 o 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.nextLine(); // Limpiar el buffer en caso de error
            }
        }

        try {
            jugador.getPisoActual().setDecision(decision);
            jugador.getPisoActual().ejecutarDecision(jugador, scanner); // Asegúrate de que este método no cierre el Scanner
        } catch (NoSuchElementException e) {
            System.out.println("Error: No se pudo leer la entrada. Asegúrate de no cerrar el Scanner dentro de ejecutarDecision.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
        
        jugador.mostrarEquipo();

        
    }

}