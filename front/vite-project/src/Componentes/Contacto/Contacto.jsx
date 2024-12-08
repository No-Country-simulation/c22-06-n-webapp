import styled from "styled-components";


const Container = styled.div`
width: 85%;
height: 29vw;
display: flex;
justify-content: space-between;
align-items: center;
gap: 3vw;
background: var(--color-tarjetas);
border: 2px solid var(--color-texto-gris);
border-radius: 10px;
box-shadow: var(--box-shadow);
padding: 30px;
text-align: center;
margin: 0 auto 5vw auto;
`;

const Imagen = styled.img`
width: 40%;
height: 24vw;
border-radius: 10px;
box-shadow: var(--box-shadow);
`;

const Titulo = styled.h3`
color: var(--color-titulos);
font-size: 1.3rem;
font-family: var(--font-titulos);
`;

const Datos = styled.div`
width: 50%;
height: 20vw;
display: flex;
align-items: center;
justify-content: center;
gap: 7px;


p {
    font-size: 1rem;
    font-family: var(--font-comun);
    padding: 15px 20px;
    background: var(--color-hover);
    border-radius: 7px;
}

;`



function Contacto(){
    
    return(
        <>
        <Container>
            <Imagen src="./contacto.png"></Imagen>
            <Titulo>Conoce nuestros canales de consulta</Titulo>
            <Datos>
            <p>0800-345-223</p>

            </Datos>

        </Container>
            
        </>
    )

}

export default Contacto