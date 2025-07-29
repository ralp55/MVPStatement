package neo.project.task.statement.service;

import neo.project.task.statement.dto.LoanOfferDto;
import neo.project.task.statement.dto.LoanStatementRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${external.deal-service.base-url}")
    private String dealServiceBaseUrl;
    @Test
    void processStatementRequest_Success() {

        var request = new LoanStatementRequestDto();
        var expectedResponse = List.of(new LoanOfferDto(), new LoanOfferDto());

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(LoanStatementRequestDto.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(any(ParameterizedTypeReference.class))).thenReturn(expectedResponse);

        List<LoanOfferDto> result = statementService.processStatementRequest(request);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(restClient).post();
        verify(requestBodyUriSpec).uri(dealServiceBaseUrl + "/deal/statement");
    }

    @Test
    void processStatementRequest_WhenEmptyResponse_ReturnsEmptyList() {
        var request = new LoanStatementRequestDto();

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(LoanStatementRequestDto.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(any(ParameterizedTypeReference.class))).thenReturn(null);

        List<LoanOfferDto> result = statementService.processStatementRequest(request);

        assertNull(result);
    }

    @Test
    void processStatementRequest_WhenError_ThrowsException() {
        var request = new LoanStatementRequestDto();

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(LoanStatementRequestDto.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(any(ParameterizedTypeReference.class)))
                .thenThrow(new RuntimeException("Service unavailable"));

        assertThrows(RuntimeException.class, () -> {
            statementService.processStatementRequest(request);
        });
    }
}
