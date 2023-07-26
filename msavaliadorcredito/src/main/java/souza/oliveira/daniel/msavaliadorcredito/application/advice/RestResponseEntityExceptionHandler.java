package souza.oliveira.daniel.msavaliadorcredito.application.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import souza.oliveira.daniel.msavaliadorcredito.application.dto.ApplicationErrorDTO;
import souza.oliveira.daniel.msavaliadorcredito.application.exception.ApplicationException;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleConflict(
            Exception ex, WebRequest request) {
        String msgEx = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof ApplicationException) {
            final var appEx = (ApplicationException) ex;
            status = appEx.getHttpStatus();
            msgEx = appEx.getMessage();
        }

        log.error(ex.getMessage(), ex);

        final var body = ApplicationErrorDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .error(status.getReasonPhrase())
                .status(status.value())
                .message(msgEx)
                .build();

        return new ResponseEntity<>(body, status);

    }
}
