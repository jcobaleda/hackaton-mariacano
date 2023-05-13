/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.rescue.config;

/**
 *
 * @author Daniel Velmonto
 */
public class DatabaseConstants {
    
    /**
     * Constantes con consultas para Empleado
     */
    public static final String SQL_VALIDA_EMPLEADO = "select empleado.Documento, empleado.idempleado, empleado.Nombres, empleado.Telefono, empleado.User, empleado.Email, e.Nombre, td.Descripcion, per.NombrePerfil, per.IdPerfil from empleado inner join estados e on empleado.IdEstado = e.id inner join tipos_documentos td on empleado.IdTipoDocumento = td.id inner join perfiles per on per.IdPerfil = empleado.IdPerfil where empleado.User = ? and empleado.clave= ?";
    public static final String SQL_LISTAR_EMPLEADO = "select empleado.clave, empleado.Documento, empleado.idempleado, empleado.Nombres, empleado.Telefono, empleado.User, empleado.Email, e.Nombre, td.Descripcion, per.NombrePerfil, per.IdPerfil from empleado inner join estados e on empleado.IdEstado = e.id inner join tipos_documentos td on empleado.IdTipoDocumento = td.id inner join perfiles per on per.IdPerfil = empleado.IdPerfil";
    public static final String SQL_AGREGAR_EMPLEADO = "INSERT INTO EMPLEADO(IDTIPODOCUMENTO,DOCUMENTO,NOMBRES,TELEFONO,IDESTADO,USER,EMAIL,IDPERFIL,CLAVE) values(?,?,?,?,?,?,?,?,?)";
    public static final String SQL_ACTUALIZAR_EMPLEADO = "UPDATE EMPLEADO SET IDTIPODOCUMENTO = ?, DOCUMENTO = ?, NOMBRES = ?, TELEFONO = ?, IDESTADO = ?, USER = ?, EMAIL = ?, IDPERFIL = ?, CLAVE = ? WHERE IDEMPLEADO = ?";
    public static final String SQL_ELIMINAR_EMPLEADO = "DELETE FROM EMPLEADO WHERE IdEmpleado = ?";
    public static final String SQL_LISTAR_EMPLEADO_POR_ID = "SELECT * FROM EMPLEADO WHERE IDEMPLEADO = ?";
    
    /**
     * Constantes con consultas para Cliente
     */
    public static final String SQL_BUSCAR_CLIENTE = "SELECT c.IdCliente, c.Documento, c.Nombres, c.Direccion, e.Nombre, td.Descripcion FROM CLIENTE c inner join estados e on c.IdEstado = e.id inner join tipos_documentos td on c.IdTipoDocumento = td.id where c.Documento = ?";
    public static final String SQL_LISTAR_CLIENTE = "SELECT c.IdCliente, c.Documento, c.Nombres, c.Direccion, e.Nombre, td.Descripcion FROM CLIENTE c inner join estados e on c.IdEstado = e.id inner join tipos_documentos td on c.IdTipoDocumento = td.id where e.id = 1";
    public static final String SQL_AGREGAR_CLIENTE = "INSERT INTO CLIENTE(IDTIPODOCUMENTO,DOCUMENTO,NOMBRES,DIRECCION,IDESTADO) VALUES(?,?,?,?,?)";
    public static final String SQL_ACTUALIZAR_CLIENTE = "UPDATE CLIENTE SET IDTIPODOCUMENTO = ?, DOCUMENTO = ?, NOMBRES = ?, DIRECCION = ?, IDESTADO = ? WHERE IDCLIENTE = ?";
    public static final String SQL_ELIMINAR_CLIENTE = "DELETE FROM CLIENTE WHERE IDCLIENTE = ?";
    public static final String SQL_LISTAR_CLIENTE_POR_ID = "SELECT * FROM CLIENTE WHERE IdCliente = ?";
    
    /**
     * Constantes con consultas para Producto
     */
    public static final String SQL_BUSCAR_PRODUCTO = "SELECT * FROM PRODUCTO WHERE IDPRODUCTO = ?";
    public static final String SQL_LISTAR_PRODUCTO = "select p.IdProducto, p.Nombres,p.Stock,p.Precio, p.codProducto, e.Nombre from producto p inner join estados e on p.IdEstado = e.id where e.id = 1";
    public static final String SQL_AGREGAR_PRODUCTO = "INSERT INTO PRODUCTO(NOMBRES,PRECIO,STOCK,IDESTADO,CODPRODUCTO) VALUES(?,?,?,?,?)";
    public static final String SQL_ACTUALIZAR_PRODUCTO = "UPDATE PRODUCTO SET NOMBRES = ?, PRECIO = ?, STOCK = ?, IDESTADO = ?, CODPRODUCTO = ? WHERE IDPRODUCTO= ?";
    public static final String SQL_ELIMINAR_PRODUCTO = "DELETE FROM PRODUCTO WHERE IDPRODUCTO = ?";
    public static final String SQL_LISTAR_PRODUCTO_POR_ID = "select p.IdProducto, p.Nombres,p.Stock,p.Precio, p.codProducto, e.Nombre from producto p inner join estados e on p.IdEstado = e.id where p.codProducto = ?";
    public static final String SQL_UPDATE_STOCK = "UPDATE PRODUCTO SET STOCK = ? WHERE IDPRODUCTO = ?";
    
    /**
     * Constantes con consultas para Venta
     */
    public static final String SQL_NUM_FACTURA = "SELECT MAX(NumeroSerie) as numSerie FROM VENTAS";
    public static final String SQL_MAX_ID_VENTAS = "SELECT MAX(IDVENTAS) as idVenta FROM VENTAS";
    public static final String SQL_INSERT_VENTAS = "INSERT INTO VENTAS(IDCLIENTE,IDEMPLEADO,NUMEROSERIE,FECHAVENTAS,MONTO,IDESTADO,IDMEDIOPAGO) VALUES(?,?,?,?,?,?,?)";
    public static final String SQL_INSERT_DETALLE_VENTAS = "INSERT INTO DETALLE_VENTAS(IDVENTAS,IDPRODUCTO,CANTIDAD,PRECIOVENTA) VALUES (?,?,?,?)";
    
    /**
     * Constantes con consultas para Proveedor
     */
    public static final String SQL_LISTAR_PROVEEDOR = "SELECT p.idProveedor, p.NumeroDocumento, p.Nombre, p.Telefono, p.Direccion, td.Descripcion, e.Nombre as estado from proveedor p inner join tipos_documentos td on p.IdTipoDocumento = td.id inner join estados e on p.IdEstado = e.id";
    public static final String SQL_AGREGAR_PROVEEDOR = "INSERT INTO PROVEEDOR(IDTIPODOCUMENTO,NUMERODOCUMENTO,NOMBRE,DIRECCION,TELEFONO,IDESTADO) VALUES(?,?,?,?,?,?)";
    public static final String SQL_ELIMINAR_PROVEEDOR = "DELETE FROM PROVEEDOR WHERE IDPROVEEDOR = ?";
    public static final String SQL_LISTAR_PROVEEDOR_POR_ID = "SELECT * FROM PROVEEDOR WHERE IDPROVEEDOR = ?";
    public static final String SQL_ACTUALIZAR_PROVEEDOR = "UPDATE PROVEEDOR SET IDTIPODOCUMENTO = ?, NUMERODOCUMENTO = ?, NOMBRE = ?, DIRECCION = ?, TELEFONO = ?, IDESTADO = ? WHERE IDPROVEEDOR = ?";
    
    /**
     * Constantes con consultas para tipos de documento
     */
    public static final String SQL_LISTAR_TIPODOC = "SELECT * FROM TIPOS_DOCUMENTOS";
    public static final String SQL_AGREGAR_TIPODOC = "INSERT INTO TIPOS_DOCUMENTOS(DESCRIPCION) VALUES(?)";
    public static final String SQL_ELIMINAR_TIPODOC = "DELETE FROM TIPOS_DOCUMENTOS WHERE ID = ?";
    public static final String SQL_ACTUALIZAR_TIPODOC = "UPDATE TIPOS_DOCUMENTOS SET DESCRIPCION = ? WHERE ID = ?";
    
    /**
     * Constantes con consultas para estados
     */
    public static final String SQL_LISTAR_ESTADOS = "SELECT * FROM ESTADOS";
    
    /**
     * Constantes con consultas para perfiles
     */
    public static final String SQL_LISTAR_PERFILES = "SELECT P.IdPerfil, P.NombrePerfil, P.Descripcion, E.Nombre FROM PERFILES P INNER JOIN estados e on P.IdEstado = e.id";
    public static final String SQL_AGREGAR_PERFIL = "INSERT INTO PERFILES(NOMBREPERFIL,DESCRIPCION,IDESTADO) VALUES(?,?,?)";
    public static final String SQL_ELIMINAR_PERFIL = "DELETE FROM PERFILES WHERE IDPERFIL = ?";
    public static final String SQL_ACTUALIZAR_PERFIL = "UPDATE PERFILES SET NOMBREPERFIL = ?, DESCRIPCION = ?, IDESTADO = ? WHERE IDPERFIL = ?";
    
    /**
     * Constantes con consultas para modulos por perfil
     */
    public static final String SQL_LISTAR_MODULOS_BY_PERFIL = "select m.Nombre, m.Url, m.icono from modules_by_perfil mp inner join modulos m on mp.IdModulo = m.IdModulo inner join perfiles p on mp.IdPerfil = p.IdPerfil where mp.IdPerfil = ? and m.IdEstado = 1";
    public static final String SQL_LISTAR_MODULOS = "SELECT M.IdModulo, M.Nombre, M.Url,E.Nombre AS Estado FROM modulos M INNER JOIN estados e on M.IdEstado = e.id where M.IdEstado = 1";
    public static final String SQL_LISTAR_MODULES_BY_ROLE = "select mp.id, m.IdModulo, m.Nombre, m.Url, p.IdPerfil, p.NombrePerfil, p.Descripcion from modules_by_perfil mp inner join modulos m on mp.IdModulo = m.IdModulo inner join perfiles p on mp.IdPerfil = p.IdPerfil where m.IdEstado = 1";
    public static final String SQL_AGREGAR_MODULO_PERFIL = "insert into modules_by_perfil(IdModulo, IdPerfil) values(?,?)";
    public static final String SQL_ELIMINAR_MODULO_BY_PERFIL = "DELETE FROM MODULES_BY_PERFIL WHERE ID = ?";
    public static final String SQL_ACTUALIZAR_MODULO_BY_PERFIL = "UPDATE MODULES_BY_PERFIL SET IDMODULO = ?, IDPERFIL = ? WHERE ID = ?";
    
    /**
     * Constantes con consultas para Medio de pago
     */
    public static String SQL_LISTAR_MEDIOSPAGO = "select mp.id, mp.Nombre_Mp, e.Nombre from medios_pago mp inner join estados e on mp.idEstado = e.id";
    public static String SQL_AGREGAR_MEDIOPAGO = "insert into medios_pago(Nombre_Mp, idEstado) values(?,?)";
    public static String SQL_ELIMINAR_MEDIOPAGO = "DELETE FROM MEDIOS_PAGO WHERE ID = ?";
    public static String SQL_ACTUALIZAR_MEDIOPAGO = "UPDATE MEDIOS_PAGO SET NOMBRE_MP = ?, IDESTADO = ? WHERE ID = ?";
    
    /**
     * Constantes para el cierre de caja
     */
    public static String SQL_TOTAL_VENTAS_DIA = "select sum(monto) as total from ventas where FechaVentas = ?";
    public static String SQL_TOTAL_VENTAS_MEDIOPAGO = "select sum(v.monto) as total from ventas v inner join medios_pago mp on v.idMedioPago = mp.id where mp.Nombre_Mp = ? and v.FechaVentas = ?";
    
    /**
     * Constantes con consultas para mascotas
     */
   public static String SQL_LISTAR_MASCOTAS = "select m.codigo, m.nombre, m.edad, m.raza, m.tamano, m.catidad_vida, m.tipo_agua, m.mascota, e.Nombre estado from mascotas m inner join estados e on m.estado_id = e.id;";
   public static String SQL_AGREGAR_MASCOTA = "insert into mascotas(nombre, edad, raza, tamano, catidad_vida, tipo_agua, mascota, estado_id) values(?,?,?,?,?,?,?,?)";
}