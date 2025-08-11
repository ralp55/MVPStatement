package neo.project.task.statement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import neo.project.task.statement.entity.Employment;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Table(name = "client")
@Schema(description = "Клиент")
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "client_id", columnDefinition = "uuid")
    @Schema(description = "ID клиента")
    private UUID clientId;

    @NotEmpty
    @Column(name = "last_name")
    @Schema(description = "Фамилия")
    private String lastName;

    @NotEmpty
    @Column(name = "first_name")
    @Schema(description = "Имя")
    private String firstName;

    @Column(name = "middle_name")
    @Schema(description = "Отчество")
    private String middleName;

    @NonNull
    @Column(name = "birth_date")
    @Schema(description = "Дата рождения")
    private LocalDate birthDate;

    @Email
    @Column(name = "email")
    @Schema(description = "Email")
    private String email;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    @Schema(description = "Пол")
    private Gender gender;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    @Schema(description = "Семейное положение")
    private MaritalStatus maritalStatus;

    @Min(1)
    @Column(name = "dependent_amount")
    @Schema(description = "Количество иждивенцев")
    private Integer dependentAmount;


    @ManyToOne
    @JoinColumn(name = "passport_id")
    @Schema(description = "Паспорт клиента")
    private PassportDto passport;

    @ManyToOne
    @JoinColumn(name = "employment_id")
    @Schema(description = "Информация о занятости")
    private Employment employment;

    @NotEmpty
    @Column(name = "account_number")
    @Schema(description = "Номер счета")
    private String accountNumber;
}