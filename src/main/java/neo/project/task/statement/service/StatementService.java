package neo.project.task.statement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.project.task.statement.dto.LoanStatementRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import neo.project.task.statement.dto.*;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatementService implements StatementServiceInterface {

    @Value("${external.deal-service.base-url}")
    private String dealServiceBaseUrl;
    private final RestClient restClient;

    @Override
    public List<LoanOfferDto> processStatementRequest(LoanStatementRequestDto req) {

        log.info("Pre-scoring complete. Sending request to /deal/statement");
        try {
            List<LoanOfferDto> response = restClient.post()
                    .uri(dealServiceBaseUrl + "/deal/statement")
                    .body(req)
                    .retrieve()
                    /*.onStatus(HttpStatusCode::isError, (res) -> {
                        log.warn("Error from deal service: {}", res.getStatusCode());
                        throw new RuntimeException("Deal service error: " + res.getStatusCode());
                    })*/
                    .body(new ParameterizedTypeReference<List<LoanOfferDto>>() {
                    });

            log.info("Received {} loan offers from /deal/statement", response != null ? response.size() : 0);

            return response;
        }
        catch (Exception ex) {
            log.error("Failed to send offer", ex);
            throw new RuntimeException("Error during offer selection", ex);
        }
    }
}

