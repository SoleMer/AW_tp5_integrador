"use strict"

/**
 * @description muestra la tabla realizando un GET y creando dinamicamente
 * el renderizado del contenido obtenido del JSON
 */
function mostrarTabla() {
    let url = "http://localhost:8080/venta";
    let tbody = document.getElementById("body");
    let contenedor = document.getElementById("contenedorInsert");

    fetch(url, {
        method: "GET",
        mode: "cors",
    }).then(respuesta => {
        if (respuesta.ok) {
            respuesta.json().then(json => {
                tbody.innerHTML = " ";

                for (let data of json) {
                    let tr = document.createElement("tr");
                    tbody.appendChild(tr);
                    let td1 = document.createElement("td");
                    let td2 = document.createElement("td");
                    let td3 = document.createElement("td");
                    let td4 = document.createElement("td");
                    let td5 = document.createElement("td");
                    let td6 = document.createElement("td");
                    let btnBorrar = document.createElement("button");
                    btnBorrar.innerHTML = "borrar";
                    let btnEditar = document.createElement("button");
                    btnEditar.innerHTML = "editar";
                    let id = data.venta.id;
                    btnBorrar.addEventListener("click", () => borrar(id));
                    btnEditar.addEventListener("click", () => editar(id));
                    console.log(data);
                    td1.innerText = data.venta.id;
                    td2.innerText = data.venta.clienteId;
                    td3.innerText = data.venta.fecha;
                    let list = document.createElement("ul");
                    data.detallesVentas.forEach(e => {
                        let li = document.createElement("li");
                        li.innerHTML = "producto: " +e.productoId + ", cantidad: " + e.cantidad;
                        list.appendChild(li);
                    })
                    td6.appendChild(list);
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tr.appendChild(td6);
                    tr.appendChild(td4);
                    tr.appendChild(td5);
                    td4.appendChild(btnBorrar)
                    td5.appendChild(btnEditar);
                }
            })
        } else {
            contenedor.innerHTML = "<h1>Error - Failed URL!</h1>";
        }
    }).catch(error => {
        console.log(error);
        contenedor.innerHTML = "<h1>Error - Conection Failed!</h1>";
    });
}


/**
 * @description Interacción con AJAX con la entidad de Venta
 * muestra la tabla y espera con un fetch a una petición POST de la URL
 */
function inicio() {
    let listaProductos = [];

    mostrarTabla();
    document.getElementById("añadir_producto").addEventListener("click", () => {
        let idprod = document.getElementById("idProducto").value;
        let cantidad = document.getElementById("idCantidad").value;

        listaProductos.push({
            "productoId" : parseInt(idprod),
            "cantidad" : parseInt(cantidad)
        });

        document.getElementById("informar_producto_añadido").innerHTML = imprimirLista();

    })

    function imprimirLista(){
        let lista =  "productos añadidos: <br>";

        listaProductos.forEach(e => {
            console.log(e)
            lista += "prducto: " + e.productoId + "; cantidad: " + e.cantidad + " <br>";
        })
        return lista;
    }


    document.getElementById("btnInsertProduct").addEventListener("click", ()=>{
        let cliente = document.getElementById("idCliente").value;


        let item = {
            "clienteId": cliente,
            "productos": listaProductos
        };

        console.log(item);

        let url = "http://localhost:8080/venta";
        let contenedor = document.getElementById("contenedorInsert");

        fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(item),
        })
            .then((r) => {
                if (!r.ok) {
                    contenedor.innerHTML = "No se ha podido insertar la venta, revise los datos ingresados e intente nuevamente";
                }
            })
            .then(() => {
                contenedor.innerHTML = "Se ha insertado la venta con exito";

                document.getElementById("idCliente").value = "";
                mostrarTabla();
            })
            .catch((e) => {
                console.log(e);
                contenedor.innerHTML = "No se pudo añadir";
            });
    });
}

/**
 * @description elimina una venta de la base de datos luego que se cumple
 * la promesa del método DELETE con la url correspondiente al id a eliminar
 */
function borrar(id){
    let url = "http://localhost:8080/venta";
    let contenedor = document.getElementById("contenedorBorrar");

    fetch(url + "/" + id, {
        method: "DELETE",
        mode: 'cors',
    }).then(respuesta => {
        if (respuesta.ok) {
            contenedor.innerHTML = "Se ha borrado correctamente";
            mostrarTabla();
        } else {
            contenedor.innerHTML = "No puede borrar un cliente asociado a una factura";
        }
    }).catch(error => {
        contenedor.innerHTML = "<h1>Error - Conection Failed!</h1>";
    });
}

/**
 * @description renderiza el formulario de edición de una venta,
 * y queda a la espera de una petición de PUT por la url de un id del path a editar
 */
function editar(id){

    let form = document.getElementById("editar");
    form.innerHTML = "";
    let label = document.createElement("label");
    label.innerHTML = "Editar la venta: " + id;

    let br2 = document.createElement("br");
    let input2 = document.createElement("input");
    input2.type = "text";
    input2.placeholder = "Inserte nuevo id cliente";
    input2.id = "idCliente";

    let br3 = document.createElement("br");
    let input3 = document.createElement("input");
    input3.type = "date";
    input3.placeholder = "Inserte nuevo id cliente";
    input3.id = "fecha";

    let br4 = document.createElement("br");
    let boton = document.createElement("input");
    boton.type = "button";
    boton.value = "EDITAR";
    boton.id = "enviar";

    form.appendChild(label);
    form.appendChild(br2);
    form.appendChild(input2);
    form.appendChild(br3);
    form.appendChild(input3);
    form.appendChild(br4);
    form.appendChild(boton);

    document.getElementById("enviar").addEventListener("click", () => {
        let contenedor = document.getElementById("contenedorEditar");
        let cliente = document.getElementById("idCliente").value;
        let fecha = document.getElementById("fecha").value;

        let item = {
            "id": id,
            "clienteId": cliente,
            "fecha": fecha
        };
        console.log(item)

        let url = "http://localhost:8080/venta";

        fetch(url, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(item),
        })
            .then((r) => {
                if (!r.ok) {
                    contenedor.innerHTML = "No se ha podido insertar la venta, revise los datos ingresados e intente nuevamente";
                }
            })
            .then(() => {
                contenedor.innerHTML = "Se ha editado la venta con exito";
                form.innerHTML = "";
                form.setAttribute("type", "hidden");
                mostrarTabla();
            })
            .catch((e) => {
                console.log(e);
            });
    });
}


document.addEventListener("DOMContentLoaded", inicio());