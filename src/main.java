import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    //     Scanner scanner = new Scanner(System.in);

        DataManager dataManager = new DataManager();
        // dataManager.imprimirListas();
        Jugador yo = new Jugador("Salva");
        yo.mostrarEquipo();
        
    //     while (yo.getPisoActual().getPiso() < 5) {
    //         System.out.println("Te encuentras en el piso " + yo.getPisoActual().getPiso());
    //         System.out.println("¿Qué quieres hacer?");
    //         System.out.println("(1) Batalla (2) Captura (3) Item Random");
    //         int decision = leerDecision(scanner);
    //         yo.getPisoActual().setDecision(decision);
    //         yo.getPisoActual().ejecutarDecision(yo);
    //         yo.mostrarEquipo();
    //         for (int i = 0; i < yo.getEquipo().length; i++) {
    //             if (yo.getEquipo()[i] != null) {
    //                 yo.getEquipo()[i].printJavaling();
    //             }
    //         }
    //     }
    //     scanner.close();
    // }


    // public static int leerDecision(Scanner scanner) {
    //     while (true) {
    //         String input = pedirLinea(scanner);
    //         try {
    //             int decision = Integer.parseInt(input.trim());
    //             if (decision >= 1 && decision <= 3) {
    //                 return decision;
    //             } else {
    //                 System.out.println("Por favor, selecciona una opción válida (1, 2 o 3).");
    //             }
    //         } catch (NumberFormatException e) {
    //             System.out.println("Entrada inválida. Por favor, ingresa un número.");
    //         }
    //     }
    // }

    // public static String pedirLinea(Scanner scanner) {
    //     try {
    //         if (scanner.hasNextLine()) {
    //             return scanner.nextLine();
    //         } else {
    //             System.out.println("No se detectó entrada. Finalizando programa.");
    //             System.exit(0);
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Error leyendo entrada. Finalizando programa.");
    //         System.exit(0);
    //     }
    //     return ""; // Nunca llega aquí, pero necesario para compilar
    Scanner scanner = new Scanner(System.in);
    int numIteraciones = 5; // Número de iteraciones que deseas
    int[] inputs = new int[numIteraciones];

    for (int i = 0; i < numIteraciones; i++) {
        System.out.print("Ingrese el valor para la iteración " + (i + 1) + ": ");
        inputs[i] = scanner.nextInt();
    }
    // Ahora 'inputs' contiene los valores ingresados por el usuario
    System.out.println("Valores ingresados:");
    for (int i = 0; i < numIteraciones; i++) {
        System.out.println("Iteración " + (i + 1) + ": " + inputs[i]);
    }
    scanner.close();
    }   
}
