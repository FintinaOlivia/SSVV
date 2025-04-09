package UnitTests.Repository;

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
        repository = new TemaXMLRepository(validator, "test_teme.xml");
    }

    // VALID TEST
    @Test
    void saveAssignment_WithValidFields_OperationIsSuccessful() {
        System.out.println("saveAssignment_WithValidFields_OperationIsSuccessful");

        // Arrange
        String validId = generateNewId();
        Tema tema = createValidTema(validId);

        // Act
        var result = repository.save(tema);

        // Assert
        assertNull(result, "Expected the result of a valid insertion to be null");
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
        assertEquals(result, tema, "Expected the entities to be equal");
    }

    @Test
    void saveAssignment_WithEmptyId_ThrowsValidationException() {
        System.out.println("saveAssignment_WithEmptyId_ThrowsValidationException");

        // Arrange
        Tema tema = createEmptyIdTema();

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(result, tema, "Expected the entities to be equal");
    }

    // DESCRIPTION FIELD TESTS
    @Test
    void saveAssignment_WithNullDescription_ThrowsValidationException() {
        System.out.println("saveAssignment_WithNullDescription_ThrowsValidationException");

        // Arrange
        String validId = generateNewId();
        Tema tema = createNullDescriptionTema(validId);

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(result, tema, "Expected the entities to be equal");
    }

    @Test
    void saveAssignment_WithEmptyDescription_ThrowsValidationException() {
        System.out.println("saveAssignment_WithEmptyDescription_ThrowsValidationException");

        // Arrange
        String validId = generateNewId();
        Tema tema = createEmptyDescriptionTema(validId);

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(result, tema, "Expected the entities to be equal");
    }

    // DEADLINE FIELD TESTS
    @Test
    void saveAssignment_WithDeadlineLessThan1_ThrowsValidationException() {
        System.out.println("saveAssignment_WithDeadlineLessThan1_ThrowsValidationException");

        // Arrange
        String validId = generateNewId();
        Tema tema = createDeadlineLessThan1Tema(validId);

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(result, tema, "Expected the entities to be equal");
    }

    @Test
    void saveAssignment_WithDeadlineGreaterThan14_ThrowsValidationException() {
        System.out.println("saveAssignment_WithDeadlineGreaterThan14_ThrowsValidationException");

        // Arrange
        String validId = generateNewId();
        Tema tema = createDeadlineGreaterThan14Tema(validId);

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(result, tema, "Expected the entities to be equal");
    }

    @Test
    void saveAssignment_WithDeadlineLessThanStartline_ThrowsValidationException() {
        System.out.println("saveAssignment_WithDeadlineLessThanStartline_ThrowsValidationException");

        // Arrange
        String validId = generateNewId();
        Tema tema = createDeadlineLessThanStartlineTema(validId);

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(result, tema, "Expected the entities to be equal");
    }

    // STARTLINE FIELD TESTS
    @Test
    void saveAssignment_WithStartlineLessThan1_ThrowsValidationException() {
        System.out.println("saveAssignment_WithStartlineLessThan1_ThrowsValidationException");

        // Arrange
        String validId = generateNewId();
        Tema tema = createStartlineLessThan1Tema(validId);

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(result, tema, "Expected the entities to be equal");
    }

    @Test
    void saveAssignment_WithStartlineGreaterThan14_ThrowsValidationException() {
        System.out.println("saveAssignment_WithStartlineGreaterThan14_ThrowsValidationException");

        // Arrange
        String validId = generateNewId();
        Tema tema = createStartlineGreaterThan14Tema(validId);

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(result, tema, "Expected the entities to be equal");
    }

    @Test
    void saveAssignment_WithStartlineGreaterThanDeadline_ThrowsValidationException() {
        System.out.println("saveAssignment_WithStartlineGreaterThanDeadline_ThrowsValidationException");

        // Arrange
        String validId = generateNewId();
        Tema tema = createStartlineGreaterThanDeadlineTema(validId);

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(result, tema, "Expected the entities to be equal");
    }

    @Test
    void saveAssignment_WithIdExisting_ReturnsEntityOnPutIfAbsent() {
        System.out.println("saveAssignment_WithIdExisting_ReturnsEntityOnPutIfAbsent");

        // Arrange
        String existingId = generateExistingId();
        Tema tema = createStartlineGreaterThanDeadlineTema(existingId);

        // Act
        var result = repository.save(tema);

        // Assert
        assertEquals(result, tema, "Expected the entities to be equal");
    }


    private String generateNewId() {
        Iterable<Tema> all = repository.findAll();

        StringBuilder newId = new StringBuilder();
        for(Tema t : all)
        {
            newId.append(t.getID());
        }

        if(newId.length() == 1)
            newId.append("1");

        return newId.toString();
    }

    private String generateExistingId() {
        Iterable<Tema> all = repository.findAll();
        String existingId = "";
        for(Tema t : all)
        {
            existingId = t.getID();
        }

        return existingId;
    }

    private Tema createValidTema(String id) {
        return new Tema(id, "Valid description", 10, 5);
    }

    private Tema createNullIdTema() {
        return new Tema(null, "Valid description", 10, 5);
    }

    private Tema createEmptyIdTema() {
        return new Tema("", "Valid description", 10, 5);
    }

    private Tema createNullDescriptionTema(String id) {
        return new Tema(id, null, 10, 5);
    }

    private Tema createEmptyDescriptionTema(String id) {
        return new Tema(id, "", 10, 5);
    }

    private Tema createDeadlineLessThan1Tema(String id) {
        return new Tema(id, "Valid description", 0, 5);
    }

    private Tema createDeadlineGreaterThan14Tema(String id) {
        return new Tema(id, "Valid description", 15, 5);
    }

    private Tema createDeadlineLessThanStartlineTema(String id) {
        return new Tema(id, "Valid description", 4, 5);
    }

    private Tema createStartlineLessThan1Tema(String id) {
        return new Tema(id, "Valid description", 5, 0);
    }

    private Tema createStartlineGreaterThan14Tema(String id) {
        return new Tema(id, "Valid description", 5, 15);
    }

    private Tema createStartlineGreaterThanDeadlineTema(String id) {
        return new Tema(id, "Valid description", 5, 6);
    }
}
