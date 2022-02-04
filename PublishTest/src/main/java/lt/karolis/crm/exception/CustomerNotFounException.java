package lt.karolis.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
//@ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
public class CustomerNotFounException extends RuntimeException{

    public CustomerNotFounException() {
        super("Customer not found");
    }

    public CustomerNotFounException(String message, Integer id) {
        super( "Customer not found "+ message + " id="+ id);
    }
}
