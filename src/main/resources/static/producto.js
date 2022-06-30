"use strict"


/**
 * @description muestra la tabla realizando un GET y creando dinamicamente
 * el renderizado del contenido obtenido del JSON
 */
function mostrarTabla() {
    let url = "http://localhost:8080/producto";
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
                    let td6 =  document.createElement("td");
                    let btnBorrar = document.createElement("button");
                    btnBorrar.innerHTML = "borrar";
                    let btnEditar = document.createElement("button");
                    btnEditar.innerHTML = "editar";
                    let id = data.id;
                    btnBorrar.addEventListener("click", () => borrar(id));
                    btnEditar.addEventListener("click", () => editar(id));
                    td1.innerText = data.id;
                    td2.innerText = data.nombre;
                    td3.innerText = data.precio;
                    td6.innerText = data.stock;
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
 * @description Interacción con AJAX con la entidad de Product
 * muestra la tabla y espera con un fetch a una petición POST de la URL
 */
function inicio() {

    mostrarTabla();
    document.getElementById("btnInsertProduct").addEventListener("click", ()=>{
        let nombre = document.getElementById("name").value;
        let precio = document.getElementById("unitPrice").value;
        let stock = document.getElementById("stock").value;

        let item = {
            "nombre": nombre,
            "stock": stock,
            "precio": precio
        };

        console.log(item);

        let url = "http://localhost:8080/producto";
        let contenedor = document.getElementById("contenedorInsert");

        fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(item),
        })
            .then((r) => {
                if (!r.ok) {
                    contenedor.innerHTML = "No se ha podido insertar el producto, revise los datos ingresados e intente nuevamente";
                }
            })
            .then(() => {
                contenedor.innerHTML = "Se ha insertado el producto con exito";
                document.getElementById("name").value = "";
                document.getElementById("unitPrice").value = "";
                document.getElementById("stock").value = "";
                mostrarTabla();
            })
            .catch((e) => {
                console.log(e);
            });
    });
}


/**
 * @description elimina un producto de la base de datos luego que se cumple
 * la promesa del método DELETE con la url correspondiente al id a eliminar
 */
function borrar(id){
    console.log(id);
    let url = "http://localhost:8080/producto";
    let contenedor = document.getElementById("contenedorBorrar");

    fetch(url + "/" + id, {
        method: "DELETE",
        mode: 'cors',
    }).then(respuesta => {
        if (respuesta.ok) {
            contenedor.innerHTML = "Se ha borrado correctamente";
            mostrarTabla();
        } else {
            contenedor.innerHTML = "No puede borrar un producto asociado a una factura";
        }
    }).catch(error => {
        console.log(error);
        contenedor.innerHTML = "<h1>Error - Conection Failed!</h1>";
    });
}



/**
 * @description renderiza el formulario de edición de un producto,
 * y queda a la espera de una petición de PUT por la url de un id del path a editar
 */
function editar(id){
    let url = "http://localhost:8080/producto";
    let form = document.getElementById("editar");
    let label = document.createElement("label");
    label.innerHTML = "Editar un producto: ";

    let br1 = document.createElement("br");
    let input1 = document.createElement("input");
    input1.type = "text";
    input1.value = id;
    input1.id = "idI";
    input1.readOnly = "true";

    let br2 = document.createElement("br");
    let input2 = document.createElement("input");
    input2.type = "text";
    input2.placeholder = "Inserte nuevo nombre";
    input2.id = "nameI";

    let br3 = document.createElement("br");
    let input3 = document.createElement("input");
    input3.type = "text";
    input3.placeholder = "Inserte nuevo valor de unidad";
    input3.id = "unitPriceI";

    let br4 = document.createElement("br");
    let input4 = document.createElement("input");
    input4.type = "text";
    input4.placeholder = "Inserte nuevo valor de unidad";
    input4.id = "stock";

    let br5 = document.createElement("br");
    let boton = document.createElement("input");
    boton.type = "button";
    boton.value = "EDITAR";
    boton.id = "enviar";

    form.appendChild(label);
    form.appendChild(br1);
    form.appendChild(input1);
    form.appendChild(br2);
    form.appendChild(input2);
    form.appendChild(br3);
    form.appendChild(input3);
    form.appendChild(br4);
    form.appendChild(input4);
    form.appendChild(br5);
    form.appendChild(boton);

    document.getElementById("enviar").addEventListener("click", () => {
        let contenedor = document.getElementById("contenedorEditar");

        let idProducto = id;
        let name = document.getElementById("nameI").value;
        let unitPrice = document.getElementById("unitPriceI").value;
        let stock = document.getElementById("stock").value;

        let item = {
            "id": idProducto,
            "nombre": name,
            "precio": unitPrice,
            "stock": stock
        };
        let url = "http://localhost:8080/producto";

        fetch(url + "/" + idProducto, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(item),
        })
            .then((r) => {
                if (!r.ok) {
                    contenedor.innerHTML = "No se ha podido insertar el producto, revise los datos ingresados e intente nuevamente";
                }
            })
            .then(() => {
                contenedor.innerHTML = "Se ha editado el producto con exito";
                form.innerHTML = "";
                mostrarTabla();
            })
            .catch((e) => {
                console.log(e);
            });
    });
}


document.addEventListener("DOMContentLoaded", inicio());