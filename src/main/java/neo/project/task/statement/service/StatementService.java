package neo.project.task.statement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.project.task.statement.dto.LoanStatementRequestDto;
import org.springframework.core.ParameterizedTypeReference;
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

    private final RestClient restClient;

    @Override
    public List<LoanOfferDto> processStatementRequest(LoanStatementRequestDto req) {
        validateRequest(req);

        log.info("Pre-scoring complete. Sending request to /deal/statement");

        List<LoanOfferDto> response = restClient.post()
                .uri("http://localhost:8081/deal/statement")
                .body(req)
                .retrieve()
                .body(new ParameterizedTypeReference<List<LoanOfferDto>>() {});

        log.info("Received {} loan offers from /deal/statement", response != null ? response.size() : 0);

        return response;
    }

    private void validateRequest(LoanStatementRequestDto req) {
        log.debug("Validation start");

        if (req.getAmount() == null || req.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be positive");

        if (req.getTerm() == null || req.getTerm() <= 0)
            throw new IllegalArgumentException("Term must be positive");

        if (req.getFirstName().length() < 2 || req.getFirstName().length() > 30)
            throw new IllegalArgumentException("First name is required");

        if (req.getLastName().length() < 2 || req.getLastName().length() > 30)
            throw new IllegalArgumentException("Last name is required");

        if (req.getMiddleName() == null || req.getMiddleName().length() < 2 || req.getMiddleName().length() > 30)
            throw new IllegalArgumentException("Middle name is required");

        if (req.getEmail() == null || !req.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
            throw new IllegalArgumentException("Invalid email format");

        if (req.getBirthdate() == null || req.getBirthdate().isAfter(LocalDate.now().minusYears(18)))
            throw new IllegalArgumentException("User must be at least 18 years old");

        if (req.getPassportSeries() == null || !req.getPassportSeries().matches("\\d{4}"))
            throw new IllegalArgumentException("Passport series must be 4 digits");

        if (req.getPassportNumber() == null || !req.getPassportNumber().matches("\\d{6}"))
            throw new IllegalArgumentException("Passport number must be 6 digits");

        log.debug("Validation end");
    }
}

