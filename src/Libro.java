/**
 * LSTI2001 — Evidencia final
 * Representa un libro del acervo de la biblioteca.
 */
public class Libro {

    // ----------------------------- Atributos -----------------------------

    /** Título del libro. */
    private String titulo;

    /** Autor del libro. */
    private String autor;

    /** Año en que el libro fue publicado. */
    private int anioPublicacion;

    // ---------------------------- Constructor ----------------------------

    public Libro(String titulo, String autor, int anioPublicacion) {
        this.titulo          = titulo;
        this.autor           = autor;
        this.anioPublicacion = anioPublicacion;
    }

    // ------------------------------ Getters ------------------------------

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    /** Representación del libro en una sola línea. */
    @Override
    public String toString() {
        return String.format("%-38s  %-26s  %d", titulo, autor, anioPublicacion);
    }
}
