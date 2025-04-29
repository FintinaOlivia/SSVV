

import console.*;
import domain.*;
import repository.*;
import service.*;
import validation.*;

public class Main {
    public static void main(String[] args) {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
//
//        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
//        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
//        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        StudentFileRepository fileRepository1 = new StudentFileRepository(studentValidator, "studenti.txt");
        TemaFileRepository fileRepository2 = new TemaFileRepository(temaValidator, "teme.txt");
        NotaFileRepository fileRepository3 = new NotaFileRepository(notaValidator, "note.txt");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        UI consola = new UI(service);
        consola.run();

        //PENTRU GUI
        // de avut un check: daca profesorul introduce sau nu saptamana la timp
        // daca se introduce nota la timp, se preia saptamana din sistem
        // altfel, se introduce de la tastatura
    }
}
