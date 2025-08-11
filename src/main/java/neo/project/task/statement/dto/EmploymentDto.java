package neo.project.task.statement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;


@Data
public class EmploymentDto {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID employement_id;

    @NonNull
    @Schema(description = "Статус занятости", example = "EMPLOYED")
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @NotEmpty
    @Schema(description = "ИНН работодателя", example = "1234567890")
    private String employerINN;

    @Min(-1)
    @Schema(description = "Ежемесячная зарплата", example = "100000")
    private BigDecimal salary;

    @NonNull
    @Schema(description = "Должность", example = "MID_MANAGER")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Min(0)
    @Schema(description = "Общий стаж (в месяцах)", example = "120")
    private Integer workExperienceTotal;

    @Min(0)
    @Schema(description = "Стаж на текущем месте (в месяцах)", example = "24")
    private Integer workExperienceCurrent;
}

