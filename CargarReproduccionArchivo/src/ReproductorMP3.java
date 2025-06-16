import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class ReproductorMP3 {
    private Player player;
    private Thread thread;

    public void reproducir(String ruta) {
        detener(); // Por si ya hay una canción sonando

        thread = new Thread(() -> {
            try {
                FileInputStream fis = new FileInputStream(ruta);
                player = new Player(fis);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void detener() {
        if (player != null) {
            player.close();
        }
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
    }
}
