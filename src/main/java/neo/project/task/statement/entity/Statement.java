package neo.project.task.statement.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import neo.project.task.statement.dto.ApplicationStatus;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "statement")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Statement {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "statement_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID statementId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "credit_id", nullable = false)
    private UUID creditId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private LoanOffer appliedOffer;

    @Column(name = "sign_date", nullable = false)
    private LocalDateTime signDate;

    @Column(name = "ses_code", nullable = false)
    private String sesCode;

    @Type(JsonType.class)
    @Column(name = "status_history", columnDefinition = "jsonb", nullable = false)
    @OneToMany(mappedBy = "statement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatementStatusHistory> statusHistory = new ArrayList<>();
}

