
document.addEventListener("DOMContentLoaded", () => {
  const formulario = document.querySelector(".principal__formulario");

  const mostrarError = (input, mensaje) => {
    const errorSpan = input.nextElementSibling;
    errorSpan.textContent = mensaje;
    errorSpan.classList.add("activo");
    input.classList.add("input-error");
  };

  const limpiarError = (input) => {
    const errorSpan = input.nextElementSibling;
    errorSpan.textContent = "";
    errorSpan.classList.remove("activo");
    input.classList.remove("input-error");
  };

  const validarFormulario = () => {
    let esValido = true;

    formulario.querySelectorAll("input").forEach((input) => {
      limpiarError(input);

      if (input.hasAttribute("required") && !input.value.trim()) {
        mostrarError(input, "Este campo es obligatorio.");
        esValido = false;
        return;
      }

      if (input.hasAttribute("minlength")) {
        const minLength = input.getAttribute("minlength");
        if (input.value.trim().length < minLength) {
          mostrarError(input, `Debe tener al menos ${minLength} caracteres.`);
          esValido = false;
          return;
        }
      }

      if (input.hasAttribute("pattern")) {
        const pattern = new RegExp(input.getAttribute("pattern"));
        if (!pattern.test(input.value.trim())) {
          mostrarError(input, "Formato inválido.");
          esValido = false;
          return;
        }
      }
    });

    return esValido;
  };

  formulario.addEventListener("submit", async (event) => {
    event.preventDefault();

    if (!validarFormulario()) {
      return;
    }

    const formData = new FormData(formulario);
    const data = Object.fromEntries(formData.entries());

    try {
      const response = await fetch("https://c22-06-n-webapp.onrender.com/api/user/save", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      if (response.ok) {
        const result = await response.json();
        alert("Datos enviados correctamente: " + JSON.stringify(result));
        formulario.reset();
      } else {
        const error = await response.json();
        alert("Error al enviar los datos: " + JSON.stringify(error));
      }
    } catch (error) {
      console.error("Error de red:", error);
      alert("Hubo un problema al enviar los datos. Intenta nuevamente.");
    }
  });
});


       