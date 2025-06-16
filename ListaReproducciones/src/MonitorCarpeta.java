import java.io.IOException;
import java.nio.file.*;
import java.util.function.Consumer;

public class MonitorCarpeta implements Runnable {
    private Path carpeta;
    private Consumer<Cancion> callback;

    public MonitorCarpeta(Path carpeta, Consumer<Cancion> callback) {
        this.carpeta = carpeta;
        this.callback = callback;
    }

    @Override
    public void run() {
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            carpeta.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            System.out.println("Monitoreando carpeta: " + carpeta.toString());

            while (true) {
                WatchKey key = watchService.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        Path nombreArchivo = (Path) event.context();
                        String archivoStr = nombreArchivo.toString().toLowerCase();

                        if (archivoStr.endsWith(".mp3") || archivoStr.endsWith(".wav") || archivoStr.endsWith(".flac")) {
                            Cancion nuevaCancion = new Cancion(nombreArchivo.toString(), carpeta.resolve(nombreArchivo));
                            callback.accept(nuevaCancion);
                        }
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    System.out.println("La carpeta ya no es accesible, terminando monitoreo.");
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
