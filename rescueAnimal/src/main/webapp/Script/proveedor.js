function editarProveedor(id) {
    var codigo = $("#idProveedor_" + id).text();
    $("#codigoProveedor").attr("value", codigo);
    
    var tipoDocumento = $("#tipoDocumento_" + id).text();
    $("#tipoDocumentoProveedor").attr("value", tipoDocumento);
    
    var documento = $("#documento_" + id).text();
    $("#documentoProveedor").attr("value", documento);
    
    var nombre = $("#nombre_" + id).text();
    $("#nombreProveedor").attr("value", nombre);
    
    var direccion = $("#direccion_" + id).text();
    $("#direccionProveedor").attr("value", direccion);
    
    var telefono = $("#telefono_" + id).text();
    $("#telefonoProveedor").attr("value", telefono);
    
    var estado = $("#estado_" + id).text();
    $("#estadoProveedor").attr("value", estado);
}
function eliminarProveedor(id) {
    $("#btneliminar").attr("href", "Controlador?menu=Proveedores&accion=Eliminar&id=" + id);
}


