package neo.project.task.statement.entity;

import neo.project.task.statement.dto.ApplicationStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StatementTest {

    @Test
    void testStatementGettersAndSetters() {
        Statement statement = new Statement();

        UUID statementId = UUID.randomUUID();
        Client client = new Client();
        UUID creditId = UUID.randomUUID();
        ApplicationStatus status = ApplicationStatus.PREAPPROVED;
        LocalDateTime creationDate = LocalDateTime.now();
        LoanOffer appliedOffer = new LoanOffer();
        LocalDateTime signDate = LocalDateTime.now();
        String sesCode = "SES123";

        List<StatementStatusHistory> statusHistory = new ArrayList<>();
        StatementStatusHistory history = new StatementStatusHistory();
        history.setStatement(statement);
        statusHistory.add(history);

        statement.setStatementId(statementId);
        statement.setClient(client);
        statement.setCreditId(creditId);
        statement.setStatus(status);
        statement.setCreationDate(creationDate);
        statement.setAppliedOffer(appliedOffer);
        statement.setSignDate(signDate);
        statement.setSesCode(sesCode);
        statement.setStatusHistory(statusHistory);

        assertEquals(statementId, statement.getStatementId());
        assertEquals(client, statement.getClient());
        assertEquals(creditId, statement.getCreditId());
        assertEquals(status, statement.getStatus());
        assertEquals(creationDate, statement.getCreationDate());
        assertEquals(appliedOffer, statement.getAppliedOffer());
        assertEquals(signDate, statement.getSignDate());
        assertEquals(sesCode, statement.getSesCode());
        assertEquals(statusHistory, statement.getStatusHistory());
        assertEquals(statement, history.getStatement());
    }
}
