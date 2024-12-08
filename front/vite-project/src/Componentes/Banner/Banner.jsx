import styled from "styled-components";
import BotonPrincipal from "../Botones/BotonPrincipal";


const Contenedor = styled.div`
display: flex;
justify-content: center;

padding: 100px 115px;
gap: 75px;
width: 100%;
height: 39vw;
background: var(--color-background-oscuro);
`;


const Imagen = styled.img`
   width: 48vw;
   height: 26vw;
   background: linear-gradient(180deg, #eeeeee3f 0, #cdcdcd33 55%, #474747ce 92.5%);
   border-radius: 10px;
   border: none;
   text-align: center;
    
`;

const DivParrafo = styled.div`
width: 60%;
height: 20vw;
color: #eeeeee;

`;

const Titulo = styled.h2`
font-size: 2rem;
font-family: var(--font-titulo);
font-weight: lighter;
line-height: 160%;
`;

 const Parrafo = styled.p`
 font-size: 1.2rem;
 font-family: var(--font-comun);
 padding-top: 35px;
 line-height: 160%;
 margin-bottom: 40px;
 `;

const BotonGrande = styled(BotonPrincipal)`
  width: 150px;
  height: 50px;
`;

function Banner() {

    return(
       <>
       <Contenedor>
            <Imagen src="./mockup-app.png" alt="telefonos" ></Imagen>
            <DivParrafo>
                <Titulo>
                    <h2>Moneda es tu libertad <br/>financiera en un click.</h2>
                </Titulo>
                <Parrafo>
                    <p>Acceda a los beneficios  de obtener su cuenta <br/> gratuita 
                    sin filas. Maneje sus finanzas desde <br/> la comodidad de
                    su hogar.</p>
                </Parrafo>

                <BotonGrande>Solicitar cuenta</BotonGrande>
               
            </DivParrafo>
       </Contenedor>
       
       
       </>
    )

}

export default Banner