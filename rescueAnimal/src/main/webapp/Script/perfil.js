function editarPerfil(id) {
    var codigo = $("#idPerfil_" + id).text();
    $("#codigoPerfil").attr("value", codigo);
    var nombre = $("#nombre_" + id).text();
    $("#nombrePerfil").attr("value", nombre);
    var descripcion = $("#descripcion_" + id).text();
    $("#descripcionPerfil").attr("value", descripcion);
    var estado = $("#estado_" + id).text();
    $("#estadoEmpleado").attr("value", estado);
}
function eliminarPerfil(id) {
    $("#btneliminar").attr("href", "Controlador?menu=Perfiles&accion=Eliminar&id=" + id);
}


