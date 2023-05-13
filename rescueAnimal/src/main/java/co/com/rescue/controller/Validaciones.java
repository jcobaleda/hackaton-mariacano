/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.com.rescue.controller;

import co.com.rescue.config.AppConstants;
import co.com.rescue.config.Validator;
import co.com.rescue.model.Empleado;
import co.com.rescue.model.EmpleadoDAO;
import co.com.rescue.model.ModuloPerfil;
import co.com.rescue.model.ModuloPerfilDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel Velmonto
 */
public class Validaciones extends HttpServlet {

    EmpleadoDAO edao = new EmpleadoDAO();
    Empleado empleado = new Empleado();
    ModuloPerfil modu = new ModuloPerfil();
    ModuloPerfilDAO modPerDao = new ModuloPerfilDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = "";
        String mostrar = "";
        HttpSession session = request.getSession();
        try {
            AppConstants.log.log(Level.INFO, "Inicia validaciones: ");
            String accion = request.getParameter("accion");
            if (accion.equalsIgnoreCase("Ingresar")) {
                if (request.getParameter("txtUser").equals("")) {
                    mensaje += "Ingrese el usuario,";
                }
                if (request.getParameter("txtPass").equals("")) {
                    mensaje += "Ingrese la contraseña,";
                }
                if (!mensaje.equals("")) {
                    throw new Exception(mensaje.substring(0, mensaje.length() - 1));
                }
                String user = request.getParameter("txtUser");
                String pass = Validator.getMD5(request.getParameter("txtPass"));
                empleado = edao.validar(user, pass);
                AppConstants.log.log(Level.INFO, "Se valido en la base de datos: " + empleado.getNombres());
                if (empleado.getNombres() != null) {
                    List modulos = modPerDao.listarByPerfil(empleado.getIdPerfil());
                    mostrar = "hidden";
                    request.setAttribute("mostrar", mostrar);
                    request.setAttribute("strMensaje", "Se inicio la sesion");
                    request.setAttribute("strTipo", "success");
                    request.setAttribute("usuario", empleado);
                    request.setAttribute("modulos", modulos);
                    session.setAttribute("usuario", empleado);
                    request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
                } else {
                    throw new Exception("Usuario o contraseña incorrecto");
                }
            } else {
                session.invalidate();
                request.setAttribute(AppConstants.STR_MENSAJE, "Sesión finalizada.");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mostrar", mostrar);
            request.setAttribute("strMensaje", e.getMessage());
            request.setAttribute("strTipo", "error");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            AppConstants.log.log(Level.SEVERE, "Error de ejecucion: " + e.getMessage(), e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
