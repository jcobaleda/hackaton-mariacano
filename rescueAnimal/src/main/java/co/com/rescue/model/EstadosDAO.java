/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.rescue.model;

import co.com.rescue.config.AppConstants;
import co.com.rescue.config.CloseConnections;
import co.com.rescue.config.DatabaseConstants;
import co.com.rescue.config.DatabaseUtil;
import co.com.rescue.config.Estados;
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
public class EstadosDAO {
    
    DatabaseUtil dbu = new DatabaseUtil();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    private static final String ERROR_DB_MESSAGE = "Error en la base de datos: ";
    private static final String CIERRE_CONEXIONES = "Se cierran conexiones de manera exitosa";
    
    /**
     * Metodo encargado de listar todos los Cliente
     * @return 
     */
    public List listar(){
        List<Estados> lstEst = new ArrayList();
        String sql = DatabaseConstants.SQL_LISTAR_ESTADOS;
        try {
            AppConstants.log.log(Level.INFO, "Inicia consulta a la base de datos");
            conn = dbu.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Estados est = new Estados();
                est.setId(rs.getInt("id"));
                est.setNombre(rs.getString("Nombre"));
                est.setValor(Integer.parseInt(rs.getString("Valor")));
                lstEst.add(est);
                AppConstants.log.log(Level.INFO, "Finaliza consulta a la base de datos de manera exitosa{0}", est.toString());
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
        return lstEst;
    }
}
