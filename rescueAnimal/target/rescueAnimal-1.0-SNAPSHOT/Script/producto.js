function editarProducto(id) {
    var idPrd = $("#id_" + id).text();
    $("#idProducto").attr("value", idPrd);
    var nombre = $("#nombre_" + id).text();
    $("#nombreProducto").attr("value", nombre);
    var precio = $("#precio_" + id).text();
    $("#precioProducto").attr("value", precio);
    var stock = $("#stock_" + id).text();
    $("#stockProducto").attr("value", stock);
    var estado = $("#estado_" + id).text();
    $("#estadoProducto").attr("value", estado);
    var codigo = $("#cod_" + id).text();
    $("#codigoProducto").attr("value", codigo);
}
function eliminarProducto(id) {
    $("#btneliminar").attr("href", "Controlador?menu=Producto&accion=Eliminar&id=" + id);
}


