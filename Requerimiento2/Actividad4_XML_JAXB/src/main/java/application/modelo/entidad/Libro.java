package application.modelo.entidad;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Gonzalo Solís Campos
 */
//Con esta etiqueta estamos estableciendo el nombre de el nodo raiz en xml
//etiqueta obligatoria.
@XmlRootElement(name = "libro")
//Podemos hacer que las etiquetas salgan en un determinado orden
//etiqueta opcional
@XmlType(propOrder = {
    "id",
    "titulo"
})
public class Libro {

    private static int contador=1;
    private int id;
    private String titulo;

    //JAXB necesita para funcionar del constructor por defecto de java
    public Libro() {
        this.id=contador++;
    }

    public Libro(String titulo) {
        this.titulo = titulo;
        this.id=contador++;
    }

    //etiqueta que hace que el id de la persona se serialize como atributo de persona
    //etiqueta opcional
    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //etiqueta opcional para cambiar el nombre del nodo
    //@XmlElement(name="apellidos")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + '}';
    }
    
    

}
