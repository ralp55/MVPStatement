package neo.project.task.statement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import neo.project.task.statement.dto.LoanOfferDto;
import neo.project.task.statement.service.OfferServiceInterface;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OfferController.class)
class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OfferServiceInterface offerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSelectOffer_success() throws Exception {
        LoanOfferDto inputOffer = new LoanOfferDto();
        inputOffer.setStatementId(UUID.randomUUID());
        inputOffer.setRequestedAmount(new BigDecimal("100000"));
        inputOffer.setTerm(12);

        LoanOfferDto returnedOffer = new LoanOfferDto();
        returnedOffer.setStatementId(inputOffer.getStatementId());
        returnedOffer.setRequestedAmount(new BigDecimal("100000"));
        returnedOffer.setTerm(12);

        Mockito.when(offerService.sendSelectedOffer(Mockito.any(LoanOfferDto.class)))
                .thenReturn(returnedOffer);

        mockMvc.perform(MockMvcRequestBuilders.post("/statement/select")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputOffer)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statementId").value(inputOffer.getStatementId().toString()))
                .andExpect(jsonPath("$.requestedAmount").value(100000))
                .andExpect(jsonPath("$.term").value(12));

        Mockito.verify(offerService, Mockito.times(1)).sendSelectedOffer(Mockito.any(LoanOfferDto.class));
    }
}
