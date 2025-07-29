package neo.project.task.statement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "История изменения статуса заявки")
public class StatementStatusHistoryDto {

    @NonNull
    @Schema(description = "Статус заявки")
    private ApplicationStatus status;

    @NotEmpty
    @Schema(description = "Время изменения")
    private LocalDateTime time;

    @NonNull
    @Schema(description = "Тип изменения статуса")
    private StatusChangeType changeType;
}
