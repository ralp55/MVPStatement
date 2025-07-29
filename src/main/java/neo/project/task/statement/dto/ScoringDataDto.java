package neo.project.task.statement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import neo.project.task.statement.entity.Employment;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ScoringDataDto {
    @NotEmpty
    @Schema(description = "Сумма кредита", example = "500000")
    private BigDecimal amount;

    @NotEmpty
    @Schema(description = "Срок кредита (в месяцах)", example = "12")
    private Integer term;

    @NotBlank
    @Schema(description = "Имя", example = "Иван")
    private String firstName;

    @NotBlank
    @Schema(description = "Фамилия", example = "Иванов")
    private String lastName;

    @NotBlank
    @Schema(description = "Отчество", example = "Иванович")
    private String middleName;

    @NonNull
    @Schema(description = "Пол", example = "MALE")
    private Gender gender;

    @Past
    @Schema(description = "Дата рождения", example = "1990-01-01")
    private LocalDate birthdate;

    @Size(min=4, max=4)
    @Schema(description = "Серия паспорта", example = "1234")
    private String passportSeries;

    @Size(min=6, max=6)
    @Schema(description = "Номер паспорта", example = "567890")
    private String passportNumber;

    @Past
    @Schema(description = "Дата выдачи паспорта", example = "2010-05-15")
    private LocalDate passportIssueDate;

    @Size(max=255)
    @Schema(description = "Кем выдан паспорт", example = "ОВД Центрального района")
    private String passportIssueBranch;

    @NonNull
    @Schema(description = "Семейное положение", example = "MARRIED")
    private MaritalStatus maritalStatus;

    @NotEmpty
    @Schema(description = "Количество иждивенцев", example = "2")
    private Integer dependentAmount;

    @NonNull
    @Schema(description = "Информация о занятости")
    private Employment employment;

    @NotBlank
    @Schema(description = "Номер счёта клиента", example = "40817810099910004312")
    private String accountNumber;

    @NonNull
    @Schema(description = "Подключена ли страховка", example = "true")
    private Boolean isInsuranceEnabled;

    @NonNull
    @Schema(description = "Является ли зарплатным клиентом", example = "false")
    private Boolean isSalaryClient;
}

