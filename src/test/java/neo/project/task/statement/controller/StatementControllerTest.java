package neo.project.task.statement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import neo.project.task.statement.dto.LoanOfferDto;
import neo.project.task.statement.dto.LoanStatementRequestDto;
import neo.project.task.statement.service.OfferServiceInterface;
import neo.project.task.statement.service.StatementServiceInterface;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StatementController.class)
class StatementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatementServiceInterface statementService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetLoanOffers_success() throws Exception {
        LoanStatementRequestDto requestDto = new LoanStatementRequestDto();
        requestDto.setAmount(new BigDecimal("100000"));
        requestDto.setTerm(12);
        requestDto.setFirstName("John");
        requestDto.setLastName("Doe");
        requestDto.setMiddleName("Smith");
        requestDto.setEmail("john.doe@example.com");
        requestDto.setBirthdate(LocalDate.of(1990, 1, 1));
        requestDto.setPassportSeries("1234");
        requestDto.setPassportNumber("123456");

        List<LoanOfferDto> mockResponse = List.of(
                createLoanOffer(UUID.randomUUID(), new BigDecimal("100000"), 12),
                createLoanOffer(UUID.randomUUID(), new BigDecimal("150000"), 24)
        );
        Mockito.when(statementService.processStatementRequest(Mockito.any(LoanStatementRequestDto.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/statement/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(mockResponse.size()))
                .andExpect(jsonPath("$[0].requestedAmount").value(new BigDecimal("100000")))
                .andExpect(jsonPath("$[1].requestedAmount").value(new BigDecimal(150000)));

        Mockito.verify(statementService, Mockito.times(1)).processStatementRequest(Mockito.any(LoanStatementRequestDto.class));
    }

    private LoanOfferDto createLoanOffer(UUID id, BigDecimal amount, int term) {
        LoanOfferDto offer = new LoanOfferDto();
        offer.setStatementId(id);
        offer.setRequestedAmount(amount);
        offer.setTerm(term);
        return offer;
    }
}

