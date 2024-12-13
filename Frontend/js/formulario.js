import esUnCUIT from "./validar-cuit.js";
import esMayorDeEdad from "./validar-edad.js";
import { tiposError, mensajes } from "./customErrors.js";


const camposDeFormulario = document.querySelectorAll("[required");
const formulario = document.querySelector("[data-formulario]");


formulario.addEventListener("submit", (e) => {
  e.preventDefault();
  const listaRespuestas = {
    firstName: e.target.elements["nombre"].value,
    lastName_p: e.target.elements["apellido_p"].value,
    lastName_m: e.target.elements["apellido_m"].value,
    email: e.target.elements["email"].value,
    dni: e.target.elements["identificacion"].value,
    cuil: e.target.elements["cuil"].value,
    birthDate: e.target.elements["fecha_nacimiento"].value,
    password: e.target.elements["password"].value,
    bankAccountType_id: 1 
  };
  
  window.location.href = "./abrir-cuenta-form-2.html";
});

camposDeFormulario.forEach((campo) => {
  campo.addEventListener("blur", () => verificarCampo(campo));
  campo.addEventListener("invalid", (evento) => evento.preventDefault());
});

function verificarCampo(campo) {
  let mensaje = "";
  campo.setCustomValidity("");

  if (campo.name == "cuil" && campo.value.length >= 11) {
    esUnCUIT(campo);
  }
  if (campo.name == "fecha_nacimiento" && campo.value != "") {
    esMayorDeEdad(campo);
  }
 
  //campos validity
  tiposError.forEach((error) => {
    if (campo.validity[error]) {
      mensaje = mensajes[campo.name][error];
    }
  });

  const mensajeError = campo.parentNode.querySelector(".mensaje-error");
  const validarInputCheck = campo.checkValidity();

  if (!validarInputCheck) {
    mensajeError.textContent = mensaje;
  } else {
    mensajeError.textContent = "";
  }
}
