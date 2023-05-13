function editarCliente(id) {
    var codigo = $("#idCliente_" + id).text();
    $("#codigoCliente").attr("value", codigo);
    var tipoDocumento = $("#tipoDocumento_" + id).text();
    $("#tipoDocumentoCliente").attr("value", tipoDocumento);
    var documento = $("#documento_" + id).text();
    $("#documentoCliente").attr("value", documento);
    var nombre = $("#nombre_" + id).text();
    $("#nombreCliente").attr("value", nombre);
    var direccion = $("#direccion_" + id).text();
    $("#direccionCliente").attr("value", direccion);
    var estado = $("#estado_" + id).text();
    $("#estadoCliente").attr("value", estado);
}
function eliminarCliente(id) {
    $("#btneliminar").attr("href", "Controlador?menu=Clientes&accion=Eliminar&id=" + id);
}


