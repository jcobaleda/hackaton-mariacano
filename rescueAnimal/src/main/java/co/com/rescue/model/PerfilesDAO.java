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
public class PerfilesDAO {
    
    DatabaseUtil dbu = new DatabaseUtil();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    private static final String ERROR_DB_MESSAGE = "Error en la base de datos: ";
    private static final String CIERRE_CONEXIONES = "Se cierran conexiones de manera exitosa";

    public List listar() {
        List<Perfiles> lstPer = new ArrayList();
        String sql = DatabaseConstants.SQL_LISTAR_PERFILES;
        try {
            AppConstants.log.log(Level.INFO, "Inicia consulta a la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Perfiles per = new Perfiles();
                per.setId(rs.getInt("IdPerfil"));
                per.setNombrePerfil(rs.getString("NombrePerfil"));
                per.setDescripcion(rs.getString("Descripcion"));
                per.setEstado(rs.getString("Nombre"));
                lstPer.add(per);
                AppConstants.log.log(Level.INFO, "Finaliza consulta a la base de datos de manera exitosa{0}", per.toString());
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
        return lstPer;
    }

    public int agregar(Perfiles per) {
        int response = 0;
        String sql = DatabaseConstants.SQL_AGREGAR_PERFIL;
        try {
            AppConstants.log.log(Level.INFO, " Inicia insercion en la base de datos"+per.toString());
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, per.getNombrePerfil());
            ps.setString(2, per.getDescripcion());
            ps.setInt(3, Integer.parseInt(per.getEstado()));
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

    public int delete(int idPerfil) {
        String sql = DatabaseConstants.SQL_ELIMINAR_PERFIL;
        int res = 0;
        try {
            AppConstants.log.log(Level.INFO, "Inicia eliminacion en la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPerfil);
            res = ps.executeUpdate();
            AppConstants.log.log(Level.INFO, "Finaliza eliminacion en la base de datos de manera exitosa");
        } catch (SQLException e) { 
            AppConstants.log.log(Level.SEVERE, ERROR_DB_MESSAGE + e.getMessage(), e);
        } finally{
            CloseConnections.closeConnection(conn);
            CloseConnections.closePrepareStatement(ps);
            CloseConnections.closeResultSet(rs);
            AppConstants.log.log(Level.INFO, CIERRE_CONEXIONES);
        }
        return res;
    }

    public int actualizar(Perfiles per) {
        int response = 0;
        String sql = DatabaseConstants.SQL_ACTUALIZAR_PERFIL;
        try {
            AppConstants.log.log(Level.INFO, "Inicia actualizacion en la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, per.getNombrePerfil());
            ps.setString(2, per.getDescripcion());
            ps.setInt(3,Integer.parseInt(per.getEstado()));
            ps.setInt(4, per.getId());
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
    
}
