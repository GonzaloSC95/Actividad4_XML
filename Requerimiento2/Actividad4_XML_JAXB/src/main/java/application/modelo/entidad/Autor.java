package application.modelo.entidad;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Gonzalo Solís Campos
 */
//Con esta etiqueta estamos estableciendo el nombre de el nodo raiz en xml
//etiqueta obligatoria.
@XmlRootElement(name = "autor")
//Podemos hacer que las etiquetas salgan en un determinado orden
//etiqueta opcional
@XmlType(propOrder = {
    "id",
    "nombre",
    "edad",
    "libros"
})
public class Autor {

    private static int contador=1;
    private int id;
    private String nombre;
    private int edad;
    private List<Libro> libros;

    //JAXB necesita para funcionar del constructor por defecto de java
    public Autor() {
        libros = new ArrayList<>();
        this.id=contador++;
    }

    public Autor(String nombre, int edad, List<Libro> libros) {
        this.nombre = nombre;
        this.edad = edad;
        this.libros = libros;
        this.id=contador++;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    //Establezco que cada elemento del array se serialice a una etiqueta xml cuyo nombre
    //sea "persona"
    @XmlElement(name = "libro")
    //Podemos crear una etiqueta que envuelva las etiquetas persona, si no la ponemos saldran
    //las etiquetas "persona" al mismo nivel que la familia, de esta manera agrupamos todos
    //los "miembro" en la etiqueta "miembros"
    @XmlElementWrapper(name = "libros")
    public List<Libro> getLibros() {
        return libros;
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

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", libros=" + libros + '}';
    }

}
