function editarEmpleado(id) {
    var codigo = $("#idEmpleado_" + id).text();
    $("#codigoEmpleado").attr("value", codigo);
    var tipoDocumento = $("#tipoDocumento_" + id).text();
    $("#txtTipoDni").attr("value", tipoDocumento);
    var documento = $("#documento_" + id).text();
    $("#documentoEmpleado").attr("value", documento);
    var nombre = $("#nombre_" + id).text();
    $("#nombreEmpleado").attr("value", nombre);
    var telefono = $("#telefono_" + id).text();
    $("#telefonoEmpleado").attr("value", telefono);
    var email = $("#email_" + id).text();
    $("#emailEmpleado").attr("value", email);
    var usuario = $("#user_" + id).text();
    $("#usuarioEmpleado").attr("value", usuario);
    var estado = $("#estado_" + id).text();
    $("#estadoEmpleado").attr("value", estado);
}
function eliminarEmpleado(id) {
    $("#btneliminar").attr("href", "Controlador?menu=Empleado&accion=Eliminar&id=" + id);
}


