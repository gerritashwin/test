package com.example.incampus.transportation.notification.notification.Service.Services;

import java.util.Random;

public class Otp {

    public int getOtp() {
        Random random = new Random();
        int otp = random.nextInt(8999);
        otp = otp + 1000;
        return otp;
    }
}
