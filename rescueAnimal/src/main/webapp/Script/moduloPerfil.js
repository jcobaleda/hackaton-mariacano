function editarModPer(id) {
    var codigo = $("#id_" + id).text();
    $("#codigoModPer").attr("value", codigo);
    var modulo = $("#modulo_" + id).text();
    $("#nombreModulo").attr("value", modulo);
    var perfil = $("#perfil_" + id).text();
    $("#nombrePerfil").attr("value", perfil);
}
function eliminarModPer(id) {
    $("#btneliminar").attr("href", "Controlador?menu=ModulePerfil&accion=Eliminar&id=" + id);
}