package co.com.rescue.controller;

import co.com.rescue.config.AppConstants;
import co.com.rescue.config.Validator;
import co.com.rescue.model.Empleado;
import co.com.rescue.model.EmpleadoDAO;
import co.com.rescue.model.EstadosDAO;
import co.com.rescue.model.Mascota;
import co.com.rescue.model.MascotaDAO;
import co.com.rescue.model.ModuloPerfil;
import co.com.rescue.model.ModuloPerfilDAO;
import co.com.rescue.model.Modulos;
import co.com.rescue.model.ModulosDAO;
import co.com.rescue.model.Perfiles;
import co.com.rescue.model.PerfilesDAO;
import co.com.rescue.model.TipoDocumento;
import co.com.rescue.model.TipoDocumentoDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel Velmonto
 */
public class Controlador extends HttpServlet {
    
    Empleado em = new Empleado();
    EmpleadoDAO empDao = new EmpleadoDAO();
    TipoDocumento tipoDoc = new TipoDocumento();
    TipoDocumentoDAO tipoDocDao = new TipoDocumentoDAO();
    EstadosDAO estDao = new EstadosDAO();
    Perfiles per = new Perfiles();
    PerfilesDAO perDao = new PerfilesDAO();
    ModuloPerfil modPer = new ModuloPerfil();
    ModuloPerfilDAO modPerDao = new ModuloPerfilDAO();
    ModulosDAO modDao = new ModulosDAO();
    Modulos mod = new Modulos();
    Mascota mas = new Mascota();
    MascotaDAO masDao = new MascotaDAO();

    int idEmpleado;
    int idProducto;
    Double totalPagar;
    String numeroSerie;
    int res;
    int idProveedor;
    int idTipoDoc;
    int idPerfil;
    int idModPer;
    int idMedPag;

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

        try {
            String menu = request.getParameter("menu");
            String accion = request.getParameter("accion");
            AppConstants.log.log(Level.INFO, "accion: " + accion);
            switch (menu) {
                case "Principal":
                    request.getRequestDispatcher("Principal.jsp").forward(request, response);
                    break;
                case "Empleado":
                    extractedEmpleado(request, response, accion);
                    request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                    break;
                case "TipoDoc":
                    extractedTipoDoc(request, response, accion);
                    request.getRequestDispatcher("TiposDocumento.jsp").forward(request, response);
                    break;
                case "Perfiles":
                    extractedPerfiles(request, response, accion);
                    request.getRequestDispatcher("Perfiles.jsp").forward(request, response);
                    break;
                case "ModulePerfil":
                    extractedModPer(request, response, accion);
                    request.getRequestDispatcher("ModulosPerfil.jsp").forward(request, response);
                    break;
                case "Mascota":
                    extractedMascota(request, response, accion);
                    request.getRequestDispatcher("Mascotas.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (IOException | ServletException e) {
            AppConstants.log.log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }
    }

    private void extractedEmpleado(HttpServletRequest request, HttpServletResponse response, String accion)
            throws ServletException, IOException, AssertionError {
        try {
            switch (accion) {
                case "Listar":
                    extractedListarEmpleado(request);
                    break;
                case "Agregar":
                    extractedAgregarEmpleado(request, response);
                    break;
                case "Eliminar":
                    extractedEliminarEmpleado(request, response);
                    break;
                case "Editar":
                    extractedEditarEmpleado(request, response);
                    break;
                case "Actualizar":
                    extractedActualizarEmpleado(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (IOException | ServletException e) {
            AppConstants.log.log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }
    }

    private void extractedActualizarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            em.setTipoDocumento(request.getParameter("txtTipoDni"));
            em.setDni(request.getParameter("txtDni"));
            em.setNombres(request.getParameter("txtNombres"));
            em.setTelefono(request.getParameter("txtTelefono"));
            em.setEstado(request.getParameter("txtEstado"));
            em.setUser(request.getParameter("txtUsuario"));
            em.setEmail(request.getParameter("txtEmail"));
            em.setPerfil(request.getParameter("txtPerfil"));
            em.setPass(Validator.getMD5(request.getParameter("txtPass")));
            em.setId(Integer.parseInt(request.getParameter("txtCodigo")));
            AppConstants.log.log(Level.INFO, "Se reciben datos desde la vista" + em.toString());
            res = empDao.actualizar(em);
            if (res > 0) {
                request.setAttribute("empleado", em);
                request.setAttribute(AppConstants.STR_MENSAJE, "Empleado actualizado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Empleado actualizado con exito");
            } else {
                throw new Exception("Ocurrio un error actualizando el empleado");
            }
            AppConstants.log.log(Level.INFO, "Se Actualizan datos en la base de datos: " + res);
            request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void extractedEditarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        try {
            idEmpleado = Integer.parseInt(request.getParameter("id"));
            Empleado e = empDao.listarId(idEmpleado);
            request.setAttribute("empleado", e);
            request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
        } catch (ServletException | IOException e) {
            AppConstants.log.log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }

    }

    private void extractedListarEmpleado(HttpServletRequest request) {
        try {
            List lista = empDao.listar();
            List lstDoc = tipoDocDao.listar();
            List lstEst = estDao.listar();
            List lstPer = perDao.listar();
            AppConstants.log.log(Level.INFO, "Se envian datos a la vista de empleados");
            request.setAttribute("empleados", lista);
            request.setAttribute("documentos", lstDoc);
            request.setAttribute("estados", lstEst);
            request.setAttribute("perfiles", lstPer);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private void extractedEliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            idEmpleado = Integer.parseInt(request.getParameter("id"));
            res = empDao.delete(idEmpleado);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Empleado eliminado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Empleado agregado con exito");
            } else {
                throw new Exception("Ocurrio un error agregando el producto");
            }
            request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void extractedAgregarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            em.setTipoDocumento(request.getParameter("txtTipoDni"));
            em.setDni(request.getParameter("txtDni"));
            em.setNombres(request.getParameter("txtNombres"));
            em.setTelefono(request.getParameter("txtTelefono"));
            em.setEstado(request.getParameter("txtEstado"));
            em.setUser(request.getParameter("txtUsuario"));
            em.setPass(Validator.getMD5(request.getParameter("txtPass")));
            em.setEmail(request.getParameter("txtEmail"));
            em.setPerfil(request.getParameter("txtPerfil"));
            AppConstants.log.log(Level.INFO, "Se reciben datos desde la vista", em.toString());
            res = empDao.agregar(em);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Empleado agregado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Producto agregado con exito");
            } else {
                throw new Exception("Ocurrio un error agregando el empleado");
            }
            AppConstants.log.log(Level.INFO, "Despues de agregar empleado en la base de datos");
            request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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

   

    private void extractedTipoDoc(HttpServletRequest request, HttpServletResponse response, String accion) {
        try {
            switch (accion) {
                case "Listar":
                    extractedListarTipoDoc(request);
                    break;
                case "Agregar":
                    extractedAgregarTipoDoc(request, response);
                    break;
                case "Eliminar":
                    extractedEliminarTipoDoc(request, response);
                    break;
                case "Actualizar":
                    extractedActualizarTipoDoc(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (IOException | ServletException e) {
            AppConstants.log.log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }
    }

    private void extractedListarTipoDoc(HttpServletRequest request) {
        try {
            List lista = tipoDocDao.listar();
            AppConstants.log.log(Level.INFO, "Se envian datos a la vista de tipos documentos");
            request.setAttribute("documentos", lista);
        } catch (Exception e) {
            AppConstants.log.log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }
    }

    private void extractedAgregarTipoDoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            tipoDoc.setDescripcion(request.getParameter("txtDni"));
            AppConstants.log.log(Level.INFO, "Se reciben datos desde la vista", tipoDoc.toString());
            res = tipoDocDao.agregar(tipoDoc);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Tipo de documento agregado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Tipo de documento agregado con exito");
            } else {
                throw new Exception("Ocurrio un error agregando el Tipo de documento");
            }
            AppConstants.log.log(Level.INFO, "Despues de agregar Tipo de documento en la base de datos");
            request.getRequestDispatcher("Controlador?menu=TipoDoc&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=TipoDoc&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void extractedEliminarTipoDoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            idTipoDoc = Integer.parseInt(request.getParameter("id"));
            res = tipoDocDao.delete(idTipoDoc);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Tipo de documento eliminado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Tipo de documento eliminado con exito");
            } else {
                throw new Exception("Ocurrio un error eliminando el Tipo de documento");
            }
            request.getRequestDispatcher("Controlador?menu=TipoDoc&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=TipoDoc&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void extractedActualizarTipoDoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            tipoDoc.setId(Integer.parseInt(request.getParameter("txtCodigo")));
            tipoDoc.setDescripcion(request.getParameter("txtDni"));
            AppConstants.log.log(Level.INFO, "Se reciben datos desde la vista" + tipoDoc.toString());
            res = tipoDocDao.actualizar(tipoDoc);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Tipo de documento actualizado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Tipo de documento actualizado con exito");
            } else {
                throw new Exception("Ocurrio un error actualizando el Tipo de documento");
            }
            AppConstants.log.log(Level.INFO, "Se Actualizan datos en la base de datos: " + res);
            request.getRequestDispatcher("Controlador?menu=TipoDoc&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=TipoDoc&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void extractedPerfiles(HttpServletRequest request, HttpServletResponse response, String accion) {
        try {
            switch (accion) {
                case "Listar":
                    extractedListarPerfiles(request);
                    break;
                case "Agregar":
                    extractedAgregarPerfiles(request, response);
                    break;
                case "Eliminar":
                    extractedEliminarPerfiles(request, response);
                    break;
                case "Actualizar":
                    extractedActualizarPerfiles(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (IOException | ServletException e) {
            AppConstants.log.log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }
    }

    private void extractedListarPerfiles(HttpServletRequest request) {
        try {
            List lista = perDao.listar();
            List lstEst = estDao.listar();
            AppConstants.log.log(Level.INFO, "Se envian datos a la vista de Perfiles");
            request.setAttribute("perfiles", lista);
            request.setAttribute("estados", lstEst);
        } catch (Exception e) {
            AppConstants.log.log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }
    }

    private void extractedAgregarPerfiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            per.setNombrePerfil(request.getParameter("txtNombre"));
            per.setDescripcion(request.getParameter("txtDescripcion"));
            per.setEstado(request.getParameter("txtEstado"));
            AppConstants.log.log(Level.INFO, "Se reciben datos desde la vista", per.toString());
            res = perDao.agregar(per);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Perfil agregado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Perfil agregado con exito");
            } else {
                throw new Exception("Ocurrio un error agregando el Perfil");
            }
            AppConstants.log.log(Level.INFO, "Despues de agregar Perfil en la base de datos");
            request.getRequestDispatcher("Controlador?menu=Perfiles&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=Perfiles&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void extractedEliminarPerfiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            idPerfil = Integer.parseInt(request.getParameter("id"));
            res = perDao.delete(idPerfil);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Perfil eliminado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Perfil eliminado con exito");
            } else {
                throw new Exception("Ocurrio un error eliminando el Perfil");
            }
            request.getRequestDispatcher("Controlador?menu=Perfiles&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=Perfiles&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void extractedActualizarPerfiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            per.setId(Integer.parseInt(request.getParameter("txtCodigo")));
            per.setNombrePerfil(request.getParameter("txtNombres"));
            per.setDescripcion(request.getParameter("txtDescripcion"));
            per.setEstado(request.getParameter("txtEstado"));
            AppConstants.log.log(Level.INFO, "Se reciben datos desde la vista" + per.toString());
            res = perDao.actualizar(per);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Perfil actualizado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Perfil actualizado con exito");
            } else {
                throw new Exception("Ocurrio un error actualizando el Perfil");
            }
            AppConstants.log.log(Level.INFO, "Se Actualizan datos en la base de datos: " + res);
            request.getRequestDispatcher("Controlador?menu=Perfiles&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=Periles&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void extractedModPer(HttpServletRequest request, HttpServletResponse response, String accion) {
        try {
            switch (accion) {
                case "Listar":
                    extractedListarModPer(request);
                    break;
                case "Agregar":
                    extractedAgregarModPer(request, response);
                    break;
                case "Eliminar":
                    extractedEliminarModPer(request, response);
                    break;
                case "Actualizar":
                    extractedActualizarModPer(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (IOException | ServletException e) {
            AppConstants.log.log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }
    }

    private void extractedListarModPer(HttpServletRequest request) {
        try {
            List lstModPer = modPerDao.listar();
            List lista = perDao.listar();
            List lstMod = modDao.listar();
            AppConstants.log.log(Level.INFO, "Se envian datos a la vista de Modulo perfil");
            request.setAttribute("moduloperfil", lstModPer);
            request.setAttribute("modulos", lstMod);
            request.setAttribute("perfiles", lista);
        } catch (Exception e) {
            AppConstants.log.log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }
    }

    private void extractedAgregarModPer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            per.setId(Integer.parseInt(request.getParameter("txtPerfil")));
            mod.setId(Integer.parseInt(request.getParameter("txtModulo")));
            modPer.setModulo(mod);
            modPer.setPerfil(per);
            AppConstants.log.log(Level.INFO, "Se reciben datos desde la vista" + per.toString());
            res = modPerDao.agregar(modPer);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Modulo por perfil agregado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Modulo por perfil agregado con exito");
            } else {
                throw new Exception("Ocurrio un error agregando el Modulo por perfil");
            }
            AppConstants.log.log(Level.INFO, "Despues de agregar Modulo por perfil en la base de datos");
            request.getRequestDispatcher("Controlador?menu=ModulePerfil&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=ModulePerfil&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void extractedEliminarModPer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            idModPer = Integer.parseInt(request.getParameter("id"));
            res = modPerDao.delete(idModPer);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Modulo por perfil eliminado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Modulo por perfil eliminado con exito");
            } else {
                throw new Exception("Ocurrio un error eliminando el Modulo por perfil");
            }
            request.getRequestDispatcher("Controlador?menu=ModulePerfil&accion=Listar").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=ModulePerfil&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void extractedActualizarModPer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            modPer.setId(Integer.parseInt(request.getParameter("txtCodigo")));
            per.setId(Integer.parseInt(request.getParameter("txtPerfil")));
            mod.setId(Integer.parseInt(request.getParameter("txtModulo")));
            modPer.setModulo(mod);
            modPer.setPerfil(per);
            AppConstants.log.log(Level.INFO, "Se reciben datos desde la vista" + modPer.toString());
            res = modPerDao.actualizar(modPer);
            if (res > 0) {
                request.setAttribute(AppConstants.STR_MENSAJE, "Modulo por perfil actualizado con exito");
                request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_SUCCESS);
                AppConstants.log.log(Level.INFO, "Modulo por perfil actualizado con exito");
            } else {
                throw new Exception("Ocurrio un error actualizando el Modulo por perfil");
            }
            AppConstants.log.log(Level.INFO, "Se Actualizan datos en la base de datos: " + res);
            request.getRequestDispatcher("Controlador?menu=ModulePerfil&accion=Listar").forward(request, response);
            AppConstants.log.log(Level.INFO, "Modulo por perfil actualizado con exito");
        } catch (Exception ex) {
            request.setAttribute(AppConstants.STR_MENSAJE, ex.getMessage());
            request.setAttribute(AppConstants.STR_TIPO, AppConstants.STR_TIPO_ERROR);
            request.getRequestDispatcher("Controlador?menu=ModulePerfil&accion=Listar").forward(request, response);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void extractedMascota(HttpServletRequest request, HttpServletResponse response, String accion) {
        switch (accion) {
            case "Listar":
                extractedListarMascota(request);
                break;
            case "Agregar":
                extractedAgregarMascota(request, response);
                break;
            case "Eliminar":
                extractedEliminarMascota(request, response);
                break;
            case "Actualizar":
                extractedActualizarMascota(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void extractedListarMascota(HttpServletRequest request) {
        try {
            List lstMas = masDao.listar();
            AppConstants.log.log(Level.INFO, "Se envian datos a la vista de Mascota");
            request.setAttribute("mascota", lstMas);
        } catch (Exception e) {
            AppConstants.log.log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }
    }

    private void extractedAgregarMascota(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void extractedEliminarMascota(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void extractedActualizarMascota(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
