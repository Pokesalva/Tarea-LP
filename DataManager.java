import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DataManager {
    private static List<Movimiento> movimientos;
    private static List<Objeto> items;
    private static List<Javaling> listaJavalings;
    private static List<Javaling> aguaJavalings;
    private static List<Javaling> fuegoJavalings;
    private static List<Javaling> plantaJavalings;
    private static List<Javaling> dragonJavalings;

    private static List<Movimiento> cargarMovimientos() {
        List<Movimiento> movimientos = new ArrayList<>();
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
                movimientos.add(movimiento);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar movimientos: " + e.getMessage());
        }
        return movimientos;
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
                Javaling javaling = null;

                switch (tipo) {
                    case "AGUA":
                        Agua javaling = new Agua(
                            jsonObject.getString("nombre"),
                            0, // hpBase
                            0, // velocidad
                            0, // hpTotal
                            0, // hpActual
                            0, // nivel
                            Tipo.valueOf(jsonObject.getString("tipo")), // tipo
                            new Movimiento[0], // movimiento vacío
                            0, // ataque
                            0, // xp
                            0 // nextXp
                        );
                        aguaJavalings.add((Agua) javaling);
                        break;
                    case "FUEGO":
                        Fuego javaling = new Fuego(
                            jsonObject.getString("nombre"),
                            0, // hpBase
                            0, // velocidad
                            0, // hpTotal
                            0, // hpActual
                            0, // nivel
                            Tipo.valueOf(jsonObject.getString("tipo")), // tipo
                            new Movimiento[0], // movimiento vacío
                            0, // ataque
                            0, // xp
                            0 // nextXp
                        );
                        fuegoJavalings.add((Fuego) javaling);
                        break;
                    case "PLANTA":
                        Planta javaling = new Planta(
                                jsonObject.getString("nombre"),
                            0, // hpBase
                            0, // velocidad
                            0, // hpTotal
                            0, // hpActual
                            0, // nivel
                            Tipo.valueOf(jsonObject.getString("tipo")), // tipo
                            new Movimiento[0], // movimiento vacío
                            0, // ataque
                            0, // xp
                            0 // nextXp
                        );
                        plantaJavalings.add((Planta) javaling);
                        break;
                    case "DRAGON":
                        Dragon javaling = new Dragon(
                            jsonObject.getString("nombre"),
                            0, // hpBase
                            0, // velocidad
                            0, // hpTotal
                            0, // hpActual
                            0, // nivel
                            Tipo.valueOf(jsonObject.getString("tipo")), // tipo
                            new Movimiento[0], // movimiento vacío
                            0, // ataque
                            0, // xp
                            0 // nextXp
                        );
                        dragonJavalings.add((Dragon) javaling);
                        break;
                }

                if (javaling != null) {
                    listaJavalings.add(javaling);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar javalings: " + e.getMessage());
        }
    }


    public static List<Movimiento> getListaMovimientos() {
        return movimientos;
    }
    public static List<Objeto> getListaItems() {
        return items;
    }
    public static List<Javaling> getListaJavalings() {
        return listaJavalings;
    }
    public static List<Javaling> getListaJavalingsTipo(Tipo tipo) {
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
    public Movimiento getMovimientoAleatorio() {
        int randomIndex = (int) (Math.random() * movimientos.size());
        Movimiento movimiento = movimientos.get(randomIndex);
        return movimiento;
    }
    public Movimiento getMovimientoAleatorioTipo(Tipo tipo) {
        List<Movimiento> movimientosDelTipo = movimientos.stream()
            .filter(movimiento -> movimiento.getTipo() == tipo)
            .collect(Collectors.toList());
        int randomIndex = (int) (Math.random() * movimientosDelTipo.size());
        return movimientosDelTipo.get(randomIndex)
    }

    //public Javaling getJavalingAleatorio() {}
    public Javaling getJavalingAleatorioTipo(int nivel,Tipo tipo) {
        if(tipo == Tipo.AGUA){
            randomIndex = (int) (Math.random() * aguaJavalings.size());
            Agua javaling = DataManager.getListaJavalingsTipo(tipo).get(randomIndex);
        }
        else if(tipo == Tipo.FUEGO){
            randomIndex = (int) (Math.random() * fuegoJavalings.size());
            Fuego javaling = DataManager.getListaJavalingsTipo(tipo).get(randomIndex);
        }
        else if(tipo == Tipo.PLANTA){
            randomIndex = (int) (Math.random() * plantaJavalings.size());
            Planta javaling = DataManager.getListaJavalingsTipo(tipo).get(randomIndex);
        }
        else if(tipo == Tipo.DRAGON){
            randomIndex = (int) (Math.random() * dragonJavalings.size());
            Dragon javaling = DataManager.getListaJavalingsTipo(tipo).get(randomIndex);
        }
        // Asignar nivel y stats
        javaling.setNivel(nivel);
        int i = 1;
        while (i <= nivel) {
            javaling.subirNivel();
            i++;
            }
        javaling.setHpActual(javaling.getHpTotal());
        javaling.setVelocidad((int) (Math.random() * 200)+1);
        // Asignar movimientos
        for (int j = 0; j < 4; j++) {
            Movimiento movimiento = DataManager.getMovimientoAleatorioTipo(tipo);
            javaling.setMovimiento(javaling, null, movimiento);
        }
        return javaling;
    }
    
}