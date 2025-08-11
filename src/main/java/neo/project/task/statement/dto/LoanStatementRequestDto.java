package neo.project.task.statement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "Данные запроса на кредит")
public class LoanStatementRequestDto {
    @NotNull(message = "amount не может быть null")
    @Schema(description = "Запрашиваемая сумма кредита", example = "100000")
    private BigDecimal amount;

    @NotNull(message = "term не может быть null")
    @Schema(description = "Срок кредита в месяцах", example = "12")
    private Integer term;

    @NotBlank(message = "Имя обязательно")
    @Schema(description = "Имя", example = "Иван")
    private String firstName;

    @NotBlank(message = "Имя обязательно")
    @Schema(description = "Фамилия", example = "Иванов")
    private String lastName;

    @NotBlank(message = "Имя обязательно")
    @Schema(description = "Отчество", example = "Иванович")
    private String middleName;

    @Email(message = "Допустим только такой вид email")
    @Schema(description = "Email", example = "ivanov@example.com")
    private String email;

    @Past(message = "Дата рождения должна быть в прошлом")
    @Schema(description = "Дата рождения", example = "1985-06-15")
    private LocalDate birthdate;

    @Pattern(regexp = "\\d{4}")
    @Schema(description = "Серия паспорта", example = "1234")
    private String passportSeries;

    @Pattern(regexp = "\\d{6}")
    @Schema(description = "Номер паспорта", example = "567890")
    private String passportNumber;

}
