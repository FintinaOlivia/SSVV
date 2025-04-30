package IntegrationTests;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.TemaValidator;
import validation.StudentValidator;
import validation.NotaValidator;
import validation.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncrementalIntegrationTest {

    private StudentXMLRepository studentRepo;
    private TemaXMLRepository temaRepo;
    private NotaXMLRepository notaRepo;
    private Service service;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        Path studentsFile = tempDir.resolve("students_test.xml");
        Path assignmentsFile = tempDir.resolve("assignments_test.xml");
        Path gradesFile = tempDir.resolve("grades_test.xml");

        Files.writeString(studentsFile, "<Entitati></Entitati>");
        Files.writeString(assignmentsFile, "<Entitati></Entitati>");
        Files.writeString(gradesFile, "<Entitati></Entitati>");

        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> assignmentValidator = new TemaValidator();
        Validator<Nota> gradeValidator = new NotaValidator();

        studentRepo = new StudentXMLRepository(studentValidator,studentsFile.toString());
        temaRepo = new TemaXMLRepository(assignmentValidator, assignmentsFile.toString());
        notaRepo = new NotaXMLRepository(gradeValidator,gradesFile.toString());
        service = new Service(studentRepo, temaRepo, notaRepo);
    }

    @Test
    void step1_addStudent_successfully() {
        int result = service.saveStudent("100", "Test Student", 931);
        assertEquals(1, result);
    }

    @Test
    void step2_addStudentThenAssignment_successfully() {
        service.saveStudent("101", "Test Student", 932);
        int result = service.saveTema("200", "Assignment desc", 12, 10);
        assertEquals(1, result);
    }

    @Test
    void step3_addStudentThenAssignmentThenGrade_successfully() {
        service.saveStudent("102", "Test Student", 933);
        service.saveTema("201", "Assignment desc", 11, 9);
        int result = service.saveNota("102", "201", 10, 10, "Good job");
        assertEquals(1, result);
    }
}
