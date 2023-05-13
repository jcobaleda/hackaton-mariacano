/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.rescue.model;

/**
 *
 * @author Daniel Velmonto
 */
public class ModuloPerfil {
    private int id;
    private Modulos modulo;
    private Perfiles perfil;

    public ModuloPerfil() {
    }

    public ModuloPerfil(int id, Modulos modulo, Perfiles perfil) {
        this.id = id;
        this.modulo = modulo;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Modulos getModulo() {
        return modulo;
    }

    public void setModulo(Modulos modulo) {
        this.modulo = modulo;
    }

    public Perfiles getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfiles perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "ModuloPerfil{" + "id=" + id + ", modulo=" + modulo + ", perfil=" + perfil + '}';
    }

}
