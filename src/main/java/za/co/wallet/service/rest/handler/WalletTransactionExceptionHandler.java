package za.co.wallet.service.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WalletTransactionExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WalletTransactionException.class)
    public ErrorResponse handleWalletTransactionException(WalletTransactionException cause) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .message(cause.getMessage())
                    .build();
            return errorResponse;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleWalletTransactionException(HttpMessageNotReadableException cause) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(cause.getMessage())
                .build();
        return errorResponse;
    }

}
