import java.util.ArrayList;
import java.util.List;

public class ListaReproduccion {
    private List<Cancion> canciones;

    public ListaReproduccion() {
        canciones = new ArrayList<>();
    }

    public void agregarCancion(Cancion cancion) {
        canciones.add(cancion);
    }

    public void eliminarCancion(Cancion cancion) {
        canciones.remove(cancion);
    }

    public List<Cancion> obtenerCanciones() {
        return canciones;
    }

    public void limpiar() {
        canciones.clear();
    }

    public int tamaño() {
        return canciones.size();
    }

    public Cancion get(int index) {
        return canciones.get(index);
    }
}
