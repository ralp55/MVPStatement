package neo.project.task.statement.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import neo.project.task.statement.dto.ApplicationStatus;
import neo.project.task.statement.dto.StatusChangeType;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "statement_status_history")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StatementStatusHistory {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statement_status_history", nullable = false)
    private Long id_statement_status_history;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    @Column(name = "change_time", nullable = false)
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "change_type", nullable = false)
    private StatusChangeType changeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statement_id", nullable = false)
    private Statement statement;
}