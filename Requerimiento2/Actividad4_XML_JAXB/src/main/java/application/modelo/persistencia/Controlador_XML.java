package application.modelo.persistencia;

import application.modelo.entidad.Catalogo;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

@Component
public class Controlador_XML {

    ////JABX/////////////////
    /*Obtiene el contexto asociado a la clase Persona, 
    con dichocontexto podremos convertir el objeto a un xml y a la inversa.
    Provoca una excepción de tipo JAXBException si la clase Persona
    no cumple los requisitos para la conversión a XML, es decir,
    contener las anotaciones necesarias y no cuenta con un constructor 
    sin argumentos.*/
    private JAXBContext contexto;
    /*Obtiene el objeto Marshaller asociado al contexto.
    Con dicho objeto podremos convertir un objeto en xml
    es decir, lo serializaremos*/
    private Marshaller marshaller;
    //Esta vez creamos un objeto que nos permite pasar
    //de XML a Object, es decir deserializar
    private Unmarshaller unmarshaller;
    /////////////////////////

    public Controlador_XML() {
        System.out.println("\n////////////////////////////////////////////////////////");
        System.out.println("GENERADOR DE XML CARGADO EN EL CONTEXTO DE SPRING");
        System.out.println("////////////////////////////////////////////////////////\n");
    }

    //-------------------------------------------------------------------------------
    public void catalogoToXMl(Catalogo catalogo) {
        try {
            contexto = JAXBContext.newInstance(Catalogo.class);
            marshaller = contexto.createMarshaller();
            /*stablecer la propiedad JAXB_FORMATTED_OUTPUT con el valor true 
            permite que en la conversión a formato XML se incluyan retornos 
            de carro e indentación (sangrado del texto).*/
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Convertimos un objeto a xml y lo imprimimos por pantalla
            System.out.println("///////////////////////////////////");
            System.out.println("FICHERO CATALOGO.XML GENERADO");
            System.out.println("///////////////////////////////////");
            marshaller.marshal(catalogo, System.out);
            //tambien podemos crear un fichero
            marshaller.marshal(catalogo, new File("catalogo.xml"));

        } catch (Exception e) {
            System.err.println("Error al convertir un objeto catalogo a xml");
            System.err.println(e.getLocalizedMessage());
        }
    }

    //------------------------DE XML A OBJETOS----------------------------------------------------------------
     public Catalogo XMLToCatalogo() {
         try {
            contexto = JAXBContext.newInstance(Catalogo.class);
            //Esta vez creamos un objeto que nos permite pasar
            //de XML a Object, es decir deserializar
            unmarshaller = contexto.createUnmarshaller();
            File fichero = new File("catalogo.xml");
            if (fichero.exists()) {
                Catalogo catalogo = (Catalogo) unmarshaller.unmarshal(fichero);
                return catalogo;
            } else {
                System.err.println("Fichero XML catalogo.xml no encontrado");
                return null;
            }
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    

}
