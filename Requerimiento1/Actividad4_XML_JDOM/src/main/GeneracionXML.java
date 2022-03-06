package main;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Gonzalo Solís Campos
 */
public class GeneracionXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //CLASES NECESARIAS PARA CONSTRUIR UN FICHERO XML
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        Document document;
        //--------------------------------------------------------------------------------
        try {
            //INSTANCIAMOS UN OBJETO DE LA CLASE DOCUMENT BUILDER
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //INSTANCIAMOS UN OBJETO DE LA CLASE DOCUMENT
            document = documentBuilder.newDocument();
            // Añadimos elementos al xml, concierto será el elemento raiz
            //--------------NODO RAIZ (CONCIERTO)---------------------------
            Element concierto = document.createElement("concierto");
            //--------------------------------------------------------
            Element fecha = document.createElement("fecha");
            Element hora = document.createElement("hora");
            Element participantes = document.createElement("participantes");
            //AGREGAMOS LOS ELEMENTOS AL DOCUEMNTO XML
            document.appendChild(concierto);
            //----ELEMENTOS HIJOS DE CONCIERTO------------
            //------------NODO FECHA-------------------
            concierto.appendChild(fecha);
            Text fecha_txt = document.createTextNode("20-Oct-2018");
            fecha.appendChild(fecha_txt);
            //----------NODO HORA--------------------------------------------------
            concierto.appendChild(hora);
            Text hora_txt = document.createTextNode("21:30");
            hora.appendChild(hora_txt);
            //-----------NODO PARTICIPANTES----------------------------------------
            concierto.appendChild(participantes);
            //--------AGREGAMOS A TODOS LOS PARTICIPANTES DEL CONCIERTO-------------------------------
            agregarElementosHijos(participantes, document);
            //----------GUARDAMOS EL DOCUMENTO XML GENERADO----------------------------
            generarXML(document);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //METODO PARA AÑADIR NODOS HIJOS A UN NODO PADRE///////////////////////////////
    public static void agregarElementosHijos(Element padre, Document document) {
        //1º PARTICIPANTE///////////////////////
        Element participante1 = document.createElement("participante");
        //-----------ENTRADA----------------------------------------
        Element entrada1 = document.createElement("entrada");
        Text entrada1_txt = document.createTextNode("21:30");
        entrada1.appendChild(entrada1_txt);
        //-----------PARTICIPANTE----------------------------------------
        Element grupo1 = document.createElement("grupo");
        Text grupo1_txt = document.createTextNode("Las Ardillas de Dakota");
        grupo1.appendChild(grupo1_txt);
        //-----------------------------------------------
        participante1.appendChild(entrada1);
        participante1.appendChild(grupo1);
        //-----------------------------------------------------
        padre.appendChild(participante1);
        ////////////////////////////////////////////////////////////
        //2º PARTICIPANTE///////////////////////
        Element participante2 = document.createElement("participante");
        //-----------ENTRADA----------------------------------------
        Element entrada2 = document.createElement("entrada");
        Text entrada2_txt = document.createTextNode("22:15");
        entrada2.appendChild(entrada2_txt);
        //-----------PARTICIPANTE----------------------------------------
        Element grupo2 = document.createElement("grupo");
        Text grupo2_txt = document.createTextNode("Fito y Fitipaldis");
        grupo2.appendChild(grupo2_txt);
        //-----------------------------------------------
        participante2.appendChild(entrada2);
        participante2.appendChild(grupo2);
        //-----------------------------------------------------
        padre.appendChild(participante2);
        ////////////////////////////////////////////////////////////
        //3º PARTICIPANTE///////////////////////
        Element participante3 = document.createElement("participante");
        //-----------ENTRADA----------------------------------------
        Element entrada3 = document.createElement("entrada");
        Text entrada3_txt = document.createTextNode("23:00");
        entrada3.appendChild(entrada3_txt);
        //-----------PARTICIPANTE----------------------------------------
        Element grupo3 = document.createElement("grupo");
        Text grupo3_txt = document.createTextNode("Coldplay");
        grupo3.appendChild(grupo3_txt);
        //-----------------------------------------------
        participante3.appendChild(entrada3);
        participante3.appendChild(grupo3);
        //-----------------------------------------------------
        padre.appendChild(participante3);

    }

    //METODO PARA GENERAR EL FICHERO.XML//////////////////////////////////
    private static void generarXML(Document doc) throws TransformerException {
        //Creamos el objeto Transfomer, que nos permitira serializar el arbol
        //dom a un fichero.
        TransformerFactory fabricaConversor = TransformerFactory.newInstance();
        Transformer conversor = fabricaConversor.newTransformer();
        //Creamos la fuente de la cual sacaremos el arbol dom.
        DOMSource fuente = new DOMSource(doc);
        //Creamos el flujo de salida, al fichero.
        StreamResult resultado = new StreamResult(new File("concierto.xml"));
        //Por ultimo, serializamos los datos.
        conversor.transform(fuente, resultado);
    }

}
