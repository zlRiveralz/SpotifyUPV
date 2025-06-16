import java.nio.file.Path;

public class Cancion {
    private String nombreArchivo;
    private Path ruta;

    public Cancion(String nombreArchivo, Path ruta) {
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public Path getRuta() {
        return ruta;
    }

    @Override
    public String toString() {
        return nombreArchivo + " (" + ruta.toString() + ")";
    }
}
