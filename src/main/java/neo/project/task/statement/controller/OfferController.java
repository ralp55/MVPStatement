package neo.project.task.statement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.project.task.statement.dto.LoanOfferDto;
import neo.project.task.statement.service.OfferService;
import neo.project.task.statement.service.OfferServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.client.RestClient;

@Slf4j
@RestController
@RequestMapping("/statement")
@Tag(name = "Loan offer", description = "Операции с кредитными предложениями")
@RequiredArgsConstructor
public class OfferController {

    private final OfferServiceInterface offerService;

    @Operation(
            summary = "Выбор кредитного предложения",
            description = "Отправляет выбранное кредитное предложение на сервис /deal/offer/select",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешный выбор предложения",
                            content = @Content(schema = @Schema(implementation = LoanOfferDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Некорректные данные (невалидный запрос)",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Неавторизованный доступ",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещен",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ресурс не найден",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Конфликт данных",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "415",
                            description = "Неподдерживаемый тип данных",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "429",
                            description = "Слишком много запросов",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Внутренняя ошибка сервера",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "503",
                            description = "Сервис временно недоступен",
                            content = @Content
                    )
            }
    )
    @PostMapping("/select")
    public ResponseEntity<LoanOfferDto> selectOffer(@Valid @RequestBody LoanOfferDto offer) {
        LoanOfferDto selectedOffer = offerService.sendSelectedOffer(offer);
        return ResponseEntity.ok(selectedOffer);
    }
}

