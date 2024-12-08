
import styled from "styled-components";
import BotonSecundario from "../Botones/BotonSecundario";
import BotonPrincipal from "../Botones/BotonPrincipal";


const HeaderEstilizado = styled.header`
  width: 100%;
  height: 80px;
  display: flex;
  justify-content: space-between;
  background: var(--color-background-oscuro);
  padding: 20px;
`;

const ListaLogo = styled.ul`
display: flex;
justify-content: left;
text-align: center;
align-items: center;
padding-left: 15px;
gap: 10px;

h2 {
  color: #ffffff;
  font-size: 1.6rem;
  font-weight: 200;
  font-family: var(--font-logo);
}

img {
    width: 70px;
    height: 70px;
    text-align: center;
    padding-top: -10px;
  }
`;

const ListaBotones = styled.ul`
display: flex;
justify-content: right;
align-items: center;
gap: 25px;
padding-right: 15px;

`;




function Head() {

    return(
      <>
       <HeaderEstilizado>
       
       <ListaLogo>
          <img src="./logo-app.png" alt="logo" />
          <h2>Moneda</h2>
        </ListaLogo>
        
        <ListaBotones>
           <BotonSecundario>Abrir cuenta</BotonSecundario>
           <BotonPrincipal>Inicia sesión</BotonPrincipal>
        </ListaBotones>

        </HeaderEstilizado> 

    </>
    )

}

export default Head