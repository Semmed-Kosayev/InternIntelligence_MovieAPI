package az.semmed.internintelligence_movieapi.exception.handler;

import az.semmed.internintelligence_movieapi.exception.AlreadyExistsException;
import az.semmed.internintelligence_movieapi.exception.NotFoundException;
import az.semmed.internintelligence_movieapi.model.constant.ErrorCode;
import az.semmed.internintelligence_movieapi.model.dto.response.GlobalExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<GlobalExceptionResponse> alreadyExistsExceptionHandler(AlreadyExistsException exception) {
        exceptionLog(exception);
        return ResponseEntity.status(BAD_REQUEST).body(GlobalExceptionResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(ErrorCode.ALREADY_EXISTS)
                .errorMessage(exception.getMessage())
                .localDateTime(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalExceptionResponse> notFoundExceptionHandler(NotFoundException exception) {
        exceptionLog(exception);
        return ResponseEntity.status(NOT_FOUND).body(GlobalExceptionResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(ErrorCode.NOT_FOUND)
                .errorMessage(exception.getMessage())
                .localDateTime(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalExceptionResponse> exceptionHandler(Exception exception) {
        exceptionLog(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalExceptionResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .errorMessage(exception.getMessage())
                .localDateTime(LocalDateTime.now())
                .build());
    }

    public void exceptionLog(Exception exception) {
        log.info("{} happened {}", exception.getClass().getSimpleName(), exception.getMessage());
    }
}
