import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;
import org.apache.commons.imaging.Imaging;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

public class VentanaPortada extends JFrame {

    public VentanaPortada(String rutaMp3) {
        super("Portada del álbum");

        try {
            // Leer archivo MP3
            AudioFile audioFile = AudioFileIO.read(new File(rutaMp3));
            Tag tag = audioFile.getTag();

            if (tag != null && tag.getFirstArtwork() != null) {
                Artwork artwork = tag.getFirstArtwork();
                byte[] imagenBytes = artwork.getBinaryData();

                // Usar Apache Commons Imaging para leer la imagen desde bytes
                BufferedImage portada = Imaging.getBufferedImage(new ByteArrayInputStream(imagenBytes));

                // Mostrar la imagen en un JLabel dentro del JFrame
                JLabel label = new JLabel(new ImageIcon(portada));
                add(label);

                setSize(portada.getWidth(), portada.getHeight());
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setVisible(true);

            } else {
                System.out.println("No se encontró portada en el archivo.");
                JOptionPane.showMessageDialog(this, "No se encontró portada en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar la portada:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, pasa la ruta del archivo MP3 como argumento.");
            System.exit(0);
        }
        new VentanaPortada(args[0]);
    }
}
