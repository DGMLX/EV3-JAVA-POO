package Modelo;


public class Pelicula {
    private String titulo,director,categoria,codigo,formato;
    private int duracion;
    private boolean estreno;

    public Pelicula() {
    }

    public Pelicula(String titulo, String director, String categoria, String codigo, String formato, int duracion, boolean estreno) {
        this.titulo = titulo;
        this.director = director;
        this.categoria = categoria;
        this.codigo = codigo;
        this.formato = formato;
        this.duracion = duracion;
        this.estreno = estreno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isEstreno() {
        return estreno;
    }

    public void setEstreno(boolean estreno) {
        this.estreno = estreno;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pelicula{");
        sb.append("titulo=").append(titulo);
        sb.append(", director=").append(director);
        sb.append(", categoria=").append(categoria);
        sb.append(", codigo=").append(codigo);
        sb.append(", formato=").append(formato);
        sb.append(", duracion=").append(duracion);
        sb.append(", estreno=").append(estreno);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
