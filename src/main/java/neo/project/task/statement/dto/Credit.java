package neo.project.task.statement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "credit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о кредите")
public class Credit {

    @Id
    @GeneratedValue
    @Column(name = "credit_id", columnDefinition = "uuid")
    @Schema(description = "ID кредита")
    private UUID creditId;

    @Min(1)
    @Column(name = "amount")
    @Schema(description = "Сумма кредита")
    private BigDecimal amount;

    @Min(1)
    @Column(name = "term")
    @Schema(description = "Срок кредита (в месяцах)")
    private Integer term;

    @Min(1)
    @Column(name = "monthly_payment")
    @Schema(description = "Ежемесячный платеж")
    private BigDecimal monthlyPayment;

    @Column(name = "rate")
    @Schema(description = "Процентная ставка")
    private BigDecimal rate;

    @Min(1)
    @Column(name = "psk")
    @Schema(description = "Полная стоимость кредита (ПСК)")
    private BigDecimal psk;

    @NotEmpty
    @Column(name = "payment_schedule", columnDefinition = "jsonb")
    @Schema(description = "График платежей")
    private String paymentSchedule;

    @NonNull
    @Column(name = "insurance_enabled")
    @Schema(description = "Страховка включена")
    private Boolean isInsuranceEnabled;

    @NonNull
    @Column(name = "salary_client")
    @Schema(description = "Является ли клиентом с зарплатным проектом")
    private Boolean isSalaryClient;
//поменять в бд названия
    @Enumerated(EnumType.STRING)
    @Column(name = "credit_status")
    @Schema(description = "Статус кредита")
    private CreditStatusInformation creditStatus;
}
