package com.example.incampus.transportation.notification.notification.Service.Services;

import com.example.incampus.transportation.notification.notification.Service.models.Credentials;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverRegistration implements Registration {

    private SmsService smsService;

    @Autowired
    public DriverRegistration(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public void saveRegistration(String message) {
        Gson gson = new Gson();
        Credentials credentials = gson.fromJson(message, Credentials.class);
        String textMessage="Welcome to Minchu Incampus eMobility Solutions. Your Registration is Successful Login to Mobile with Username: "+credentials.getUsername()+ "and Password: "+credentials.getPassword();
        smsService.publishSMS(credentials.getUsername(),textMessage);

    }
}
