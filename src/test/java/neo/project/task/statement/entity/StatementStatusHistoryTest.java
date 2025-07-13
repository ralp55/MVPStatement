package neo.project.task.statement.entity;

import neo.project.task.statement.entity.StatementStatusHistory;
import neo.project.task.statement.entity.Statement;
import neo.project.task.statement.dto.ApplicationStatus;
import neo.project.task.statement.dto.StatusChangeType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class StatementStatusHistoryTest {

    @Test
    void testStatementStatusHistoryGettersAndSetters() {
        StatementStatusHistory history = new StatementStatusHistory();

        Long id = 1L;
        ApplicationStatus status = ApplicationStatus.APPROVED;
        LocalDateTime time = LocalDateTime.of(2023, 5, 15, 10, 30);
        StatusChangeType changeType = StatusChangeType.AUTO;
        Statement statement = new Statement();

        history.setId_statement_status_history(id);
        history.setStatus(status);
        history.setTime(time);
        history.setChangeType(changeType);
        history.setStatement(statement);

        assertEquals(id, history.getId_statement_status_history());
        assertEquals(status, history.getStatus());
        assertEquals(time, history.getTime());
        assertEquals(changeType, history.getChangeType());
        assertEquals(statement, history.getStatement());
    }
}
