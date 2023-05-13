/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.rescue.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author Daniel Velmonto
 */
public class CloseConnections {
    
    public static void closePrepareStatement(PreparedStatement ps){
        try {
            if (ps!=null) {
                ps.close();
            }
        } catch (SQLException e) {
            AppConstants.log.log(Level.SEVERE, "Error cerrando PreparedStatement: "+e.getMessage(), e);
        }
    }
    
    public static void closeConnection(Connection cn){
        try {
            if (cn!=null) {
                cn.close();
            }
        } catch (SQLException e) {
            AppConstants.log.log(Level.SEVERE, "Error cerrando Connection: "+e.getMessage(), e);
        }
    }
    
    public static void closeResultSet(ResultSet rs){
        try {
            if (rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
            AppConstants.log.log(Level.SEVERE, "Error cerrando ResultSet: "+e.getMessage(), e);
        }
    }
}
