
import styled from "styled-components";


const Container = styled.div`
display: flex;
justify-content:center;
width: auto;
height: 25vw;
gap: 20px;
align-items: center;
overflow: scroll;
margin: 60px 0;
padding-left: 20vw;
`;


const Imagen = styled.img`
width: 35wv;
height: 17vw;
flex-shrink: 0;
`;

function Carrusel(){

    return(
        <>
         <Container>

            <Imagen src="./tarjeta-desktop.png" alt="tarjeta"></Imagen>
            <Imagen src="./descuentos-desktop.png"></Imagen>
            <Imagen src="./descargar-app.png"></Imagen>

         </Container>
                 
        </>
    )
}

export default Carrusel