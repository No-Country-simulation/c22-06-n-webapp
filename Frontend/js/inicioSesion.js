document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');

    // Expresiones regulares
    
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[com]{3}$/;
    const passwordRegex = /^\d{6}$/;

    // Validar formulario al enviar
    form.addEventListener('submit', function (event) {
        let isValid = true;

        // Limpiar mensajes previos de error
        document.querySelectorAll('.error-message').forEach(span => span.remove());

        // Validar email
        if (!emailInput.value.trim() || !emailRegex.test(emailInput.value.trim())) {
            isValid = false;
            showError(emailInput, 'El correo electrónico debe contener "@" y terminar en ".com"');
        }

        // Validar contraseña
        if (!passwordInput.value.trim() || !passwordRegex.test(passwordInput.value.trim())) {
            isValid = false;
            showError(passwordInput, 'La contraseña debe tener 6 carácteres numéricos');
        }

        if (!isValid) {
            event.preventDefault(); // Evita el envío del formulario si hay errores
        }
    });

    function showError(inputElement, message) {
        const errorSpan = document.createElement('span');
        errorSpan.textContent = message;
        errorSpan.style.color = 'red';
        errorSpan.classList.add('error-message');
        inputElement.parentElement.appendChild(errorSpan);
    }
});
