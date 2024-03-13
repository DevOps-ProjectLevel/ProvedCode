package com.provedcode.handlers;

import com.provedcode.handlers.dto.ErrorDTO;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class AwsS3ExceptionHandler {
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<?> fileSizeLimitExceededExceptionHandler(FileSizeLimitExceededException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorDTO("file size is too large"));
    }
}
