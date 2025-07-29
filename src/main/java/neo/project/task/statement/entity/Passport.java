package neo.project.task.statement.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "passport")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Passport {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "pasport_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID passportId;

    @Column(name = "series", nullable = false, length = 4)
    private String series;

    @Column(name = "number_passport", nullable = false, length = 6)
    private String numberPassport;

    @Column(name = "isuue_branch", nullable = false)
    private String issueBranch;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;
}
