package Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.TemaXMLRepository;
import service.Service;

public class AddAssignmentServiceTests {
    @Mock
    private TemaXMLRepository temaXMLRepository;

    @InjectMocks
    private Service service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveAssignment_SuccessfulSave_Returns1() {
        // Arrange
        when(temaXMLRepository.save(any(Tema.class))).thenReturn(null); //fails because we force it to by making it return "null" as the exception

        // Act
        int result = service.saveTema("100", "descriere", 10, 5);

        // Assert
        assertEquals(1, result, "Expected save to return 1 on succes");
    }

    @Test
    void saveStudent_FailSave_Returns0() {
        // Arrange
        Tema assignment = new Tema("100", "descriere", 10, 5);
        when(temaXMLRepository.save(any(Tema.class))).thenReturn(assignment);

        // Act
        int result = service.saveTema("100", "descriere", 10, 5);

        // Assert
        assertEquals(0, result, "Expected save to return 0 on fail");
    }
}
