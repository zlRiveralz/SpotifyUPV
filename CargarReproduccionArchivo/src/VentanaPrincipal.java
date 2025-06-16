import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VentanaPrincipal extends JFrame {
    private DefaultListModel<Cancion> modeloLista;
    private JList<Cancion> listaCanciones;
    private ReproductorMP3 reproductor;
    private ListaReproduccion listaReproduccion;

    public VentanaPrincipal() {
        setTitle("Lista de Reproducción");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listaReproduccion = new ListaReproduccion();
        reproductor = new ReproductorMP3();
        modeloLista = new DefaultListModel<>();
        listaCanciones = new JList<>(modeloLista);

        JScrollPane scrollPane = new JScrollPane(listaCanciones);

        JButton btnCargar = new JButton("Cargar Canciones");
        JButton btnReproducir = new JButton("Reproducir");
        JButton btnDetener = new JButton("Detener");

        btnCargar.addActionListener(e -> cargarCanciones());
        btnReproducir.addActionListener(e -> reproducirSeleccionada());
        btnDetener.addActionListener(e -> reproductor.detener());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCargar);
        panelBotones.add(btnReproducir);
        panelBotones.add(btnDetener);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarCanciones() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File carpeta = chooser.getSelectedFile();
            File[] archivos = carpeta.listFiles();

            if (archivos != null) {
                modeloLista.clear();
                listaReproduccion.limpiar();

                for (File archivo : archivos) {
                    if (archivo.getName().toLowerCase().endsWith(".mp3")) {
                        Cancion c = new Cancion(archivo.getName(), archivo.getAbsolutePath());
                        listaReproduccion.agregarCancion(c);
                        modeloLista.addElement(c);
                    }
                }

                JOptionPane.showMessageDialog(this, "Canciones cargadas: " + listaReproduccion.tamaño());
            }
        }
    }

    private void reproducirSeleccionada() {
        Cancion seleccionada = listaCanciones.getSelectedValue();
        if (seleccionada != null) {
            reproductor.reproducir(seleccionada.getRuta());
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una canción de la lista.");
        }
    }
}
