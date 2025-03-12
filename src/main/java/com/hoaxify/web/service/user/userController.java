package com.hoaxify.web.service.user;


import com.hoaxify.web.service.error.ApiError;
import com.hoaxify.web.service.shared.GenericMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class userController {

    @Autowired
    UserService userService;
    @Autowired
    MessageSource messageSource;

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody User user) {
        userService.save(user);
        String message = messageSource.getMessage("hoaxify.create.user.success.message", new Object[]{}, LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        String message = messageSource.getMessage("hoaxify.error.validation", new Object[]{}, LocaleContextHolder.getLocale());
        apiError.setMessage(message);
        apiError.setStatus(400);
        var validationErrors = exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap
                (FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing));
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }
        @ExceptionHandler(NotUniqueEmailException.class)
            ResponseEntity<ApiError> handleNotUniqueEmailException(NotUniqueEmailException exception){
           ApiError apiError1 = new ApiError();
            apiError1.setPath("/api/v1/users");
            String message = messageSource.getMessage("hoaxify.error.validation", new Object[]{}, LocaleContextHolder.getLocale());
            apiError1.setMessage(message);
            apiError1.setStatus(400);
            String validationError = messageSource.getMessage("hoaxify.constraint.email.notunique", new Object[]{}, LocaleContextHolder.getLocale());
            Map<String, String> validationErrors1=new HashMap<>();
           validationErrors1.put("email",validationError);
           apiError1.setValidationErrors(validationErrors1);
            return ResponseEntity.badRequest().body(apiError1);
    }

}



