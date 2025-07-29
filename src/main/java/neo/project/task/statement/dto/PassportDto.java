package neo.project.task.statement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Table(name = "passport")
@Schema(description = "Паспорт клиента")
public class PassportDto {

    @Id
    @GeneratedValue
    @Column(name = "pasport_id", columnDefinition = "uuid")
    @Schema(description = "ID паспорта")
    private UUID passportId;

    @NotBlank
    @Column(name = "series", nullable = false, length = 4)
    @Schema(description = "Серия паспорта")
    private String series;

    @NotBlank
    @Column(name = "number_passport", nullable = false, length = 6)
    @Schema(description = "Номер паспорта")
    private String numberPassport;

    @NotBlank
    @Size(max=255)
    @Column(name = "isuue_branch")
    @Schema(description = "Орган выдавший паспорт")
    private String issueBranch;

    @Past
    @Column(name = "issue_date")
    @Schema(description = "Дата выдачи паспорта")
    private LocalDate issueDate;
}
