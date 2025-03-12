package com.hoaxify.web.service.user.validation;

import com.hoaxify.web.service.user.User;
import com.hoaxify.web.service.user.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       User inDataBase= userRepository.findByEmail(value);
      return inDataBase == null;
    }
}
