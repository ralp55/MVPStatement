package neo.project.task.statement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;
import neo.project.task.statement.entity.Employment;

import java.time.LocalDate;

@Data
@Schema(description = "Запрос на завершение регистрации")
public class FinishRegistrationRequestDto {
    @NotNull
    @Schema(description = "Пол")
    private Gender gender;

    @NotNull
    @Schema(description = "Семейное положение")
    private MaritalStatus maritalStatus;

    @Min(0)
    @Schema(description = "Количество иждивенцев")
    private Integer dependentAmount;

    @Past
    @Schema(description = "Дата выдачи паспорта")
    private LocalDate passportIssueDate;

    @Size(max = 255)
    @Schema(description = "Кем выдан паспорт")
    private String passportIssueBranch;

    @Schema(description = "Информация о занятости")
    private EmploymentDto employment;

    @NotBlank
    @Schema(description = "Номер счета")
    private String accountNumber;
}