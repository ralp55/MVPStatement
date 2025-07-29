package neo.project.task.statement.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.project.task.statement.dto.LoanOfferDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferService implements OfferServiceInterface {
    @Value("${external.deal-service.base-url}")
    private String dealServiceBaseUrl;
    private final RestClient restClient;

    public LoanOfferDto sendSelectedOffer(LoanOfferDto offer) {
        log.info("Sending offer to /deal/offer/select: {}", offer);

        try {
            LoanOfferDto response = restClient.post()
                    .uri(dealServiceBaseUrl + "/deal/offer/select")
                    .body(offer)
                    .retrieve()
                    /*.onStatus(HttpStatusCode::isError, ( res) -> {
                        log.warn("Error from deal service: {}", res.getStatusCode());
                        throw new RuntimeException("Deal service error: " + res.getStatusCode());
                    })*/
                    .body(LoanOfferDto.class);

            log.info("Received selected offer response: {}", response);
            return response;

        } catch (Exception ex) {
            log.error("Failed to send offer", ex);
            throw new RuntimeException("Error during offer selection", ex);
        }
    }
}
