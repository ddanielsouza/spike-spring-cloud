package souza.oliveira.daniel.msavaliadorcredito.application.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApplicationException extends RuntimeException{
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;



    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public ApplicationException(String message, Throwable cause, int httpStatus) {
        super(message, cause);
        this.httpStatus = HttpStatus.valueOf(httpStatus);
    }

    public ApplicationException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public ApplicationException(Throwable cause, int httpStatus) {
        super(cause);
        this.httpStatus = HttpStatus.valueOf(httpStatus);
    }
}
