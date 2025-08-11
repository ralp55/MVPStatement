package neo.project.task.statement.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String message;
}
