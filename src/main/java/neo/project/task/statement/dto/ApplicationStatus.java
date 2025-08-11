package neo.project.task.statement.dto;

public enum ApplicationStatus{
    PREAPPROVED,
    APPROVED,
    CC_DENIED, //не знаю как расшифровать это, так было на сайте
    CC_APPROVED,
    PREPARED_DOCUMENTS,
    DOCUMENTS_CREATED,
    CLIENT_DEINIED,
    DOCUMENT_SIGNED,
    CREDIT_ISSUED
}
