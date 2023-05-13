<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Principal</title>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js" integrity="sha512-AA1Bzp5Q0K1KanKKmvN/4d3IRKVlv9PYgwFPvm32nPO6QS8yH1HO7LbgB1pgiOxPtfeg5zEn2ba64MUcqJx6CA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </head>
    <body>
        <%
            if (request.getAttribute("strMensaje") != null && request.getAttribute("strTipo") != null) {
        %>
        <input type="text" hidden="" id="txtMensaje" value="<%= request.getAttribute("strMensaje")%>"/>
        <input type="text" hidden="" id="txtTipo" value="<%= request.getAttribute("strTipo")%>"/>
        <script>
            var mensaje = document.getElementById("txtMensaje").value;
            var tipo = document.getElementById("txtTipo").value;
            swal("Mensaje", mensaje, tipo);
        </script>
        <%
            }
        %>

        <main>
            <nav class="navbar navbar-expand-lg text-bg-secondary" aria-label="Offcanvas navbar large">
                <div class="offcanvas-body">
                    <ul class="navbar-nav flex-grow-1">
                        <c:forEach var="modu" items="${modulos}">
                            <li class="nav-item">
                                <a class="nav-link py-3 rounded-0 link-light" title="${modu.getNombre()}" data-bs-toggle="tooltip" data-bs-placement="right" href="${modu.getUrl()}" target="myFrame">
                                    ${modu.getIcono()} - ${modu.getNombre()}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="dropdown">
                    <div class="flex-shrink-0 dropdown">
                        <a href="#" class="d-flex align-items-center justify-content-center p-3 link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://github.com/mdo.png" alt="mdo" width="24" height="24" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small shadow" style="position: absolute; inset: 0px 0px auto auto; margin: 0px; transform: translate(0px, 34px);">
                            <li><a class="dropdown-item" href="#">${usuario.getEmail()}</a></li>
                            <li><a class="dropdown-item" href="#">${usuario.getUser()}</a></li>
                            <li><a class="dropdown-item" href="#">${usuario.getNombres()}</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a type="button" class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#cerrar">Salir</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="container-fluid" style="height:710px;">
                <br><br>
                <iframe name="myFrame" style="height: 100%; width: 100%">
                    <img src="img/LogoMultiMotos.png" style="height:610px;" class="img-fluid"/>
                </iframe>
            </div>
            <div id="cerrar" class="modal" tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Cerrar Sesion</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>¿Esta seguro que desea finalizar la sesión ahora?</p>
                        </div>
                        <div class="modal-footer">
                            <form action="Validaciones" method="POST">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" class="btn btn-primary" name="accion" value="Salir">Aceptar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
