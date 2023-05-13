/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.rescue.model;

/**
 *
 * @author DanielVelasquezMonto
 */
public class Mascota {
    private int codigo;
    private String nombre;
    private String edad;
    private String raza;
    private String tamaño;
    private String cantidadVida;
    private String tipoAgua;
    private String mascota;
    private String estado;

    public Mascota() {
    }

    public Mascota(int codigo, String nombre, String edad, String raza, String tamaño, String cantidadVida, String tipoAgua, String mascota, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
        this.tamaño = tamaño;
        this.cantidadVida = cantidadVida;
        this.tipoAgua = tipoAgua;
        this.mascota = mascota;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getCantidadVida() {
        return cantidadVida;
    }

    public void setCantidadVida(String cantidadVida) {
        this.cantidadVida = cantidadVida;
    }

    public String getTipoAgua() {
        return tipoAgua;
    }

    public void setTipoAgua(String tipoAgua) {
        this.tipoAgua = tipoAgua;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Mascota{" + "codigo=" + codigo + ", nombre=" + nombre + ", edad=" + edad + ", raza=" + raza + ", tama\u00f1o=" + tamaño + ", cantidadVida=" + cantidadVida + ", tipoAgua=" + tipoAgua + ", mascota=" + mascota + ", estado=" + estado+ '}';
    }
}
