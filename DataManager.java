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
    private static List<Javaling> javalings;
    private static List<Javaling> aguaJavalings;
    private static List<Javaling> fuegoJavalings;
    private static List<Javaling> plantaJavalings;
    private static List<Javaling> dragonJavalings;

    // private static List<Movimiento> cargarMovimientos() {
    //     List<Movimiento> movimientos = new ArrayList<>();
    //     try (FileReader reader = new FileReader("data/movimientos.json")) {
    //         JSONTokener tokener = new JSONTokener(reader);
    //         JSONArray jsonArray = new JSONArray(tokener);
    //         for (int i = 0; i < jsonArray.length(); i++) {
    //             JSONObject jsonObject = jsonArray.getJSONObject(i);
    //             Movimiento movimiento = new Movimiento(
    //                     jsonObject.getString("nombre"),
    //                     jsonObject.getInt("potencia"),
    //                     jsonObject.getInt("precision"),
    //                     jsonObject.getString("tipo"),
    //                     jsonObject.getBoolean("esEstado")
    //             );
    //             movimientos.add(movimiento);
    //         }
    //     } catch (IOException e) {
    //         System.err.println("Error al cargar movimientos: " + e.getMessage());
    //     }
    //     return movimientos;
    // }
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
        javalings = new ArrayList<>();
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
                        javaling = new Agua(
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
                        javaling = new Fuego(
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
                        javaling = new Planta(
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
                        javaling = new Dragon(
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
                    javalings.add(javaling);
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
        return javalings;
    }
    public static List<Javaling> getListaAguaJavalings() {
        return aguaJavalings;
    }
    public static List<Javaling> getListaFuegoJavalings() {
        return fuegoJavalings;
    }
    public static List<Javaling> getListaPlantaJavalings() {
        return plantaJavalings;
    }
    public static List<Javaling> getListaDragonJavalings() {
        return dragonJavalings;
    }

}