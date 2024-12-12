
const asideMenu = document.querySelector(".menu");
const menuPerfil = document.querySelector(".perfil-usuario");
const botonMenu = document.getElementById("boton-menu");
const botonPerfil = document.getElementById("boton-perfil");


botonMenu.addEventListener("click", () => {
     asideMenu.classList.toggle("cerrar-menu");
   
});

botonPerfil.addEventListener("click", () => {
menuPerfil.classList.toggle("cerrar-menu");
    

});

