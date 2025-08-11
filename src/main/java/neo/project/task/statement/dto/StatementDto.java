package neo.project.task.statement.dto;

import com.vladmihalcea.hibernate.type.json.JsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "statement")
@Schema(description = "Заявка")
public class StatementDto {

    @Id
    @GeneratedValue
    @Schema(description = "ID заявки")
    private UUID statementId;

    @NotNull(message = "Информация о клиенте обязательна")
    @Schema(description = "Клиент")
    private Client client;

    @NotNull(message = "ID кредита обязательно")
    @Schema(description = "ID кредита")
    private UUID creditId;

    @NotNull(message = "Статус заявки обязателен")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Текущий статус заявки")
    private ApplicationStatus status;

    @NotNull(message = "Дата создания обязательна")
    @PastOrPresent(message = "Дата создания должна быть в прошлом или настоящем")
    @Schema(description = "Дата создания заявки")
    private LocalDateTime creationDate;

    @NotNull(message = "Кредитное предложение обязательно")
    @Type(JsonType.class)
    @Schema(description = "Выбранное кредитное предложение")
    private LoanOfferDto appliedOffer;

    @Schema(description = "Дата подписания")
    private LocalDateTime signDate;

    @NotBlank(message = "СЭС-код не может быть пустым")
    @Size(min = 6, max = 50, message = "СЭС-код должен быть от 6 до 50 символов")
    @Schema(description = "СЭС-код")
    private String sesCode;

    @NotNull(message = "История статусов обязательна")
    @Type(JsonType.class)
    @Schema(description = "История изменения статусов")
    private List<StatementStatusHistoryDto> statusHistory;
}

