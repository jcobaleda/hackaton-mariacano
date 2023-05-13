function editarMedPag(id) {
    var codigo = $("#id_" + id).text();
    $("#codigoMedPag").attr("value", codigo);
    var modulo = $("#medioPago_" + id).text();
    $("#medioPago").attr("value", modulo);
    var estado = $("#estado_" + id).text();
    $("#estadoMP").attr("value", estado);
}
function eliminarMedPag(id) {
    $("#btneliminar").attr("href", "Controlador?menu=MedioPago&accion=Eliminar&id=" + id);
}


