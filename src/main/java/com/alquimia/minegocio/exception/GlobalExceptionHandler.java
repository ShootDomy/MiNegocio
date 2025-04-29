package com.alquimia.minegocio.exception;

import java.net.URI;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String getNow() {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> handleBusiness(BusinessException ex, WebRequest request) {
        // Crear el ProblemDetail con el mensaje y el código de estado
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());

        // Convertir el string de la URI a URI
        String requestUri = request.getDescription(false).replace("uri=", "");
        URI uri = URI.create(requestUri); // Convertir String a URI

        problemDetail.setInstance(uri); // Establecer la instancia como URI
        problemDetail.setProperty("timestamp", getNow());

        return new ResponseEntity<>(problemDetail, HttpStatus.CONFLICT);
    }

    // Manejo de ValidationException
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ProblemDetail> handleValidation(ValidationException ex, WebRequest request) {
        // Crear el ProblemDetail
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        URI uri = URI.create(request.getDescription(false).replace("uri=", ""));
        problemDetail.setInstance(uri); // Establecer la URI de la solicitud
        problemDetail.setProperty("timestamp", getNow());

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    // Manejo de ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        // Crear el ProblemDetail
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        URI uri = URI.create(request.getDescription(false).replace("uri=", ""));
        problemDetail.setInstance(uri); // Establecer la URI de la solicitud
        problemDetail.setProperty("timestamp", getNow());

        return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
    }

    // Manejo de Exception genérica
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleOtherErrors(Exception ex, WebRequest request) {
        // Crear el ProblemDetail para errores genéricos
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error inesperado: " + ex.getMessage());
        URI uri = URI.create(request.getDescription(false).replace("uri=", ""));
        problemDetail.setInstance(uri); // Establecer la URI de la solicitud
        problemDetail.setProperty("timestamp", getNow());

        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
