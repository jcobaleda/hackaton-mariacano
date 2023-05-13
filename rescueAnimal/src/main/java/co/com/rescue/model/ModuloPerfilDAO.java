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
public class ModuloPerfilDAO {
    DatabaseUtil dbu = new DatabaseUtil();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    private static final String ERROR_DB_MESSAGE = "Error en la base de datos: ";
    private static final String CIERRE_CONEXIONES = "Se cierran conexiones de manera exitosa";
    
    public List listarByPerfil(int idPerfil) {
        List<Modulos> lstMod = new ArrayList();
        String sql = DatabaseConstants.SQL_LISTAR_MODULOS_BY_PERFIL;
        try {
            AppConstants.log.log(Level.INFO, "Inicia consulta a la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPerfil);
            rs = ps.executeQuery();
            while (rs.next()) {
                Modulos mod = new Modulos();
                mod.setNombre(rs.getString("Nombre"));
                mod.setUrl(rs.getString("Url"));
                mod.setIcono(rs.getString("icono"));
                lstMod.add(mod);
                AppConstants.log.log(Level.INFO, "Finaliza consulta a la base de datos de manera exitosa{0}"+ mod.toString());
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
        return lstMod;
    }
    
    public List listar() {
        List<ModuloPerfil> lstModPer = new ArrayList();
        String sql = DatabaseConstants.SQL_LISTAR_MODULES_BY_ROLE;
        try {
            AppConstants.log.log(Level.INFO, "Inicia consulta a la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ModuloPerfil modPer = new ModuloPerfil();
                Modulos mod = new Modulos();
                Perfiles per = new Perfiles();
                modPer.setId(rs.getInt("id"));
                mod.setNombre(rs.getString("Nombre"));
                per.setNombrePerfil(rs.getString("NombrePerfil"));
                modPer.setModulo(mod);
                modPer.setPerfil(per);
                lstModPer.add(modPer);
                AppConstants.log.log(Level.INFO, "Finaliza consulta a la base de datos de manera exitosa{0}"+ modPer.toString());
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
        return lstModPer;
    }

    public int agregar(ModuloPerfil modPer) {
        int response = 0;
        String sql = DatabaseConstants.SQL_AGREGAR_MODULO_PERFIL;
        try {
            AppConstants.log.log(Level.INFO, " Inicia insercion en la base de datos"+modPer.toString());
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, modPer.getModulo().getId());
            ps.setInt(2, modPer.getPerfil().getId());
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

    public int delete(int idModPer) {
        String sql = DatabaseConstants.SQL_ELIMINAR_MODULO_BY_PERFIL;
        int res = 0;
        try {
            AppConstants.log.log(Level.INFO, "Inicia eliminacion en la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(idModPer));
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

    public int actualizar(ModuloPerfil modPer) {
        int response = 0;
        String sql = DatabaseConstants.SQL_ACTUALIZAR_MODULO_BY_PERFIL;
        try {
            AppConstants.log.log(Level.INFO, "Inicia actualizacion en la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, modPer.getModulo().getId());
            ps.setInt(2, modPer.getPerfil().getId());
            ps.setInt(3, modPer.getId());
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
