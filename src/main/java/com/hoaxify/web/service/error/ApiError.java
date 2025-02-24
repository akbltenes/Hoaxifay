package com.hoaxify.web.service.error;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
//Hata mesajları
public class ApiError {
   private int status;

   private String message;

    private String path;

  private long timestamp = new Date().getTime();

  private Map<String,String> validationErrors=new HashMap<>();


    }

