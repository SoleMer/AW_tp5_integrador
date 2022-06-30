"use strict"

/**
 * @description muestra la tabla realizando un GET y creando dinamicamente
 * el renderizado del contenido obtenido del JSON
 */
function mostrarTabla() {
    let url = "http://localhost:8080/cliente";
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
                    console.log(data);
                    let tr = document.createElement("tr");
                    tbody.appendChild(tr);
                    let td1 = document.createElement("td");
                    let td2 = document.createElement("td");
                    let td3 = document.createElement("td");
                    let btnBorrar = document.createElement("button");
                    btnBorrar.innerHTML = "borrar";
                    let btnEditar = document.createElement("button");
                    btnEditar.innerHTML = "editar";
                    let id = data.id;
                    btnBorrar.addEventListener("click", () => borrar(id));
                    btnEditar.addEventListener("click", () => editar(id));
                    td1.innerText = data.nombreApellido;

                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    td2.appendChild(btnBorrar)
                    td3.appendChild(btnEditar);
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
 * @description Interacción con AJAX con la entidad de Client
 * muestra la tabla y espera con un fetch a una petición POST de la URL
 */
function inicio() {
    mostrarTabla();
    document.getElementById("btnInsertClient").addEventListener("click", ()=>{
        let name = document.getElementById("name").value;
        let item = {
            "nombreApellido": name
        };
        console.log(item);
        let url = "http://localhost:8080/cliente";
        let contenedor = document.getElementById("contenedorInsert");

        fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(item),
        })
            .then((r) => {
                if (!r.ok) {
                    contenedor.innerHTML = "No se ha podido insertar el usuario, revise los datos ingresados e intente nuevamente";
                }
            })
            .then(() => {
                contenedor.innerHTML = "Se ha insertado el usuario con exito";
                document.getElementById("name").value = "";
                mostrarTabla();
            })
            .catch((e) => {
                console.log(e);
            });
    });
}

/**
 * @description elimina un cliente de la base de datos luego que se cumple
 * la promesa del método DELETE con la url correspondiente al id a eliminar
 */
function borrar(id){
    let url = "http://localhost:8080/cliente";
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
 * @description renderiza el formulario de edición de un cliente,
 * y queda a la espera de una petición de PUT por la url de un id del path a editar
 */
function editar(id){
    let url = "http://localhost:8080/cliente";

    let form = document.getElementById("editar");
    let label = document.createElement("label");
    label.innerHTML = "Editar un cliente: ";

    let br2 = document.createElement("br");
    let input2 = document.createElement("input");
    input2.type = "text";
    input2.placeholder = "Inserte nuevo nombre";
    input2.id = "nameI";

    let br4 = document.createElement("br");
    let boton = document.createElement("input");
    boton.type = "button";
    boton.value = "EDITAR";
    boton.id = "enviar";

    form.appendChild(label);
    form.appendChild(br2);
    form.appendChild(input2);
    form.appendChild(br4);
    form.appendChild(boton);

    document.getElementById("enviar").addEventListener("click", () => {
        let contenedor = document.getElementById("contenedorEditar");


        let name = document.getElementById("nameI").value;

        let item = {
            "nombreApellido": name
        };

        let url = "http://localhost:8080/cliente";

        fetch(url + "/" + id, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(item),
        })
            .then((r) => {
                if (!r.ok) {
                    contenedor.innerHTML = "No se ha podido insertar el cliente, revise los datos ingresados e intente nuevamente";
                }
            })
            .then(() => {
                contenedor.innerHTML = "Se ha editado el cliente con exito";
                form.innerHTML = "";
                form.setAttribute("hiden");
                mostrarTabla();
            })
            .catch((e) => {
                console.log(e);
            });
    });
}

/**
 * @description evento del DOM que se ejecuta al cargar el inicio de la página
 */
document.addEventListener("DOMContentLoaded", inicio());