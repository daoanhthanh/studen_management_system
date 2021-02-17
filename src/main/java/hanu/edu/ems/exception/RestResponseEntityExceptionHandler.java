package hanu.edu.ems.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Nonnull
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            @Nonnull HttpHeaders headers,
            @Nonnull HttpStatus status,
            @Nonnull WebRequest request
    ) {
        String error = ex.getParameterName() + " parameter is missing";
        String message = removePackageLocation(ex.getLocalizedMessage());
        BaseException baseException =
                new BaseException(HttpStatus.BAD_REQUEST, message, Collections.singletonList(error));
        return new ResponseEntity<>(
                baseException, new HttpHeaders(), baseException.getStatus());
    }

    @Nonnull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @Nonnull HttpHeaders headers,
            @Nonnull HttpStatus status,
            @Nonnull WebRequest request
    ) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        String message = removePackageLocation(ex.getLocalizedMessage());
        BaseException baseException = new BaseException(HttpStatus.BAD_REQUEST, message, errors);
        return handleExceptionInternal(ex, baseException, headers, baseException.getStatus(), request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        String message = removePackageLocation(e.getLocalizedMessage());
        BaseException baseException = new BaseException(HttpStatus.CONFLICT, message, Collections.singletonList("Data Integrity Violation"));
        return new ResponseEntity<>(baseException, new HttpHeaders(), baseException.getStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialsException(BadCredentialsException e) {
        String message = removePackageLocation(e.getLocalizedMessage());
        BaseException baseException = new BaseException(HttpStatus.BAD_REQUEST, message, Collections.singletonList("Incorrect Username or password"));
        return new ResponseEntity<>(baseException, new HttpHeaders(), baseException.getStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        BaseException baseException =
                new BaseException(HttpStatus.BAD_REQUEST, "Domain constraints' validations failed", errors);
        return new ResponseEntity<>(
                baseException, new HttpHeaders(), baseException.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex) {
        String error = ex.getName() + " should be of type " + Objects.requireNonNull(ex.getRequiredType()).getName();

        String message = removePackageLocation(ex.getLocalizedMessage());
        BaseException baseException =
                new BaseException(HttpStatus.BAD_REQUEST, message, Collections.singletonList(error));
        return new ResponseEntity<>(
                baseException, new HttpHeaders(), baseException.getStatus());
    }

    @Override
    @Nonnull
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            @Nonnull HttpHeaders headers,
            @Nonnull HttpStatus status,
            @Nonnull WebRequest request
    ) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(
                " method is not supported for this request. Supported methods are ");
        Objects.requireNonNull(ex.getSupportedHttpMethods()).forEach(t -> builder.append(t).append(" "));

        String message = removePackageLocation(ex.getLocalizedMessage());
        BaseException baseException = new BaseException(HttpStatus.METHOD_NOT_ALLOWED, message, Collections.singletonList(builder.toString()));
        return new ResponseEntity<>(
                baseException, new HttpHeaders(), baseException.getStatus());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        String message = removePackageLocation(ex.getLocalizedMessage());
        BaseException baseException = new BaseException(HttpStatus.NOT_FOUND, message, null);
        return new ResponseEntity<>(baseException, new HttpHeaders(), baseException.getStatus());
    }

    private String removePackageLocation(String message) {
        return message.replaceAll("hanu\\.edu\\.ems\\.?", "");
    }

//    @ExceptionHandler({Exception.class})
//    public ResponseEntity<Object> handleAll(Exception ex) {
//        BaseException baseException = new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), Collections.singletonList("Unknown error occurred"));
//        return new ResponseEntity<>(baseException, new HttpHeaders(), baseException.getStatus());
//    }
}
