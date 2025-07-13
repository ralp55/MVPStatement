package neo.project.task.statement.service;

import neo.project.task.statement.dto.LoanOfferDto;
import neo.project.task.statement.dto.LoanStatementRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatementServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private RestClient.RequestBodySpec requestBodySpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @InjectMocks
    private StatementService statementService;

    private LoanStatementRequestDto validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new LoanStatementRequestDto();
        validRequest.setAmount(BigDecimal.valueOf(100_000));
        validRequest.setTerm(12);
        validRequest.setFirstName("Ivan");
        validRequest.setLastName("Petrov");
        validRequest.setMiddleName("Ivanovich");
        validRequest.setEmail("ivan.petrov@example.com");
        validRequest.setBirthdate(LocalDate.now().minusYears(20));
        validRequest.setPassportSeries("1234");
        validRequest.setPassportNumber("567890");
    }

    @Test
    void testProcessStatementRequest_success() {
        List<LoanOfferDto> expected = List.of(new LoanOfferDto(), new LoanOfferDto());

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("http://localhost:8081/deal/statement")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(validRequest)).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(ArgumentMatchers.<ParameterizedTypeReference<List<LoanOfferDto>>>any()))
                .thenReturn(expected);

        List<LoanOfferDto> actual = statementService.processStatementRequest(validRequest);

        assertEquals(expected.size(), actual.size());
        verify(restClient).post();
    }

    @Test
    void testValidation_negativeAmount() {
        validRequest.setAmount(BigDecimal.valueOf(-1));
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("Amount must be positive", ex.getMessage());
    }

    @Test
    void testValidation_zeroTerm() {
        validRequest.setTerm(0);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("Term must be positive", ex.getMessage());
    }

    @Test
    void testValidation_shortFirstName() {
        validRequest.setFirstName("I");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("First name is required", ex.getMessage());
    }

    @Test
    void testValidation_longFirstName() {
        validRequest.setFirstName("A".repeat(31));
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("First name is required", ex.getMessage());
    }

    @Test
    void testValidation_invalidLastName() {
        validRequest.setLastName("Z");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("Last name is required", ex.getMessage());
    }

    @Test
    void testValidation_nullMiddleName() {
        validRequest.setMiddleName(null);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("Middle name is required", ex.getMessage());
    }

    @Test
    void testValidation_shortMiddleName() {
        validRequest.setMiddleName("A");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("Middle name is required", ex.getMessage());
    }

    @Test
    void testValidation_invalidEmail() {
        validRequest.setEmail("invalid-email");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("Invalid email format", ex.getMessage());
    }

    @Test
    void testValidation_tooYoung() {
        validRequest.setBirthdate(LocalDate.now().minusYears(17));
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("User must be at least 18 years old", ex.getMessage());
    }

    @Test
    void testValidation_invalidPassportSeries() {
        validRequest.setPassportSeries("12AB");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("Passport series must be 4 digits", ex.getMessage());
    }

    @Test
    void testValidation_invalidPassportNumber() {
        validRequest.setPassportNumber("ABC123");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> statementService.processStatementRequest(validRequest));
        assertEquals("Passport number must be 6 digits", ex.getMessage());
    }
}
