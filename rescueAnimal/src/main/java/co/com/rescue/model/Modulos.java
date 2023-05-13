/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.rescue.model;

/**
 *
 * @author Daniel Velmonto
 */
public class Modulos {
    private int id;
    private String nombre;
    private String url;
    private String estado;
    private String icono;

    public Modulos() {
    }

    public Modulos(int id, String nombre, String url, String estado, String icono) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.estado = estado;
        this.icono = icono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    @Override
    public String toString() {
        return "Modulos{" + "id=" + id + ", nombre=" + nombre + ", url=" + url + ", estado=" + estado + ", icono=" + icono + '}';
    }
}
