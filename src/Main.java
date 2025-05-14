import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataManager dataManager = new DataManager();
        // dataManager.imprimirListas();
        System.out.println("\n \n /**************************************************/\n \n ");
        System.out.println("BIENVENIDO A LA TORRE DEL MAESTRO");
        System.out.println("ingresa tu nombre malito:");
        String nombre = scanner.next();
        Jugador yo = new Jugador(nombre);

        int numIteraciones = 20; // Número de iteraciones que deseas

        //Entrenador entrenador1 = new Entrenador(false, yo.getPisoActual(), dataManager);
        //System.out.println(entrenador1.getEquipo()[0]);

        while (yo.getPisoActual().getPiso() <= numIteraciones) {
            System.out.println("\n¿Qué quieres hacer? PisoActual:" + yo.getPisoActual().getPiso());
            System.out.println("(1) Batalla (2) Captura (3) Item Random (4) ver Equipo");

            int decision = 0;
            while (true) {
                try {
                    System.out.print("> Ingrese su decisión: ");
                    decision = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer después de leer un entero
                    if (decision >= 1 && decision <= 4) {
                        break;
                    } else {
                        System.out.println("Ingrese un valor válido).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                    scanner.nextLine(); // Limpiar el buffer en caso de error
                }
            }

            try {
                yo.getPisoActual().setDecision(decision);
                yo.getPisoActual().ejecutarDecision(yo, scanner); // Asegúrate de que este método no cierre el Scanner
            } catch (NoSuchElementException e) {
                System.out.println("Error: No se pudo leer la entrada. Asegúrate de no cerrar el Scanner dentro de ejecutarDecision.");
                break; // Salir del bucle si ocurre un error crítico
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                break; // Salir del bucle si ocurre un error crítico
            }

            

        }
        scanner.close();
    }
}
