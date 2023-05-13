<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Script/bootstrap.min.css" rel="stylesheet">
        <LINK rel=StyleSheet href="css/LoginStyle2.css" type="text/css" media=screen>
        <meta charset="UTF-8">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js" integrity="sha512-AA1Bzp5Q0K1KanKKmvN/4d3IRKVlv9PYgwFPvm32nPO6QS8yH1HO7LbgB1pgiOxPtfeg5zEn2ba64MUcqJx6CA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
     </head>
    <body class="bg-dark">
        <% 
            if (request.getAttribute("strMensaje") != null && request.getAttribute("strTipo") != null)  {
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
    <center>
        <div class="login card">
            <h2 class="active" style="text-align: center"> Iniciar Sesion </h2>
            <form method="POST" action="Validaciones" >
                <input type="text" class="text" id="user" name="txtUser" >
                <span>Nombre de Usuario</span>
                <br><br><br>
                <input type="password" class="text" id="contra" name="txtPass">
                <span>Contraseña</span>
                <br>
                <input type="submit" value="Ingresar" id="btnAceptar" class="signin" name="accion"></input>
                <span id="result"></span>
                <a href="#">¿Olvido su Contraseña?</a><br><br>
                <img class="img-fluid" src="img/Logo.png">
            </form>
        </div>
    </center>
</body>
</html>
