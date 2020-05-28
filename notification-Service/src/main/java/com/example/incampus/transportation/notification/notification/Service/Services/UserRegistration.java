package com.example.incampus.transportation.notification.notification.Service.Services;

import com.example.incampus.transportation.notification.notification.Service.Repository.RedisDao;
import com.example.incampus.transportation.notification.notification.Service.models.UserOtpMap;
import com.example.incampus.transportation.notification.notification.Service.models.UserDetails;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserRegistration  implements  Registration{

    private RedisDao redisDao;
    private SmsService smsService;

    @Autowired
    public UserRegistration(RedisDao redisDao, SmsService smsService) {
        this.redisDao = redisDao;
        this.smsService = smsService;
    }

    @Override
    public void saveRegistration(String message){
        Gson gson = new Gson();
        UserDetails userDetails = gson.fromJson(message, UserDetails.class);
        System.out.println(userDetails.getPhoneNumber());
        UserOtpMap userOtpMap = new UserOtpMap();
        Otp otpObj=new Otp();
        String otp= String.valueOf(otpObj.getOtp());
        String textMessage = "Welcome to Minchu Incampus eMobility Solutions. Your OTP is " +otp ;
        smsService.publishSMS(userDetails.getPhoneNumber(),textMessage);
        HashMap<String,String> map=new HashMap<>();
        map.put("username",userDetails.getUsername());
        map.put("otp",otp);
        userOtpMap.setOtp(otp);
        userOtpMap.setUsername(userDetails.getUsername());
        redisDao.addUser(map);
    }
}
