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
public class TipoDocumentoDAO {
    
    DatabaseUtil dbu = new DatabaseUtil();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    private static final String ERROR_DB_MESSAGE = "Error en la base de datos: ";
    private static final String CIERRE_CONEXIONES = "Se cierran conexiones de manera exitosa";
    
    
    public List listar() {
        List<TipoDocumento> lstTipoDoc = new ArrayList();
        String sql = DatabaseConstants.SQL_LISTAR_TIPODOC;
        try {
            AppConstants.log.log(Level.INFO, "Inicia consulta a la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoDocumento tipoDoc = new TipoDocumento();
                tipoDoc.setId(rs.getInt("id"));
                tipoDoc.setDescripcion(rs.getString("Descripcion"));
                lstTipoDoc.add(tipoDoc);
                AppConstants.log.log(Level.INFO, "Finaliza consulta a la base de datos de manera exitosa{0}", tipoDoc.toString());
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
        return lstTipoDoc;
    }

    public int agregar(TipoDocumento tipoDoc) {
        int response = 0;
        String sql = DatabaseConstants.SQL_AGREGAR_TIPODOC;
        try {
            AppConstants.log.log(Level.INFO, "Inicia insercion en la base de datos"+tipoDoc.toString());
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, tipoDoc.getDescripcion());
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

    public int delete(int idTipoDoc) {
        String sql = DatabaseConstants.SQL_ELIMINAR_TIPODOC;
        int res = 0;
        try {
            AppConstants.log.log(Level.INFO, "Inicia eliminacion en la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(idTipoDoc));
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

    public int actualizar(TipoDocumento tipoDoc) {
        int response = 0;
        String sql = DatabaseConstants.SQL_ACTUALIZAR_TIPODOC;
        try {
            AppConstants.log.log(Level.INFO, "Inicia actualizacion en la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, tipoDoc.getDescripcion());
            ps.setInt(2, tipoDoc.getId());
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
