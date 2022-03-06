package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Gonzalo Solís Campos
 */
public class LecturaXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeneracionXML.main(args);
        //-------------------------------------------------
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        Document document;
        Node raiz;

        try {
            String fichero = "";
            //---------------------------------
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse("concierto.xml");
            raiz = document.getDocumentElement();
            //-------------------------------------------
            System.out.println(pintarFechayHora(raiz));
            fichero = fichero + pintarFechayHora(raiz) + "\n";
            //----------------------------------------------
            System.out.println("Participan los siguientes grupos:");
            fichero = fichero + "Participan los siguientes grupos:" + "\n";
            //-----------------------------------------------------
            System.out.println(pintarParticipantes(raiz));
            fichero = fichero + pintarParticipantes(raiz);
            //----------------------------------------------------
            guaradarFichetoTxt(fichero);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static String pintarFechayHora(Node raiz) {
        String fecha_hora = "";
        //---------------------------------------------
        NodeList nodos = raiz.getChildNodes();
        //---¡OJO!-----------
        //Los saltos de linea que hay entre las etiquetas se cuentan.
        //como nodos texto.
        for (int i = 0; i < nodos.getLength(); i++) {
            // Iteración por los elementos de CONCIERTO.
            Node nodoHijo = nodos.item(i);
            if (nodoHijo.getNodeName().equals("fecha")) {
                fecha_hora = fecha_hora + "Fecha y hora del concierto: " + nodoHijo.getTextContent();
            }
            if (nodoHijo.getNodeName().equals("hora")) {
                fecha_hora = fecha_hora + " a las " + nodoHijo.getTextContent() + ".";
            }
            //Otra manera si no conocemos el nombre del nodo.
            /*if (nodoHijo.getNodeType() == Node.ELEMENT_NODE) {
                //Accedemos al nodo 1 ya que el 0 es un texto (otra vez por el tema de los saltos de linea)
                //esto no se deberia hacer asi, si no que habria que 
                //volver a recorrer todos los nodos hijos, y solo pintar
                //los que sean nodo elemento.*/
        }
        return fecha_hora;
    }

    private static String pintarParticipantes(Node raiz) {
        String p = "";
        String retorno = "";
        //---------------------------
        ArrayList<String> participantesList = new ArrayList<>();
        //---------------------------------------------
        NodeList nodos = raiz.getChildNodes();
        NodeList participantes = null;
        NodeList participante = null;
        //--------------------------------------------------------------
        //Obtenemos la lista de participantes
        for (int i = 0; i < nodos.getLength(); i++) {
            // --------------------------------
            if (nodos.item(i).getNodeName().equals("participantes")) {
                participantes = nodos.item(i).getChildNodes();
            }
        }
        //-------------------------------------------------------
        //Obtenemos a los participantes
        for (int i = 0; i < participantes.getLength(); i++) {
            //--------------------------------------
            if (participantes.item(i).getNodeName().equals("participante")) {
                participante = participantes.item(i).getChildNodes();
                for (int j = 0; j < participante.getLength(); j++) {
                    if (participante.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        if (participante.item(j).getNodeName().equals("entrada")) {
                            p = p + "- " + participante.item(j).getTextContent() + " ";
                        }
                        if (participante.item(j).getNodeName().equals("grupo")) {
                            p = p + participante.item(j).getTextContent() + ".";
                        }
                    }
                }
                participantesList.add(p);
                p = "";
            }
        }
        for (String part : participantesList) {
            retorno = retorno + part + "\n";
        }
        return retorno;
    }

    private static void guaradarFichetoTxt(String string) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("concierto.txt")));
        bw.write(string);
        bw.close();
    }
}
