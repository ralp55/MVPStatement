package neo.project.task.statement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.project.task.statement.dto.LoanOfferDto;
import neo.project.task.statement.dto.LoanStatementRequestDto;
import neo.project.task.statement.service.StatementServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/statement")
@Tag(name = "Statement API", description = "API для рассчета возможных условий")
@RequiredArgsConstructor
public class StatementController {
    private final StatementServiceInterface statementService;
    @Operation(
            summary = "Получение кредитных предложений",
            description = "Вычисляет список возможных кредитных предложений по параметрам клиента.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешный ответ",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = LoanOfferDto.class)))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Некорректные входные данные",
                            content = @Content
                    )
            }
    )
    @PostMapping("/offers")
    public ResponseEntity<List<LoanOfferDto>> getLoanOffers(@RequestBody LoanStatementRequestDto request) {
        log.info("Received loan request: {}", request);
        List<LoanOfferDto> offers = statementService.processStatementRequest(request);
        log.info("Successfully generated {} loan offers", offers.size());
        return ResponseEntity.ok(offers);
    }
}
