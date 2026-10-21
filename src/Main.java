import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LSTI2001 — Evidencia final
 * Punto de entrada: crea la biblioteca, registra cuatro libros y los muestra.
 */
public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Inicia la aplicación Biblioteca");

        System.out.println("=== Administrador de biblioteca ===");

        // Se crea una instancia de la biblioteca
        Biblioteca biblioteca = new Biblioteca(10);

        // Se agregan cuatro libros
        biblioteca.agregarLibro(new Libro("Cien años de soledad",   "Gabriel García Márquez", 1967));
        biblioteca.agregarLibro(new Libro("El llano en llamas",     "Juan Rulfo",             1953));
        biblioteca.agregarLibro(new Libro("La casa de los espíritus","Isabel Allende",        1982));
        biblioteca.agregarLibro(new Libro("Rayuela",                 "Julio Cortázar",        1963));

        // Se muestran todos los libros de la biblioteca
        biblioteca.mostrarLibros();

        LOG.info("Finaliza la aplicación con {} libro(s) registrados", biblioteca.getCantidad());
    }
}
