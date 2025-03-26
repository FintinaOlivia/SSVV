package Repository;

import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TemaXMLRepository;
import validation.TemaValidator;
import validation.Validator;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AddAssignmentRepositoryTests {
    private TemaXMLRepository repository;

    @BeforeEach
    void setUp() throws IOException {
        File file = new File("test_teme.xml");
        System.out.println("Exists? " + file.exists());

        Validator<Tema> validator = new TemaValidator();
        repository = new TemaXMLRepository(validator, "test.xml");
    }

    // VALID TEST
    @Test
    void saveAssignment_WithValidFields_OperationIsSuccessful() {
        System.out.println("saveAssignment_WithValidFields_OperationIsSuccessful");

        // Arrange
        Tema tema = createValidTema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(tema, result, "Expected the entities to be equal");
    }

    // ID FIELD TESTS
    @Test
    void saveAssignment_WithNullId_ThrowsValidationException() {
        System.out.println("saveAssignment_WithNullId_ThrowsValidationException");

        // Arrange
        Tema tema = createNullIdTema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the entities to be null");
    }

    @Test
    void saveAssignment_WithEmptyId_ThrowsValidationException() {
        System.out.println("saveAssignment_WithEmptyId_ThrowsValidationException");

        // Arrange
        Tema tema = createEmptyIdTema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the entities to be null");
    }

    // DESCRIPTION FIELD TESTS
    @Test
    void saveAssignment_WithNullDescription_ThrowsValidationException() {
        System.out.println("saveAssignment_WithNullDescription_ThrowsValidationException");

        // Arrange
        Tema tema = createNullDescriptionTema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the entities to be null");
    }

    @Test
    void saveAssignment_WithEmptyDescription_ThrowsValidationException() {
        System.out.println("saveAssignment_WithEmptyDescription_ThrowsValidationException");

        // Arrange
        Tema tema = createEmptyDescriptionTema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the entities to be null");
    }

    // DEADLINE FIELD TESTS
    @Test
    void saveAssignment_WithDeadlineLessThan1_ThrowsValidationException() {
        System.out.println("saveAssignment_WithDeadlineLessThan1_ThrowsValidationException");

        // Arrange
        Tema tema = createDeadlineLessThan1Tema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the entities to be null");
    }

    @Test
    void saveAssignment_WithDeadlineGreaterThan14_ThrowsValidationException() {
        System.out.println("saveAssignment_WithDeadlineGreaterThan14_ThrowsValidationException");

        // Arrange
        Tema tema = createDeadlineGreaterThan14Tema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the entities to be null");
    }

    @Test
    void saveAssignment_WithDeadlineLessThanStartline_ThrowsValidationException() {
        System.out.println("saveAssignment_WithDeadlineLessThanStartline_ThrowsValidationException");

        // Arrange
        Tema tema = createDeadlineLessThanStartlineTema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the entities to be null");
    }

    // STARTLINE FIELD TESTS
    @Test
    void saveAssignment_WithStartlineLessThan1_ThrowsValidationException() {
        System.out.println("saveAssignment_WithStartlineLessThan1_ThrowsValidationException");

        // Arrange
        Tema tema = createStartlineLessThan1Tema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the entities to be null");
    }

    @Test
    void saveAssignment_WithStartlineGreaterThan14_ThrowsValidationException() {
        System.out.println("saveAssignment_WithStartlineGreaterThan14_ThrowsValidationException");

        // Arrange
        Tema tema = createStartlineGreaterThan14Tema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the entities to be null");
    }

    @Test
    void saveAssignment_WithStartlineGreaterThanDeadline_ThrowsValidationException() {
        System.out.println("saveAssignment_WithStartlineGreaterThanDeadline_ThrowsValidationException");

        // Arrange
        Tema tema = createStartlineGreaterThanDeadlineTema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the entities to be null");
    }

//    SETUP

    private Tema createValidTema() {
        return new Tema("1", "Valid description", 10, 5);
    }

    private Tema createNullIdTema() {
        return new Tema(null, "Valid description", 10, 5);
    }

    private Tema createEmptyIdTema() {
        return new Tema("", "Valid description", 10, 5);
    }

    private Tema createNullDescriptionTema() {
        return new Tema("1", null, 10, 5);
    }

    private Tema createEmptyDescriptionTema() {
        return new Tema("1", "", 10, 5);
    }

    private Tema createDeadlineLessThan1Tema() {
        return new Tema("1", "Valid description", 0, 5);
    }

    private Tema createDeadlineGreaterThan14Tema() {
        return new Tema("1", "Valid description", 15, 5);
    }

    private Tema createDeadlineLessThanStartlineTema() {
        return new Tema("1", "Valid description", 4, 5);
    }

    private Tema createStartlineLessThan1Tema() {
        return new Tema("1", "Valid description", 5, 0);
    }

    private Tema createStartlineGreaterThan14Tema() {
        return new Tema("1", "Valid description", 5, 15);
    }

    private Tema createStartlineGreaterThanDeadlineTema() {
        return new Tema("1", "Valid description", 5, 6);
    }
}
