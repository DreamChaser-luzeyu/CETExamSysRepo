package cn.binarydumplings.cetexam;

import cn.binarydumplings.cetexam.Bean.Exception.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException e) {

        return e.getMessage();
    }
}
