package neo.project.task.statement.service;

import neo.project.task.statement.dto.LoanOfferDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OfferServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private RestClient.RequestBodySpec requestBodySpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @InjectMocks
    private OfferService offerService;

    @Test
    void testSendSelectedOffer() {
        LoanOfferDto offer = new LoanOfferDto();
        offer.setStatementId(UUID.randomUUID());
        offer.setRequestedAmount(BigDecimal.valueOf(100000));
        offer.setRate(BigDecimal.valueOf(12.5));
        offer.setIsSalaryClient(true);
        offer.setIsInsuranceEnabled(false);

        LoanOfferDto expectedResponse = new LoanOfferDto();
        expectedResponse.setStatementId(UUID.randomUUID());
        expectedResponse.setRequestedAmount(BigDecimal.valueOf(100000));
        expectedResponse.setRate(BigDecimal.valueOf(12.5));
        expectedResponse.setIsSalaryClient(true);
        expectedResponse.setIsInsuranceEnabled(false);

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("http://localhost:8081/deal/offer/select")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(offer)).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(LoanOfferDto.class)).thenReturn(expectedResponse);

        LoanOfferDto actualResponse = offerService.sendSelectedOffer(offer);

        assertEquals(expectedResponse, actualResponse);
    }
}
