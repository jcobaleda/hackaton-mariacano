/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.rescue.model;

/**
 *
 * @author Daniel Velmonto
 */
public class Perfiles {
    
    private int id;
    private String nombrePerfil;
    private String descripcion;
    private String estado;

    public Perfiles() {
    }

    public Perfiles(int id, String nombrePerfil, String descripcion, String estado) {
        this.id = id;
        this.nombrePerfil = nombrePerfil;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Perfiles{" + "id=" + id + ", nombrePerfil=" + nombrePerfil + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }
}
