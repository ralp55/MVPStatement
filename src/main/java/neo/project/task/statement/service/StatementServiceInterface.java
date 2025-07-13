package neo.project.task.statement.service;

import neo.project.task.statement.dto.LoanOfferDto;
import neo.project.task.statement.dto.LoanStatementRequestDto;

import java.util.List;

public interface StatementServiceInterface {
    List<LoanOfferDto> processStatementRequest(LoanStatementRequestDto request);
}
