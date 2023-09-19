let inputNombre = document.getElementById("validarNombre");
let inputApellido = document.getElementById("validarApellido");
let anchor = document.getElementById("salida");

const linkURL = "/saludame/";

function crearLink() {
    anchor.setAttribute('href', linkURL + inputNombre.value + "-" + inputApellido.value);
}

document.querySelectorAll("input").forEach(input => input.addEventListener("input", crearLink));