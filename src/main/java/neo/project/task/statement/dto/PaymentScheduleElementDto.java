package neo.project.task.statement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class PaymentScheduleElementDto {
    @NotEmpty
    @Positive(message = "Номер платежа должен быть положительным числом")
    @Schema(description = "Номер платежа", example = "1")
    private Integer number;

    @NonNull
    @Schema(description = "Дата платежа", example = "2025-07-01")
    private LocalDate date;

    @NotEmpty
    @DecimalMin(value = "0.0", inclusive = false, message = "Общая сумма платежа должна быть больше 0")
    @Schema(description = "Общая сумма платежа", example = "45000.00")
    private BigDecimal totalPayment;

    @NotEmpty
    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Процент по кредиту", example = "5000.00")
    private BigDecimal interestPayment;

    @NotEmpty
    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Погашение основного долга", example = "40000.00")
    private BigDecimal debtPayment;

    @NotEmpty
    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Оставшийся долг", example = "460000.00")
    private BigDecimal remainingDebt;
}
