package neo.project.task.statement.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import neo.project.task.statement.dto.EmploymentStatus;
import neo.project.task.statement.dto.Position;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employment {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "employement_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID employement_id;

    @Enumerated(EnumType.STRING)
    @Column(name="employment_status", columnDefinition = "status_information", nullable = false) // определено в бд
    private EmploymentStatus employmentStatus;

    @Column(name = "employer_inn", nullable = false)
    private String employerINN;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(name="position", columnDefinition = "position_info", nullable = false) //определено в бд
    private Position position;

    @Column(name = "work_experience_total", nullable = false)
    private Integer workExperienceTotal;

    @Column(name = "work_experience_current", nullable = false)
    private Integer workExperienceCurrent;
}

