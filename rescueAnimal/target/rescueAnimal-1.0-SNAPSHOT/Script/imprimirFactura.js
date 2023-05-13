function mostrar_impresoras() {
    connetor_plugin.obtenerImpresoras()
            .then(impresoras => {
                console.log(impresoras);
            });
}


async function imprimir() {
    let nombreImpresora = "OneNote for Windows 10";
    let api_key = "12345";


    const conector = new connetor_plugin();
    conector.fontsize("2");
    conector.textaling("center");
    conector.text("Ferreteria Angel");
    conector.fontsize("1");
    conector.text("Calle de las margaritas #45854");
    conector.text("PEC7855452SCC");
    conector.feed("3");
    conector.textaling("left");
    conector.text("Fecha: Miercoles 8 de ABRIL 2022 13:50");
    conector.text("Cant.       Descripcion      Importe");
    conector.text("------------------------------------------");
    conector.text("1- Barrote 2x4x8                    $110");
    conector.text("3- Esquinero Vinil                  $165");
    conector.feed("1");
    conector.fontsize("2");
    conector.textaling("center");
    conector.text("Total: $275");
    conector.qr("https://abrazasoft.com");
    conector.feed("5");
    conector.cut("0");

    const resp = await conector.imprimir(nombreImpresora, api_key);
    if (resp === true) {
        print();
    } else {
        console.log("Problema al imprimir: " + resp);

    }
}


