package co.com.rescue.model;

/**
 *
 * @author Daniel Velmonto
 */
public class Empleado {
    
    private int id;
    private String tipoDocumento;
    private String dni;
    private String nombres;
    private String telefono;
    private String estado;
    private String user;
    private String email;
    private String perfil;
    private int idPerfil;
    private String pass;

    public Empleado() {
    }

    public Empleado(int id, String tipoDocumento, String dni, String nombres, String telefono, String estado, String user, String email, String perfil, int idPerfil, String pass) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.dni = dni;
        this.nombres = nombres;
        this.telefono = telefono;
        this.estado = estado;
        this.user = user;
        this.email = email;
        this.perfil = perfil;
        this.idPerfil = idPerfil;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", tipoDocumento=" + tipoDocumento + ", dni=" + dni + ", nombres=" + nombres + ", telefono=" + telefono + ", estado=" + estado + ", user=" + user + ", email=" + email + ", perfil=" + perfil + ", idPerfil=" + idPerfil + ", pass=" + pass + '}';
    }
}
