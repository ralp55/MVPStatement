package neo.project.task.statement.service;

import neo.project.task.statement.dto.LoanOfferDto;

public interface OfferServiceInterface {
    LoanOfferDto sendSelectedOffer(LoanOfferDto offer);
}
