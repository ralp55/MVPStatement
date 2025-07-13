package neo.project.task.statement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionHandlerTest {

    private final ControllerExceptionHandler handler = new ControllerExceptionHandler();

    @Test
    void handleRuntimeException_returnsNotFound() {
        RuntimeException ex = new RuntimeException("Runtime error");
        ResponseEntity<Map<String, String>> response = handler.handleRuntimeException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Runtime error", response.getBody().get("error"));
    }

    @Test
    void handleRestClientException_returnsServiceUnavailable() {
        RestClientException ex = new RestClientException("Rest client failed");
        ResponseEntity<Map<String, String>> response = handler.handleRestClientException(ex);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("Rest client failed", response.getBody().get("error"));
    }

    @Test
    void handleOtherExceptions_returnsInternalServerError() {
        Exception ex = new Exception("Something went wrong");
        ResponseEntity<Map<String, String>> response = handler.handleOtherExceptions(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Something went wrong", response.getBody().get("error"));
    }

}
