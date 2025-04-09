package IntegrationTests;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AddFeatureTest {
    @Mock
    private TemaXMLRepository temaXMLRepository;

    @Mock
    private NotaXMLRepository notaXMLRepository;

    @Mock
    private StudentXMLRepository studentXMLRepository;

    @InjectMocks
    private Service service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveAssignment_WithValidFields_OperationIsSuccessful() {
        System.out.println("saveAssignment_WithValidFields_OperationIsSuccessful");

        // Act
        var result = service.saveTema("1", "Descriere", 10, 5);

        // Assert
        assertEquals(1,result, "Expected a valid insertion");
    }

    @Test
    void saveStudent_WithValidFields_OperationIsSuccessful() {
        System.out.println("saveStudent_WithValidFields_OperationIsSuccessful");

        // Act
        var result = service.saveStudent("123", "Alice", 111);

        // Assert
        assertEquals(1,result, "Expected the entities to be equal");
    }

    @Test
    void saveGrade_WithValidFields_OperationIsSuccessful() {
        System.out.println("saveStudent_WithValidFields_OperationIsSuccessful");

        Student student = new Student("123", "Alice", 111);
        Tema assignment = new Tema("1", "descriere", 10, 5);

        when(studentXMLRepository.findOne(any(String.class))).thenReturn(student);
        when(temaXMLRepository.findOne(any(String.class))).thenReturn(assignment);

        // Act
        var result = service.saveNota("1", "1", 10, 5, "");

        // Assert
        assertEquals(1, result, "Expected the entities to be equal");
    }


    @Test
    void saveEach_WithValidFields_OperationIsSuccessful() {
        System.out.println("saveEach_WithValidFields_OperationIsSuccessful");
        saveAssignment_WithValidFields_OperationIsSuccessful();
        saveStudent_WithValidFields_OperationIsSuccessful();
        saveGrade_WithValidFields_OperationIsSuccessful();
    }
}
