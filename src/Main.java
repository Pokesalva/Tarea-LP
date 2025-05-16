import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.xml.crypto.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataManager dataManager = new DataManager();
        dataManager.imprimirListas();
        imprimirASCII("welcome","rojo");
        Main.print("ingresa tu nombre malito:  ","aqua claro");
        String nombre = scanner.next();
        Jugador yo = new Jugador(nombre);
        loading("Cargando... expande la ventana de la terminal para ver mejor las imágenes.");
        
        int numIteraciones = 23; // Número de iteraciones que deseas,
        
        //Entrenador entrenador1 = new Entrenador(false, yo.getPisoActual(), dataManager);
        //System.out.println(entrenador1.getEquipo()[0]);
        imprimirASCII("Charizard","");

        elegirInicial(yo,dataManager,scanner);
        imprimirASCII(yo.getEquipo()[0].getNombre(), yo.getEquipo()[0].getColor());
        Main.print("Elegiste a " + yo.getEquipo()[0].getNombre() + " es una pésima elección...\n", "aqua claro");
        Main.print("Quiéres cambiar el nombre? (1) SI   (2) NO:  ", "aqua claro");
        int choice = scanner.nextInt();
        if(choice == 1){
            Main.print("Elige un nombre digno: ", "aqua claro");
            String newname = scanner.next();
            yo.getEquipo()[0].setNombre(newname);
        }
        Main.print("\nTu primer Javaling \n Nombre: " + yo.getEquipo()[0].getNombre() + "\n", "aqua claro");
        Main.print("Tipo: ", "aqua claro");
        Main.print(yo.getEquipo()[0].getTipo() + "\n", yo.getEquipo()[0].getColor());
        Main.print("HpTotal: " + yo.getEquipo()[0].getHpTotal() + "\n", "aqua claro");
        Main.print("Velocidad: " + yo.getEquipo()[0].getVelocidad() + "\n", "aqua claro");
        Main.print(yo.getEquipo()[0].getMovimientosString() + "\n", "aqua claro");

       // hpTotal, velocidad y los nombres de los 4 movimientos del Javaling activo

        while (yo.getPisoActual().getPiso() <= numIteraciones) {
            System.out.println("\n¿Qué quieres hacer? PisoActual:" + yo.getPisoActual().getPiso());
            System.out.println("(1) Batalla (2) Captura (3) Item Random (4) ver Equipo (5) Salir llorando");

            int decision = 0;
            while (true) {
                try {
                    System.out.print("> Ingrese su decisión: ");
                    decision = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer después de leer un entero
                    if (decision >= 1 && decision <= 5) {
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
    private static void elegirInicial(Jugador jugador, DataManager dataManager, Scanner scanner) {
        Javaling java1 = dataManager.getJavalingAleatorioSalvaje(5);
        Javaling java2 = dataManager.getJavalingAleatorioSalvaje(5);
        Javaling java3 = dataManager.getJavalingAleatorioSalvaje(5);
    
        Main.print("Wena compare\n", "aqua claro");
        Main.print("Cómo te va?\n", "aqua claro");
        Main.print("Estos son los Javaling que me quedan, saludos:\n", "aqua claro");
        Main.print("1. " + java1.getNombre() + "\n2. " + java2.getNombre() + "\n3. " + java3.getNombre()+ "\n", "aqua claro");
        Main.print("ELIGE: ",50, "aqua claro",true,"ELIGE\n");
        for (int i = 0; i < 2; i++) {
            try {
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        jugador.agregarJavaling(java1, scanner);
                        return;
                    case 2:
                        jugador.agregarJavaling(java2, scanner);
                        return;
                    case 3:
                        jugador.agregarJavaling(java3, scanner);
                        return;
                    default:
                        Main.print("Anota un número weno o te vai funao\n","aqua claro");
                }
            } catch (InputMismatchException e) {
                Main.print("Anota un número weno o te vai funao\n", "aqua claro");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }
        Main.print("De hecho perdiste por chistosito\n", "rojo");
                Main.imprimirASCII("gameover", "rojo");
                System.exit(0);
    }



    public static void imprimirASCII(String archivo, String color) {
        String codigoColor = obtenerCodigoColor(color);
        String rutaArchivo = "ASCII_art/" + archivo + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.print(codigoColor);
                for (int i = 0; i < linea.length(); i++) {
                    System.out.print(linea.charAt(i));
                    try {
                        Thread.sleep(0, 500000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Interrumpido durante la espera.");
                        return;
                    }
                }
                System.out.println(); // Asegura salto de línea después de cada línea del archivo
            }
            System.out.print("\u001B[0m"); // Restablecer color después de todo
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Mapeo de colores por nombre a códigos ANSI
    private static String obtenerCodigoColor(String color) {
        return switch (color.toLowerCase()) {
            case "rojo" -> "\u001B[31m";
            case "verde" -> "\u001B[32m";
            case "amarillo" -> "\u001B[33m";
            case "azul" -> "\u001B[34m";
            case "morado" -> "\u001B[35m";
            case "cyan" -> "\u001B[36m";
            case "blanco" -> "\u001B[37m";
            case "gris"         -> "\u001B[90m";
            case "azul claro"   -> "\u001B[94m";
            case "verde claro"  -> "\u001B[92m";
            case "aqua claro"   -> "\u001B[96m";
            case "rojo claro"   -> "\u001B[91m";
            case "purpura claro"-> "\u001B[95m";
            case "amarillo claro" -> "\u001B[93m";
            case "blanco brillante" -> "\u001B[97m";
            default -> ""; // Sin color si no se reconoce
        };
    }
    private static String obtenerCodigoColorFondo(String color) {
        return switch (color.toLowerCase()) {
            case "rojo"     -> "\u001B[41m";
            case "verde"    -> "\u001B[42m";
            case "amarillo" -> "\u001B[43m";
            case "azul"     -> "\u001B[44m";
            case "morado"   -> "\u001B[45m";
            case "cyan"     -> "\u001B[46m";
            case "blanco"   -> "\u001B[47m";
            case "negro"    -> "\u001B[40m";
            default         -> ""; // Sin fondo si no se reconoce
        };
    }
    public static void loading(String... string){
        String mensaje1="Cargando";
        String mensaje2="Listo!";
        int i =0;
        for (String param : string){
            if(param !=null ){
                mensaje1=param;
            }
            if (param != null && i==1){
                mensaje2= param;
            }
            i++;
        }

        System.out.print("\u001b[5m\u001B[36m>>>\u001B[0m\u001B[36m"+ mensaje1);
        int milisegundos = 125;
        for (i = 0; i < 15; i++) {
            System.out.print("*");
            try {
                Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Interrumpido durante la espera.\u001B[0m");
            }
        }
        System.out.println("\033[2K\r" + mensaje2 + "\u001B[0m");
    }
    public static void loading(String string, int milisegundos, String color){
        String colorcode = obtenerCodigoColor(color);
        String mensaje1=string;
        System.out.print("\u001b[5m"+colorcode+">>>"+mensaje1+colorcode);
        for (int i = 0; i < 20; i++) {
            System.out.print("*");
            try {
                Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Interrumpido durante la espera.\u001B[0m");
            }
        }
        System.out.print("\u001B[0m");
        System.out.print("\033[2K\r" +colorcode+"Completado..." + "\u001B[0m");
    }


    public static void print(String string, int milisegundos, String color, boolean parpadear, String stringFinal){
        String finale= "Completado...";
        if (stringFinal !=null){
            finale = stringFinal;
        }
        
        if (parpadear){
            String colorcode = obtenerCodigoColor(color);
            String mensaje1=string;
            System.out.print("\u001b[5m"+colorcode+">>>");
            for (int i = 0; i < mensaje1.length(); i++) {
                System.out.print(mensaje1.charAt(i));
                try {
                    Thread.sleep(milisegundos);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Interrumpido durante la espera."+"\u001B[0m");
                    return;
                }
            }
            System.out.print("\u001B[0m");
            System.out.print("\033[2K\r" +colorcode+ finale + "\u001B[0m");

        }
        else{
            String colorcode = obtenerCodigoColor(color);
            String mensaje1=string;
            System.out.print(colorcode);
            for (int i = 0; i < mensaje1.length(); i++) {
                System.out.print(mensaje1.charAt(i));
                try {
                    Thread.sleep(milisegundos);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Interrumpido durante la espera."+"\u001B[0m");
                    return;
                }
            }
            System.out.print("\u001B[0m");
        }
    }
    public static void print(String string,String color){
        String colorcode = obtenerCodigoColor(color);
        String mensaje1=string;
        System.out.print(colorcode);
        for (int i = 0; i < mensaje1.length(); i++) {
            System.out.print(mensaje1.charAt(i));
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Interrumpido durante la espera."+"\u001B[0m");
                return;
            }
        }
        System.out.print("\u001B[0m");
    }
    public static void print(String string,String color, int milisegundos){
        String colorcode = obtenerCodigoColor(color);
        String mensaje1=string;
        System.out.print(colorcode);
        for (int i = 0; i < mensaje1.length(); i++) {
            System.out.print(mensaje1.charAt(i));
            try {
                Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Interrumpido durante la espera."+"\u001B[0m");
                return;
            }
        }
        System.out.print("\u001B[0m");
    }
    public static void print(String string,String color, String colorFondo, int milisegundos){
        String colorcode = obtenerCodigoColor(color);
        String colorcodeBack = obtenerCodigoColorFondo(colorFondo);
        String mensaje1=string;
        System.out.print(colorcode);
        System.out.print(colorcodeBack);
        for (int i = 0; i < mensaje1.length(); i++) {
            System.out.print(mensaje1.charAt(i));
            try {
                Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Interrumpido durante la espera."+"\u001B[0m");
                return;
            }
        }
        System.out.print("\u001B[0m \n");
    }
} 
