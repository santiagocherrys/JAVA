package entity;

public class Libro extends Autor{
    private int id;
    private String titulo;
    private int anio_publicacion;

    private double precio;
    private int id_autor;

    //constructores

    public Libro(){

    }

    public Libro(int id, String nombre, String nacionalidad, int id1, String titulo, int anio_publicacion, double precio, int id_autor) {
        super(id, nombre, nacionalidad);
        this.id = id1;
        this.titulo = titulo;
        this.anio_publicacion = anio_publicacion;
        this.precio = precio;
        this.id_autor = id_autor;
    }
//Setters and Getters

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getIdSuper(){
        return super.getId();
    }

    public void setIdSuper(int id){
        super.setId(id);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio_publicacion() {
        return anio_publicacion;
    }

    public void setAnio_publicacion(int anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }


    //ToString

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anio_publicacion=" + anio_publicacion +
                ", precio=" + precio +
                ", id_autor=" + id_autor +
                '}';
    }

    public String imprimirPorAutor(){
        return "Libro{" +
                ", titulo='" + titulo + '\'' +
                ", anio_publicacion=" + anio_publicacion +
                ", precio=" + precio +
                '}';
    }

    public String imprimirTodo(){
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anio_publicacion=" + anio_publicacion +
                ", precio=" + precio +
                ", id_autor=" + id_autor +
                ", id=" + super.getId() +
                ",nombre=" + super.getNombre() +
                ",nacionalidad =" +super.getNacionalidad()+
                '}';

    }
}
