public class Cancion {
    private String nombre;
    private String ruta;

    public Cancion(String nombre, String ruta) {
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuta() {
        return ruta;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
