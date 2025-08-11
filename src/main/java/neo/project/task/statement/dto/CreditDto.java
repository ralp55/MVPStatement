package neo.project.task.statement.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;

@Data
public class CreditDto {
    @Min(1)
    @Schema(description = "Сумма кредита", example = "500000.00")
    private BigDecimal amount;
    @Min(1)
    @Schema(description = "Срок кредита (в месяцах)", example = "12")
    private Integer term;
    @Min(1)
    @Schema(description = "Ежемесячный платёж", example = "45000.00")
    private BigDecimal monthlyPayment;
    @Min(1)
    @Schema(description = "Процентная ставка", example = "9.5")
    private BigDecimal rate;
    @Min(1)
    @Schema(description = "Полная стоимость кредита (ПСК)", example = "512000.00")
    private BigDecimal psk;
    @NonNull
    @Schema(description = "Страховка подключена", example = "true")
    private Boolean isInsuranceEnabled;
    @NonNull
    @Schema(description = "Зарплатный клиент", example = "false")
    private Boolean isSalaryClient;
    @NonNull
    @Schema(description = "График платежей")
    @Type(JsonBinaryType.class)
    private JsonNode paymentSchedule;

}
