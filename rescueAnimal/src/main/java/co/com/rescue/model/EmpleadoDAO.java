/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.rescue.model;


import co.com.rescue.config.AppConstants;
import co.com.rescue.config.CloseConnections;
import co.com.rescue.config.DatabaseConstants;
import co.com.rescue.config.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Daniel Velmonto
 */
public class EmpleadoDAO {
    DatabaseUtil dbu = new DatabaseUtil();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    private static final String ERROR_DB_MESSAGE = "Error en la base de datos: ";
    private static final String CIERRE_CONEXIONES = "Se cierran conexiones de manera exitosa";
    
    /**
     * Metodo encargado de validar en el login
     * @param user
     * @param pass
     * @return 
     */
    public Empleado validar(String user, String pass){
        Empleado em = new Empleado();
        String sql = DatabaseConstants.SQL_VALIDA_EMPLEADO;
        try {
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                em.setId(rs.getInt("idempleado"));
                em.setUser(rs.getString("User"));
                em.setTipoDocumento(rs.getString("Descripcion"));
                em.setDni(rs.getString("Documento"));
                em.setNombres(rs.getString("Nombres"));
                em.setEstado(rs.getString("Nombre"));
                em.setEmail(rs.getString("Email"));
                em.setPerfil(rs.getString("NombrePerfil"));
                em.setIdPerfil(rs.getInt("IdPerfil"));
            }
        } catch (SQLException e) {
            AppConstants.log.log(Level.SEVERE, ERROR_DB_MESSAGE + e.getMessage(), e);
        } finally{
            CloseConnections.closeConnection(conn);
            CloseConnections.closePrepareStatement(ps);
            CloseConnections.closeResultSet(rs);
            AppConstants.log.log(Level.INFO, CIERRE_CONEXIONES);
        }
        return em;
    }
    
    /**
     * Metodo encargado de listar todos los empleados
     * @return 
     */
    public List listar(){
        List<Empleado> lstEmp = new ArrayList();
        String sql = DatabaseConstants.SQL_LISTAR_EMPLEADO;
        try {
            AppConstants.log.log(Level.INFO, "Inicia consulta a la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado();
                em.setId(rs.getInt("IdEmpleado"));
                em.setTipoDocumento(rs.getString("Descripcion"));
                em.setDni(rs.getString("Documento"));
                em.setNombres(rs.getString("Nombres"));
                em.setTelefono(rs.getString("Telefono"));
                em.setUser(rs.getString("User"));
                em.setEmail(rs.getString("Email"));
                em.setEstado(rs.getString("Nombre"));
                em.setPerfil(rs.getString("NombrePerfil"));
                em.setIdPerfil(rs.getInt("IdPerfil"));
                em.setPass(rs.getString("clave"));
                lstEmp.add(em);
                AppConstants.log.log(Level.INFO, "Finaliza consulta a la base de datos de manera exitosa{0}", em.toString());
            }
            AppConstants.log.log(Level.INFO, "Finaliza consulta a la base de datos de manera exitosa");
        } catch (SQLException e) {
            AppConstants.log.log(Level.SEVERE, ERROR_DB_MESSAGE + e.getMessage(), e);
        } finally{
            CloseConnections.closeConnection(conn);
            CloseConnections.closePrepareStatement(ps);
            CloseConnections.closeResultSet(rs);
            AppConstants.log.log(Level.INFO, CIERRE_CONEXIONES);
        }
        return lstEmp;
    }
    
    /**
     * Metodo encargado de hacer inserciones de los empleados
     * @param em
     * @return 
     */
    public int agregar(Empleado em){
        int response = 0;
        String sql = DatabaseConstants.SQL_AGREGAR_EMPLEADO;
        try {
            AppConstants.log.log(Level.INFO, "Inicia insercion en la base de datos"+em.toString());
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(em.getTipoDocumento()));
            ps.setString(2, em.getDni());
            ps.setString(3, em.getNombres());
            ps.setString(4, em.getTelefono());
            ps.setInt(5, Integer.parseInt(em.getEstado()));
            ps.setString(6, em.getUser());
            ps.setString(7, em.getEmail());
            ps.setInt(8, Integer.parseInt(em.getPerfil()));
            ps.setString(9, em.getPass());
            response = ps.executeUpdate();
            AppConstants.log.log(Level.INFO, "Finaliza insercion en la base de datos de manera exitosa");
        } catch (SQLException e) {
            AppConstants.log.log(Level.SEVERE, ERROR_DB_MESSAGE + e.getMessage(), e);
        } finally{
            CloseConnections.closeConnection(conn);
            CloseConnections.closePrepareStatement(ps);
            CloseConnections.closeResultSet(rs);
            AppConstants.log.log(Level.INFO, CIERRE_CONEXIONES);
        }
        return response;
    }
    
    /**
     * Metodo encargado de listar por Id de empleado
     * @param id
     * @return 
     */
    public Empleado listarId(int id){
        Empleado em = new Empleado();
        String sql = DatabaseConstants.SQL_LISTAR_EMPLEADO_POR_ID;
        try {
            AppConstants.log.log(Level.INFO, "Inicia consulta a la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                em.setDni(rs.getString("Dni"));
                em.setNombres(rs.getString("Nombres"));
                em.setTelefono(rs.getString("Telefono"));
                em.setEstado(rs.getString("Estado"));
                em.setUser(rs.getString("User"));
                em.setEmail(rs.getString("Email"));
                em.setId(id);
            }
            AppConstants.log.log(Level.INFO, "Finaliza consulta a la base de datos de manera exitosa");
        } catch (SQLException e) {
            AppConstants.log.log(Level.SEVERE, ERROR_DB_MESSAGE + e.getMessage(), e);
        } finally{
            CloseConnections.closeConnection(conn);
            CloseConnections.closePrepareStatement(ps);
            CloseConnections.closeResultSet(rs);
            AppConstants.log.log(Level.INFO, CIERRE_CONEXIONES);
        }
        return em;
    }
    
    /**
     * Metodo encargado de actualizar el empleado en la base de datos
     * @param em
     * @return 
     */
    public int actualizar(Empleado em){
        int response = 0;
        String sql = DatabaseConstants.SQL_ACTUALIZAR_EMPLEADO;
        try {
            AppConstants.log.log(Level.INFO, "Inicia actualizacion en la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(em.getTipoDocumento()));
            ps.setString(2, em.getDni());
            ps.setString(3, em.getNombres());
            ps.setString(4, em.getTelefono());
            ps.setInt(5, Integer.parseInt(em.getEstado()));
            ps.setString(6, em.getUser());
            ps.setString(7, em.getEmail());
            ps.setInt(8, Integer.parseInt(em.getPerfil()));
            ps.setString(9, em.getPass());
            ps.setInt(10, em.getId());
            response = ps.executeUpdate();
            AppConstants.log.log(Level.INFO, "Finaliza actualizacion en la base de datos de manera exitosa");
        } catch (SQLException e) {
            AppConstants.log.log(Level.SEVERE, ERROR_DB_MESSAGE + e.getMessage(), e);
        } finally{
            CloseConnections.closeConnection(conn);
            CloseConnections.closePrepareStatement(ps);
            CloseConnections.closeResultSet(rs);
            AppConstants.log.log(Level.INFO, CIERRE_CONEXIONES);
        }
        return response;
    }
    
    /**
     * Metodo encargado de eliminar un empleado por medio del id
     * @param id 
     */
    public int delete(int id){
        String sql = DatabaseConstants.SQL_ELIMINAR_EMPLEADO;
        int response = 0;
        try {
            AppConstants.log.log(Level.INFO, "Inicia eliminacion en la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            response = ps.executeUpdate();
            AppConstants.log.log(Level.INFO, "Finaliza eliminacion en la base de datos de manera exitosa");
        } catch (SQLException e) { 
            AppConstants.log.log(Level.SEVERE, ERROR_DB_MESSAGE + e.getMessage(), e);
        } finally{
            CloseConnections.closeConnection(conn);
            CloseConnections.closePrepareStatement(ps);
            CloseConnections.closeResultSet(rs);
            AppConstants.log.log(Level.INFO, CIERRE_CONEXIONES);
        }
        return response;
    }
    
}
