import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DataManager {
    private static Movimiento[] movimientos;
    private static List<Objeto> items;
    private static List<Javaling> listaJavalings;
    private static List<Javaling> aguaJavalings;
    private static List<Javaling> fuegoJavalings;
    private static List<Javaling> plantaJavalings;
    private static List<Javaling> dragonJavalings;
    private static int sizeDragonJavalings;
    private static int sizeAguaJavalings;
    private static int sizeFuegoJavalings;
    private static int sizePlantaJavalings;
    private static int sizeListaJavalings;
    private static int sizeItems;
    private static int sizeMovimientos;
    private static Random random = new Random();

    public DataManager() {
        if (movimientos == null) {
            cargarDatos();
        }
        random = new Random();
    }
    private static void cargarDatos() {
        movimientos = cargarMovimientos();
        items = cargarItems();
        cargarJavalings();
        sizeDragonJavalings = dragonJavalings.size();
        sizeAguaJavalings = aguaJavalings.size();
        sizeFuegoJavalings = fuegoJavalings.size();
        sizePlantaJavalings = plantaJavalings.size();
        sizeListaJavalings = listaJavalings.size();
        sizeItems = items.size();
        sizeMovimientos = movimientos.length;
    }
    public static int getSizeJavalingsTipo(Tipo tipo) {
        /**
         * Devuelve el tamaño de la lista de javalings de un tipo específico.
         * Si no se encuentra el tipo, devuelve el tamaño de la lista de javalings.
         */
        if (tipo == Tipo.AGUA) {
            return sizeAguaJavalings;
        } else if (tipo == Tipo.FUEGO) {
            return sizeFuegoJavalings;
        } else if (tipo == Tipo.PLANTA) {
            return sizePlantaJavalings;
        } else if (tipo == Tipo.DRAGON) {
            return sizeDragonJavalings;
        }
        return sizeListaJavalings;
    
    }
    public static int getSizeItems() {
        return sizeItems;
    }
    public static int getSizeMovimientos() {
        return sizeMovimientos;
    }
    private static Movimiento[] cargarMovimientos() {
        List<Movimiento> movimientosList = new ArrayList<>(); // Cambiar a List<Movimiento>
        try (FileReader reader = new FileReader("data/movimientos.json")) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray jsonArray = new JSONArray(tokener);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Movimiento movimiento = new Movimiento(
                        jsonObject.getString("nombre"),
                        jsonObject.getInt("potencia"),
                        jsonObject.getInt("precision"),
                        Tipo.valueOf(jsonObject.getString("tipo")),
                        jsonObject.getBoolean("esEstado")
                );
                movimientosList.add(movimiento); // Usar add() en la lista
            }
        } catch (IOException e) {
            System.err.println("Error al cargar movimientos: " + e.getMessage());
        }
        return movimientosList.toArray(new Movimiento[0]); // Convertir la lista a un arreglo
    }
    private static List<Objeto> cargarItems() {
        List<Objeto> items = new ArrayList<>();
        try (FileReader reader = new FileReader("data/items.json")) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray jsonArray = new JSONArray(tokener);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Objeto item = new Objeto(
                        jsonObject.getString("nombre"),
                        jsonObject.getString("descripcion"),
                        jsonObject.getBoolean("esCurativo"),
                        jsonObject.getInt("cantidad")
                );
                items.add(item);
            }
        }catch (IOException e) {
        System.err.println("Error al cargar items: " + e.getMessage());
        }
        return items;
    }
    public static void cargarJavalings() {
        listaJavalings = new ArrayList<>();
        aguaJavalings = new ArrayList<>();
        fuegoJavalings = new ArrayList<>();
        plantaJavalings = new ArrayList<>();
        dragonJavalings = new ArrayList<>();
        try (FileReader reader = new FileReader("data/javalings.json")) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray jsonArray = new JSONArray(tokener);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String tipo = jsonObject.getString("tipo");

                Javaling javaling = null; // Declarar la variable fuera del switch

                switch (tipo) {
                    case "AGUA":
                        javaling = new Agua(
                            jsonObject.getString("nombre"),
                            55, // hpBase
                            0, // velocidad
                            0, // hpTotal
                            0, // hpActual
                            0, // nivel
                            Tipo.AGUA, // tipo
                            new Movimiento[4] // movimiento vacío
                        );
                        aguaJavalings.add(javaling);
                        break;
                    case "FUEGO":
                        javaling = new Fuego(
                            jsonObject.getString("nombre"),
                            60, // hpBase
                            0, // velocidad
                            0, // hpTotal
                            0, // hpActual
                            0, // nivel
                            Tipo.FUEGO, // tipo
                            new Movimiento[4] // movimiento vacío
                        );
                        fuegoJavalings.add(javaling);
                        break;
                    case "PLANTA":
                        javaling = new Planta(
                            jsonObject.getString("nombre"),
                            65, // hpBase
                            0, // velocidad
                            0, // hpTotal
                            0, // hpActual
                            0, // nivel
                            Tipo.PLANTA, // tipo
                            new Movimiento[4] // movimiento vacío
                        );
                        plantaJavalings.add(javaling);
                        break;
                    case "DRAGON":
                        javaling = new Dragon(
                            jsonObject.getString("nombre"),
                            70, // hpBase
                            0, // velocidad
                            0, // hpTotal
                            0, // hpActual
                            0, // nivel
                            Tipo.DRAGON, // tipo
                            new Movimiento[4] // movimiento vacío
                        );
                        dragonJavalings.add(javaling);
                        break;
                }

                if (javaling != null) {
                    listaJavalings.add(javaling); // Añadir a la lista principal
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar javalings: " + e.getMessage());
        }
    }
    public Movimiento[] getListaMovimientos() {
        return movimientos;
    }
    public List<Objeto> getListaItems() {
        return items;
    }
    public List<Javaling> getListaJavalings() {
        return listaJavalings;
    }
    public List<Javaling> getListaJavalingsTipo(Tipo tipo) {
        /**
         * Devuelve una lista de javalings de un tipo específico.
         * Si no se encuentra el tipo, devuelve lista de javalings.
         */
        if (tipo == Tipo.AGUA) {
            return aguaJavalings;
        } else if (tipo == Tipo.FUEGO) {
            return fuegoJavalings;
        } else if (tipo == Tipo.PLANTA) {
            return plantaJavalings;
        } else if (tipo == Tipo.DRAGON) {
            return dragonJavalings;
        }
        return listaJavalings;
    }
    public Objeto getItemAleatorio() {
        int randomIndex = (int) (Math.random() * items.size());
        Objeto item = items.get(randomIndex);
        return item;
    }
    //
        // public Movimiento getMovimientoAleatorio() {
        //     int randomIndex = (int) (Math.random() * movimientos.length);
        //     Movimiento movimiento = movimientos[randomIndex];
        //     return movimiento;
        // }
        // public Movimiento getMovimientoAleatorioTipo(Tipo tipo) {
        //     Movimiento[] movimientosDelTipo = movimientos.stream()
        //         .filter(movimiento -> movimiento.getTipo() == tipo)
        //         .collect(Collectors.toList());
        //     int randomIndex = (int) (Math.random() * movimientosDelTipo.length);
        //     return movimientosDelTipo[randomIndex];
        // }
        // public Movimiento getMovimientoAleatorioTipoEstado(Tipo tipo) {
        //     Movimiento[] movimientosEstado = movimientos.stream()
        //         .filter(movimiento -> movimiento.getTipo() == tipo && movimiento.esEstado())
        //         .collect(Collectors.toList());
        //     int randomIndex = (int) (Math.random() * movimientosEstado.length);
        //     return movimientosEstado[randomIndex];
        // }
        // public Movimiento getMovimientoAleatorioTipoNoEstado(Tipo tipo) {
        //     Movimiento[] movimientosNoEstado = movimientos.stream()
        //         .filter(movimiento -> movimiento.getTipo() == tipo && !movimiento.esEstado())
        //         .collect(Collectors.toList());
        //     int randomIndex = (int) (Math.random() * movimientosNoEstado.length);
        //     return movimientosNoEstado[randomIndex];
        // }
    public Movimiento getMovimientoAleatorio() {
    int randomIndex = (int) (Math.random() * sizeMovimientos);
    return movimientos[randomIndex];
}
    public Movimiento getMovimientoAleatorioTipo(Tipo tipo) {
        Movimiento[] movimientosDelTipo = Arrays.stream(movimientos)
                .filter(movimiento -> movimiento.getTipo() == tipo)
                .toArray(Movimiento[]::new);
        if (movimientosDelTipo.length == 0) {
            return null; // o lanza una excepción si no hay movimientos del tipo
        }
        int randomIndex = (int) (Math.random() * movimientosDelTipo.length);
        if (randomIndex >= movimientosDelTipo.length) {
            randomIndex = movimientosDelTipo.length - 1; // Asegurarse de que el índice esté dentro del rango
        }
        return movimientosDelTipo[randomIndex];
    }
    public Movimiento getMovimientoAleatorioTipoEstado(Tipo tipo) {
        Movimiento[] movimientosEstado = Arrays.stream(movimientos)
                .filter(movimiento -> movimiento.getTipo() == tipo && movimiento.esEstado())
                .toArray(Movimiento[]::new);
        if (movimientosEstado.length == 0) {
            return null; // o lanza una excepción si no hay movimientos del tipo y estado
        }
        int randomIndex = (int) (Math.random() * movimientosEstado.length);
        return movimientosEstado[randomIndex];
    }
    public Movimiento getMovimientoAleatorioTipoNoEstado(Tipo tipo) {
        Movimiento[] movimientosNoEstado = Arrays.stream(movimientos)
                .filter(movimiento -> movimiento.getTipo() == tipo && !movimiento.esEstado())
                .toArray(Movimiento[]::new);
        if (movimientosNoEstado.length == 0) {
            System.out.println("No hay movimientos del tipo " + tipo + " y no estado.");
            return null; // o lanza una excepción si no hay movimientos del tipo y no estado
        }
        int randomIndex = (int) (Math.random() * movimientosNoEstado.length);
        return movimientosNoEstado[randomIndex];
    }
    public Javaling getJavalingAleatorio(int nivel) {
        Tipo tipoAleatorio = Tipo.values()[(int) (Math.random() * 4)];
        return getJavalingAleatorioTipo(nivel, tipoAleatorio);
    }
    public Javaling getJavalingAleatorioSalvaje(int nivel){
        /**
         * Devuelve un javaling aleatorio con una probabilidad de 0.97 de que sea
         * un tipo diferente a DRAGON y una probabilidad de 0.03 de que sea un
         * tipo DRAGON.
         */
        double probabilidad = random.nextDouble();
        List<Tipo> tipos = new ArrayList<>();
        Tipo tipoAleatorio;
        for (Tipo t : Tipo.values()) {
            tipos.add(t);
        }tipos.remove(Tipo.DRAGON);
        if (probabilidad < 0.97) {
            tipoAleatorio = tipos.get(random.nextInt(tipos.size()));
        } else {
            tipoAleatorio = Tipo.DRAGON;
        }
        return getJavalingAleatorioTipo(nivel, tipoAleatorio);
    }
    public Javaling getJavalingAleatorioEntrenador(int nivel){
        /**
         * Devuelve un javaling aleatorio con una probabilidad de 0.95 de que sea
         * un tipo diferente a DRAGON y una probabilidad de 0.05 de que sea un
         * tipo DRAGON.
         */
        double probabilidad = random.nextDouble();
        List<Tipo> tipos = new ArrayList<>();
        Tipo tipoAleatorio;
        for (Tipo t : Tipo.values()) {
            tipos.add(t);
        }tipos.remove(Tipo.DRAGON);
        if (probabilidad < 0.95) {
            tipoAleatorio = tipos.get(random.nextInt(tipos.size()));
        } else {
            tipoAleatorio = Tipo.DRAGON;
        }
        return getJavalingAleatorioTipo(nivel, tipoAleatorio);
    }
    public Javaling getJavalingAleatorioTipo(int nivel,Tipo tipo) {
        /**
         * CREA OBJETO (new)
         * Método principal para crear un Iniciar un javaling.
         */
        int randomIndex;
        Random random = new Random();
        Javaling javaling = null;
        if(tipo == Tipo.AGUA){
            randomIndex = (int) (Math.random() * getSizeJavalingsTipo(tipo));
            javaling = new Agua(getListaJavalingsTipo(tipo).get(randomIndex));
        } 
        else if (tipo == Tipo.FUEGO) {
            randomIndex = (int) (Math.random() * getSizeJavalingsTipo(tipo));
            javaling = new Fuego(getListaJavalingsTipo(tipo).get(randomIndex)); // Inicializar con Fuego
        }
        else if (tipo == Tipo.PLANTA) {
            randomIndex = (int) (Math.random() * getSizeJavalingsTipo(tipo));
            javaling = new Planta(getListaJavalingsTipo(tipo).get(randomIndex)); // Inicializar con Planta
        }
        else if (tipo == Tipo.DRAGON) {
            randomIndex = (int) (Math.random() * getSizeJavalingsTipo(tipo));
            javaling = new Dragon(getListaJavalingsTipo(tipo).get(randomIndex)); // Inicializar con Dragon
        }
        // Asignar nivel y stats
        javaling.setNivel(nivel);
        javaling.setHpTotalNivel(nivel);
        javaling.setHpActual(javaling.getHpTotal());
        javaling.setVelocidad(random.nextInt(401));
        // Asignar movimientos

        for (int i = 0; i < 4; i++) {
            javaling.getMovimiento()[i] = null;
        }
        while(javaling.getMovimiento()[0] == null || javaling.getMovimiento()[1] == null){
            Movimiento movimiento = getMovimientoAleatorioTipoNoEstado(tipo);
            if (movimiento != null && !javaling.tieneMovimiento(movimiento)) {
                javaling.setMovimiento(null, movimiento);
            }
        }
        while (javaling.getMovimiento()[2] == null){
            Movimiento movimiento2 = getMovimientoAleatorioTipoNoEstado(tipo);
            if (movimiento2 != null && !javaling.tieneMovimiento(movimiento2)) {
                javaling.setMovimiento(null, movimiento2);
            }
        }
    
        return javaling;
    } 
    public void imprimirListas() {
        System.out.println("Movimientos:");
        for (Movimiento movimiento : movimientos) {
            System.out.println(movimiento);
        }
    
        System.out.println("\nItems:");
        for (Objeto item : items) {
            System.out.println(item);
        }
    
        System.out.println("\nLista de Javalings:");
        for (Javaling javaling : listaJavalings) {
            System.out.println(javaling);
        }
    
        System.out.println("\nJavalings de Agua:");
        for (Javaling javaling : aguaJavalings) {
            System.out.println(javaling);
        }
    
        System.out.println("\nJavalings de Fuego:");
        for (Javaling javaling : fuegoJavalings) {
            System.out.println(javaling);
        }
    
        System.out.println("\nJavalings de Planta:");
        for (Javaling javaling : plantaJavalings) {
            System.out.println(javaling);
        }
    
        System.out.println("\nJavalings de Dragon:");
        for (Javaling javaling : dragonJavalings) {
            System.out.println(javaling);
        }
    }
    public void imprimirListasMovimientoTipo(Tipo tipo) {
        Movimiento[] movimientosDelTipo = Arrays.stream(movimientos)
                .filter(movimiento -> movimiento.getTipo() == tipo)
                .toArray(Movimiento[]::new);
    
        System.out.println("\nMovimientos del tipo " + tipo + ":");
        for (Movimiento movimiento : movimientosDelTipo) {
            System.out.println(movimiento);
        }
    }
}