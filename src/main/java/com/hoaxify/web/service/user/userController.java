package com.hoaxify.web.service.user;


import com.hoaxify.web.service.error.ApiError;
import com.hoaxify.web.service.shared.GenericMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class userController {

    @Autowired
    UserService userService;


    @PostMapping("/api/v1/users")

    ResponseEntity<?> createUser(@RequestBody User user) {
        //kullanıcı adı girişi boş bırakılırsa
        if (user.getUsername() == null || user.getUsername() .isEmpty()) {
            ApiError apiError=new ApiError();
            apiError.setPath("/api/v1/users");
            apiError.setMessage("Validation error");
            apiError.setStatus(400);
            Map<String,String> validationErrors=new HashMap<>();
            validationErrors.put("username","Kullanıcı adı boş bırakılamaz");
            apiError.setValidationErrors(validationErrors);

            return ResponseEntity.badRequest().body(apiError);
        }
    userService.save(user);
    return ResponseEntity.ok().body(new GenericMessage("Kullanıcı başarıyla oluşturuldu"));
    }




}
