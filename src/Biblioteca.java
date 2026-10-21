import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LSTI2001 — Evidencia final
 * Administra una colección de libros almacenada en un arreglo.
 */
public class Biblioteca {

    private static final Logger LOG = LogManager.getLogger(Biblioteca.class);

    // ----------------------------- Atributos -----------------------------

    /** Arreglo donde se guardan los libros de la biblioteca. */
    private Libro[] libros;

    /** Cantidad de libros registrados hasta el momento. */
    private int cantidad;

    // ---------------------------- Constructor ----------------------------

    /**
     * @param capacidad número máximo de libros que puede guardar la biblioteca
     */
    public Biblioteca(int capacidad) {
        this.libros   = new Libro[capacidad];
        this.cantidad = 0;
        LOG.info("Biblioteca creada con capacidad para {} libros", capacidad);
    }

    // ------------------------------ Métodos ------------------------------

    /**
     * Agrega un libro a la biblioteca.
     *
     * @param libro libro que se desea registrar
     * @return true si se agregó; false si el arreglo ya está lleno
     */
    public boolean agregarLibro(Libro libro) {
        if (cantidad >= libros.length) {
            LOG.warn("No se pudo agregar \"{}\": la biblioteca está llena", libro.getTitulo());
            System.out.println("La biblioteca está llena. No se pudo agregar: " + libro.getTitulo());
            return false;
        }

        libros[cantidad] = libro;
        cantidad++;

        LOG.info("Libro agregado: \"{}\" de {} ({})",
                 libro.getTitulo(), libro.getAutor(), libro.getAnioPublicacion());
        return true;
    }

    /** Muestra en pantalla todos los libros disponibles en la biblioteca. */
    public void mostrarLibros() {
        LOG.info("Mostrando el acervo: {} libro(s)", cantidad);

        System.out.println();
        System.out.println("===================== ACERVO DE LA BIBLIOTECA =====================");

        if (cantidad == 0) {
            System.out.println("Todavía no hay libros registrados.");
            System.out.println("===================================================================");
            return;
        }

        System.out.printf("%-3s  %-38s  %-26s  %s%n", "#", "TÍTULO", "AUTOR", "AÑO");
        System.out.println("-------------------------------------------------------------------");

        for (int i = 0; i < cantidad; i++) {
            System.out.printf("%-3d  %s%n", (i + 1), libros[i]);
        }

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Total de libros: " + cantidad + " de " + libros.length);
        System.out.println("===================================================================");
    }

    /** @return cuántos libros hay registrados */
    public int getCantidad() {
        return cantidad;
    }
}
