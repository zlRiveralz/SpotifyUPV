import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Path rutaMusica = Paths.get("C:/Musica"); // Cambia la ruta a la que quieres monitorear

        List<Cancion> listaCanciones = new ArrayList<>();

        MonitorCarpeta monitor = new MonitorCarpeta(rutaMusica, (cancion) -> {
            listaCanciones.add(cancion);
            System.out.println("Nueva canción detectada y agregada a la lista: " + cancion);
        });

        Thread hiloMonitor = new Thread(monitor);
        hiloMonitor.start();

        // Aquí podrías agregar más lógica para manejar la lista, interfaz, etc.
    }
}
