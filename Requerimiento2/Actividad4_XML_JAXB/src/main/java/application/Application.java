package application;

import application.modelo.entidad.Autor;
import application.modelo.entidad.Catalogo;
import application.modelo.entidad.Editorial;
import application.modelo.entidad.Libreria;
import application.modelo.entidad.Libro;
import application.modelo.persistencia.Controlador_XML;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Application.class, args);
        ///////////////////////////////////////////////////
        Controlador_XML controlador_XML = context.getBean("controlador_XML", Controlador_XML.class);
        /////////////////////////////////////LIBROS//////////////////////////////////
        Libro libro1 = new Libro("Todo se desmorona");
        Libro libro2 = new Libro("Me alegraria de otra muerte");
        Libro libro3 = new Libro("La flecha del dios");
        Libro libro4 = new Libro("Termiteros de la sabana");
        Libro libro5 = new Libro("La capsa de contes");
        Libro libro6 = new Libro("El patito feo");
        Libro libro7 = new Libro("Pulgarcita");
        Libro libro8 = new Libro("Cuentos de Andersen");
        Libro libro9 = new Libro("Microlitos");
        ////////--------------------------------------------------------------------
        List libros1 = new ArrayList();
        libros1.add(libro1);
        libros1.add(libro2);
        libros1.add(libro3);
        List libros2 = new ArrayList();
        libros2.add(libro4);
        libros2.add(libro5);
        libros2.add(libro6);
        List libros3 = new ArrayList();
        libros3.add(libro7);
        libros3.add(libro8);
        libros3.add(libro9);
        //////////////////////////EDITORIALES//////////////////////////////////////////////////////////////////////
        Editorial editorial1 = new Editorial("Alianza Editorial", "Ramon", libros1);
        Editorial editorial2 = new Editorial("Fandogamia Editorial", "Gonzalo", libros2);
        Editorial editorial3 = new Editorial("Planeta Comic", "Juan", libros3);
        ///////////////////////////////LIBRERIAS/////////////////////////////////////
        Libreria libreria1 = new Libreria("2Pajaros", "Madrid", libros1);
        Libreria libreria2 = new Libreria("Abarzuza", "Roma", libros2);
        Libreria libreria3 = new Libreria("Acuarel xxx", "Paris", libros3);
        ////////////////AUTORES//////////////////
        Autor autor1 = new Autor("Chinua Achebe", 37, libros1);
        Autor autor2 = new Autor("Hans Christian Andersen", 50, libros2);
        Autor autor3 = new Autor("Paul Celan", 69, libros3);
        //////////////////////////////////////////////////////////////////////////
        List autores = new ArrayList();
        autores.add(autor1);
        autores.add(autor2);
        autores.add(autor3);
        List editoriales = new ArrayList();
        editoriales.add(editorial1);
        editoriales.add(editorial2);
        editoriales.add(editorial3);
        List librerias = new ArrayList();
        librerias.add(libreria1);
        librerias.add(libreria2);
        librerias.add(libreria3);
        ////////////////////////////////CATALOGO//////////////////////////////////
        Catalogo catalogo = new Catalogo(autores, editoriales, librerias);
        ////////////////////////////////////////////////////////////////////////
        System.out.println("\n");
        controlador_XML.catalogoToXMl(catalogo);
        System.out.println("////////////////////////////////////////");

        System.out.println("\n");
        Catalogo c = controlador_XML.XMLToCatalogo();
        System.out.println("////////////////////////////////////////");
        System.out.println("MUESTRA DEL TITULO DE TODOS LOS LIBROS DEL CATALOGO");
        System.out.println("////////////////////////////////////////");
        ///////////////////////////////////
        for (Autor a : c.getAutores()) {
            System.out.println("\n////AUTOR ---> "+a.getNombre());
            for (Libro libro : a.getLibros()) {
                System.out.println(libro);
            }
            System.out.println("////////////\n");
        }
        ///////////////////////////////////
        for (Editorial e : c.getEditoriales()) {
            System.out.println("\n////EDITORIAL ---> "+e.getNombre());
            for (Libro libro : e.getLibros()) {
                System.out.println(libro);
            }
            System.out.println("////////////\n");
        }
        ///////////////////////////////////
        for (Libreria l : c.getLibrerias()) {
            System.out.println("\n////LIBRERIA ---> "+l.getNombre());
            for (Libro libro : l.getLibros()) {
                System.out.println(libro);
            }
            System.out.println("////////////\n");
        }

        System.out.println("////////////////FIN DEL PROGRAMA////////////////////////\n");

    }

}
