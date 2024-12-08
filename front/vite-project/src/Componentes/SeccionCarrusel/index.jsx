
import styled from "styled-components";
import Carrusel from "./Carrusel";


const Container = styled.div`
width: 100%;
height: 50vw;
background: var(--background);
`;



const Titulo = styled.h2`
color: var(--color-titulos);
font-size: 1.8rem;
font-family: var(--font-titulos);
text-align: center;
margin-top: 100px;

`;

const Parrafo = styled.p`
color: var(--texto-comun);
font-size: 1.4rem;
text-align: center;
margin-top: 50px;
padding: 0 100px;
text-align: justify;
`; 




function ParrafoCentral() {

return(
       <>
        <Container>
            <Titulo> Moneda revoluciona tu experiencia bancaria</Titulo>
            <Parrafo>Con tu registro obtienes una cuenta de ahorros gratuita, para que empieces a disfrutar de todos los beneficios que tienen muestros clientes. Transferencias sin comisiones, descuentos exclusivos y la experiencia de pagar servicios  sin salir de casa.</Parrafo>
            <Carrusel></Carrusel>

        </Container>
              
      </>
)

}

export default ParrafoCentral