package Repository;

import static org.junit.jupiter.api.Assertions.*;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentXMLRepository;
import validation.StudentValidator;
import validation.Validator;

class StudentServiceTest {
    private StudentXMLRepository repository;

    @BeforeEach
    void setUp() {
        Validator<Student> validator = new StudentValidator();
        repository = new StudentXMLRepository(validator, "test_grades.xml");
    }

    @Test
    void saveStudent_WithValidFields_OperationIsSuccessful() {
        System.out.println("saveStudent_WithValidFields_OperationIsSuccessful");

        // Arrange
        Student student = createValidStudent();

        // Act
        var result = repository.save(student);

        // Assert
        assertEquals(student, result, "Expected the entities to be equal");
    }

    //    NAME FIELD VALIDATION TESTING
    @Test
    void saveStudent_WithNullName_ReturnsNull() {
        System.out.println("saveStudent_WithNullName_ReturnsNull");

        // Arrange
        Student student = createNullNameStudent();

        // Act
        var result = repository.save(student);

        // Assert
        assertNull(result, "Expected the name to be non-null");
    }

    @Test
    void saveStudent_WithEInvalidName_ReturnsNull() {
        System.out.println("saveStudent_WithEInvalidName_ReturnsNull");

        // Arrange
        Student student = createInvalidNameStudent();

        // Act
        var result = repository.save(student);

        // Assert
        assertNull(result, "Expected the name to be non-null");
    }

    //    GROUP FIELD VALIDATION TESTING
    @Test
    void saveStudent_WithInvalidLesserGroup_ReturnsNull() {
        System.out.println("saveStudent_WithLesserInvalidGroup_ReturnsNull");

        // Arrange
        Student student = createInvalidLesserGroupStudent();

        // Act
        var result = repository.save(student);

        // Assert
        assertNull(result, "Expected the entity to be invalidated");
    }

    @Test
    void saveStudent_WithInvalidGreaterGroup_ReturnsNull() {
        System.out.println("saveStudent_WithInvalidGreaterGroup_ReturnsNull");

        // Arrange
        Student student = createInvalidGreaterGroupStudent();

        // Act
        var result = repository.save(student);

        // Assert
        assertNull(result, "Expected the entity to be invalidated");
    }

    //    ID FIELD VALIDATION TESTING
    @Test
    void saveStudent_WithNullId_ReturnsNull() {
        System.out.println("saveStudent_WithNullId_ReturnsNull");

        // Arrange
        Student student = createNullIdStudent();

        // Act
        var result = repository.save(student);

        // Assert
        assertNull(result, "Expected the id to be non-null");
    }

    @Test
    void saveStudent_WithInvalidId_ReturnsNull() {
        System.out.println("saveStudent_WithInvalidId_ReturnsNull");

        // Arrange
        Student student = createInvalidIdStudent();

        // Act
        var result = repository.save(student);

        // Assert
        assertNull(result, "Expected the entity to be invalidated");
    }

    //  SETUP
    private Student createValidStudent() {
        return new Student("123", "Alice", 111);
    }

    private Student createInvalidIdStudent() {
        return new Student("", "Alice", 111);
    }

    private Student createNullIdStudent() {
        return new Student(null, "Alice", 111);
    }

    private Student createInvalidNameStudent() {
        return new Student("456", "", 900);
    }

    private Student createNullNameStudent() {
        return new Student("456", null, 900);
    }

    private Student createInvalidLesserGroupStudent() {
        return new Student("789", "Carla", 100);
    }

    private Student createInvalidGreaterGroupStudent() {
        return new Student("789", "Carla", 1000);
    }
}
