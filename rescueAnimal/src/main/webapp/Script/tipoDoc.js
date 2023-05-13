function editarTipoDoc(id) {
    var codigo = $("#idDocumento_" + id).text();
    $("#codigoTipoDoc").attr("value", codigo);
    
    var descripcion = $("#descripcion_" + id).text();
    $("#tipoDoc").attr("value", descripcion);
}
function eliminarTipoDoc(id) {
    $("#btneliminar").attr("href", "Controlador?menu=TipoDoc&accion=Eliminar&id=" + id);
}


