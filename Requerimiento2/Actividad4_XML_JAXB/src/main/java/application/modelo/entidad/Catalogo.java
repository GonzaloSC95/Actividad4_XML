package application.modelo.entidad;
//Con esta etiqueta estamos estableciendo el nombre de el nodo raiz en xml
//etiqueta obligatoria.

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "catalogo")
//Podemos hacer que las etiquetas salgan en un determinado orden
//etiqueta opcional
@XmlType(propOrder = {
    "autores",
    "editoriales",
    "librerias"
})
public class Catalogo {

    private List<Autor> autores;
    private List<Editorial> editoriales;
    private List<Libreria> librerias;

    public Catalogo() {
    }

    public Catalogo(List<Autor> autores, List<Editorial> editoriales, List<Libreria> librerias) {
        this.autores = autores;
        this.editoriales = editoriales;
        this.librerias = librerias;
    }

    //Establezco que cada elemento del array se serialice a una etiqueta xml cuyo nombre
    //sea "persona"
    @XmlElement(name = "autor")
    //Podemos crear una etiqueta que envuelva las etiquetas persona, si no la ponemos saldran
    //las etiquetas "persona" al mismo nivel que la familia, de esta manera agrupamos todos
    //los "miembro" en la etiqueta "miembros"
    @XmlElementWrapper(name = "autores")
    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    //Establezco que cada elemento del array se serialice a una etiqueta xml cuyo nombre
    //sea "persona"
    @XmlElement(name = "editorial")
    //Podemos crear una etiqueta que envuelva las etiquetas persona, si no la ponemos saldran
    //las etiquetas "persona" al mismo nivel que la familia, de esta manera agrupamos todos
    //los "miembro" en la etiqueta "miembros"
    @XmlElementWrapper(name = "editoriales")
    public List<Editorial> getEditoriales() {
        return editoriales;
    }

    public void setEditoriales(List<Editorial> editoriales) {
        this.editoriales = editoriales;
    }

    //Establezco que cada elemento del array se serialice a una etiqueta xml cuyo nombre
    //sea "persona"
    @XmlElement(name = "libreria")
    //Podemos crear una etiqueta que envuelva las etiquetas persona, si no la ponemos saldran
    //las etiquetas "persona" al mismo nivel que la familia, de esta manera agrupamos todos
    //los "miembro" en la etiqueta "miembros"
    @XmlElementWrapper(name = "librerias")
    public List<Libreria> getLibrerias() {
        return librerias;
    }

    public void setLibrerias(List<Libreria> librerias) {
        this.librerias = librerias;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "autores=" + autores + ", editoriales=" + editoriales + ", librerias=" + librerias + '}';
    }

}
