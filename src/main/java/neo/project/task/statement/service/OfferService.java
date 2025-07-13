package neo.project.task.statement.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.project.task.statement.dto.LoanOfferDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Slf4j
@Service
@RequiredArgsConstructor
public class OfferService implements OfferServiceInterface {

    private final RestClient restClient;

    public LoanOfferDto sendSelectedOffer(LoanOfferDto offer) {
        log.info("Sending offer to /deal/offer/select: {}", offer);

        LoanOfferDto response = restClient.post()
                .uri("http://localhost:8081/deal/offer/select")
                .body(offer)
                .retrieve()
                .body(LoanOfferDto.class);

        log.info("Received selected offer response: {}", response);

        return response;
    }
}
