import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;

public class LectorPortada {
    public static BufferedImage obtenerPortada(String ruta) {
        try {
            File archivo = new File(ruta);
            AudioFile audioFile = AudioFileIO.read(archivo);
            Tag tag = audioFile.getTag();

            if (tag != null && tag.getFirstArtwork() != null) {
                Artwork portada = tag.getFirstArtwork();
                byte[] datos = portada.getBinaryData();
                return ImageIO.read(new ByteArrayInputStream(datos));
            }
        } catch (Exception e) {
            System.err.println("Error al leer la portada: " + e.getMessage());
        }
        return null;
    }

}
