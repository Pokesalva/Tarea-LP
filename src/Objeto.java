public class Objeto {
    private String nombre;
    private String descripcion;
    private boolean esCurativo;
    private int cantidad;

    public Objeto(String nombre, String descripcion, boolean esCurativo, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esCurativo = esCurativo;
        this.cantidad = cantidad;
    }
    @Override
    public String toString() {
        return "Objeto [nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }

    public void usar(Javaling javaling) {
        if (this.nombre.equals("Poción") ){
            int saludRecuperada = (int) (javaling.getHpTotal() * 0.2);
            javaling.recuperarSalud(saludRecuperada);
        } else if (this.nombre.equals("Superpoción")) {
            int saludRecuperada = (int) (javaling.getHpTotal() * 0.5);
            javaling.recuperarSalud(saludRecuperada);
        } else if (this.nombre.equals("Poción máxima")) {
            javaling.recuperarSalud(javaling.getHpTotal());
        } else if (this.nombre.equals("Revivir máximo")) {
            if (javaling.getHpActual() == 0) {
                javaling.recuperarSalud(javaling.getHpTotal());
            }
        } else if (this.nombre.equals("Caramelo pequeño")) {
            javaling.subirNivel();
        } else if (this.nombre.equals("Caramelo grande")) {
            javaling.subirNivel();
            javaling.subirNivel();
        }
        this.cantidad--;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public boolean esCurativo() {
        return esCurativo;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setEsCurativo(boolean esCurativo) {
        this.esCurativo = esCurativo;
    }
}

// public class Pocion extends Objeto {
//     public Pocion(int cantidad) {
//         super("Poción", "Cura el 20% de la salud de un Javaling", true, cantidad);
//     }

//     @Override
//     public void usar(Javaling javaling) {
//         int saludRecuperada = (int) (javaling.getHpTotal() * 0.2);
//         javaling.recuperarSalud(saludRecuperada);
//         this.cantidad--;
//     }
// }

// public class SuperPocion extends Objeto {
//     public SuperPocion(int cantidad) {
//         super("Superpoción", "Cura el 50% de la salud de un Javaling", true, cantidad);
//     }

//     @Override
//     public void usar(Javaling javaling) {
//         int saludRecuperada = (int) (javaling.getHpTotal() * 0.5);
//         javaling.recuperarSalud(saludRecuperada);
//         this.cantidad--;
//     }
// }

// public class PocionMaxima extends Objeto {
//     public PocionMaxima(int cantidad) {
//         super("Poción máxima", "Cura el 100% de la salud de un Javaling", true, cantidad);
//     }

//     @Override
//     public void usar(Javaling javaling) {
//         javaling.recuperarSalud(javaling.getHpTotal());
//         this.cantidad--;
//     }
// }

// public class RevivirMaximo extends Objeto {
//     public RevivirMaximo(int cantidad) {
//         super("Revivir máximo", "Devuelve un Javaling abatido a su 100% de salud", true, cantidad);
//     }

//     @Override
//     public void usar(Javaling javaling) {
//         if (javaling.getHpActual() == 0) {
//             javaling.recuperarSalud(javaling.getHpTotal());
//         }
//         this.cantidad--;
//     }
// }

// public class CarameloPequeno extends Objeto {
//     public CarameloPequeno(int cantidad) {
//         super("Caramelo pequeño", "Otorga 1 nivel adicional a cada Javaling", false, cantidad);
//     }

//     @Override
//     public void usar(Javaling javaling) {
//         javaling.subirNivel();
//         this.cantidad--;
//     }
// }

// public class CarameloGrande extends Objeto {
//     public CarameloGrande(int cantidad) {
//         super("Caramelo grande", "Otorga 2 niveles adicionales a cada Javaling", false, cantidad);
//     }

//     @Override
//     public void usar(Javaling javaling) {
//         javaling.subirNivel();
//         javaling.subirNivel();
//         this.cantidad--;
//     }
// }


// USO DE EJEMPLO
// Pocion pocion = new Pocion(5);
// Javaling javaling = new Javaling();
// pocion.usar(javaling);