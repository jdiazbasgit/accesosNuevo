class Estado {
    constructor(id, descripcion, tipo) {
        this.id = id;
        this.descripcion = descripcion
        this.tipo = tipo;
    }
}

class Empleado {
    constructor(id, nombre, apellidos, dni, identificador, fecha_alta, fecha_baja, jornada) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.identificador = identificador;
        this.fecha_alta = fecha_alta;
        this.fecha_baja = fecha_baja;
        this.jornada = jornada;
    }
}

class Jornada {
    constructor(id, lunes, martes, miercoles, jueves, viernes, descripcion, especial) {
        this.id = id;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.descripcion = descripcion;
        this.especial = especial;
    }
}

class Calendario {
    constructor(id, fecha, estado) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
    }
}

class Usuario_estados {
    constructor(id, empleado, estado, calendario, jornada) {
        this.id = id;
        this.empleado = empleado;
        this.estado = estado;
        this.calendario = calendario;
        this.jornada = jornada;
    }
}

class Registro {
    constructor(idEmpleado, idJornada, idEstado, idCalendario) {
        this.idEmpleado = idEmpleado;
        this.idJornada = idJornada;
        this.idEstado = idEstado;
        this.idCalendario = idCalendario;
    }
}

function obtenerDatos(url) {
    return new Promise(function(resolve, reject) {
        var headers = new Headers();
        headers.append("Content-Type", "application/json");
        fetch(url, { "headers": headers }).then(response => response.json())
            .then(data => { return resolve(data) });
    })
}

function grabarDatos(url, dato) {
    return new Promise(function(resolve, reject) {
        var headers = new Headers();
        headers.append("Content-Type", "application/json");
        fetch(url, {
                "headers": headers,
                "method": "POST",
                "body": JSON.stringify(dato)
            }).then(response => response.json())
            .then(data => { return resolve(data) });
    })
}

function borrarDatos(url, dato) {
    return new Promise(function(resolve, reject) {
        var headers = new Headers();
        headers.append("Content-Type", "application/json");
        fetch(url, {
            "headers": headers,
            "method": "POST",
            "body": JSON.stringify(dato)
        }).then(response => response);
    })
}

function cargarEstados() {
    obtenerDatos("/api/estados/").then(function(estados) {

        var componente = document.querySelector("#estados");

        estados.forEach(function(estado) {
            var option = document.createElement("option");
            option.value = estado.id;
            option.text = estado.descripcion;
            componente.appendChild(option)
        })

    })
}

function cargarEmpleados() {
    obtenerDatos("/api/empleados/").then(function(empleados) {

        var componente = document.querySelector("#empleados");

        empleados.forEach(x => {
            var option = document.createElement("option");
            option.value = x.id;
            option.text = `${x.apellidos}, ${x.nombre}`;
            componente.appendChild(option);
        })

    })
}

function cargarJornadas() {
    obtenerDatos("/api/jornadas/").then(function(jornadas) {

        var componente = document.querySelector("#jornadas");

        jornadas.forEach(x => {
            var option = document.createElement("option");
            option.value = x.id;
            option.text = x.descripcion;
            componente.appendChild(option);
        })

    })
}

function cargarCalendarios() {
    obtenerDatos("/api/calendario/").then(function(calendarios) {

        var componente = document.querySelector("#calendarios");

        calendarios.forEach(x => {
            var option = document.createElement("option");
            option.value = x.id;
            option.text = x.fecha;
            componente.appendChild(option);
        })

    })
}

function modificarBt(idEmpleado, idJornada, idEstado, idCalendario) {
    document.getElementById("empleados").value = idEmpleado;
    document.getElementById("jornadas").value = idJornada;
    document.getElementById("estados").value = idEstado;
    document.getElementById("calendarios").value = idCalendario;
}

function borrarBt(idRegistro) {
    borrarDatos("/api/usuarios-estados/borrar-registro", new Usuario_estados(idRegistro)).then(function() {});
}

function addLineaATabla(ue) {
    var componente = document.querySelector("#mi-table");

    var tr = document.createElement("tr");

    var tdUsuario = document.createElement("td");
    tdUsuario.appendChild(document.createTextNode(`${ue.empleado.apellidos}, ${ue.empleado.nombre}`))
    tr.appendChild(tdUsuario)

    var tdJornada = document.createElement("td");
    tdJornada.appendChild(document.createTextNode(ue.jornada.descripcion))
    tr.appendChild(tdJornada);

    var tdEstado = document.createElement("td");
    tdEstado.appendChild(document.createTextNode(ue.estado.descripcion))
    tr.appendChild(tdEstado)

    var tdCalendario = document.createElement("td");
    tdCalendario.appendChild(document.createTextNode(ue.calendario.fecha))
    tr.appendChild(tdCalendario)

    var tdBotones = document.createElement("td");
    tdBotones.id = "mi-td";

    var btModificar = document.createElement("button")
    btModificar.type = "button";
    btModificar.classList.add("btn")
    btModificar.classList.add("btn-primary")
    btModificar.textContent = "Modificar"
    btModificar.onclick = function() {
        modificarBt(ue.empleado.id, ue.jornada.id, ue.estado.id, ue.calendario.id);
    }
    tdBotones.appendChild(btModificar)

    var btBorrar = document.createElement("button")
    btBorrar.type = "button"
    btBorrar.classList.add("btn")
    btBorrar.classList.add("btn-danger")
    btBorrar.textContent = "Borrar"
    btBorrar.onclick = function() {
        borrarBt(ue.id);
        componente.removeChild(tr);
    }
    tdBotones.appendChild(btBorrar)

    tr.appendChild(tdBotones);

    componente.appendChild(tr);
}

function cargarUsuarioEstados() {
    obtenerDatos("/api/usuarios-estados/").then(function(usuarioEstados) {

        usuarioEstados.forEach(x => {
            addLineaATabla(x)
        })

    })
}

function borrarTabla() {
    var tabla = document.querySelector("#mi-table");
    while (tabla.childElementCount > 1) {
        tabla.removeChild(tabla.lastChild);
    }
}

function guardarRegistro() {
    var listaEmpleados = document.getElementById("empleados")
    var empleadoSelected = listaEmpleados.options[listaEmpleados.selectedIndex].value;

    var listaEstados = document.getElementById("estados")
    var estadoSelected = listaEstados.options[listaEstados.selectedIndex].value;

    var listaJornadas = document.getElementById("jornadas")
    var jornadaSelected = listaJornadas.options[listaJornadas.selectedIndex].value;

    var listaCalendarios = document.getElementById("calendarios")
    var calendarioSelected = listaCalendarios.options[listaCalendarios.selectedIndex].value;

    if (calendarioSelected !== "empty" &&
        empleadoSelected !== "empty" &&
        jornadaSelected !== "empty" &&
        estadoSelected !== "empty") {

        var registro = new Registro(empleadoSelected, jornadaSelected, estadoSelected, calendarioSelected)

        grabarDatos("api/usuarios-estados/guardar-registro", registro).then(function(x) {

            if (x.id == 0) {
                alert("El registro no se ha guardado")
            } else {
                borrarTabla();
                cargarUsuarioEstados();
            }
        })
    } else {
        alert("Por favor selecciona correctamente todos los campos")
    }

}

window.onload = function() {
    cargarEmpleados();
    cargarJornadas();
    cargarEstados();
    cargarCalendarios();
    cargarUsuarioEstados();
}

function muestraDatosCargados() {
    console.log(empleados)
    console.log(jornadas)
    console.log(estados)
    console.log(calendarios)
    console.log(usuarioEstados)
}