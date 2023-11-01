package cl.daravena.simple_firebase_crud.model;

public class Alumno {

    private String identificador;
    private String nombre;
    private String descripcion;

    public Alumno() {
    }

    public Alumno(String identificador, String nombre, String descripcion) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
