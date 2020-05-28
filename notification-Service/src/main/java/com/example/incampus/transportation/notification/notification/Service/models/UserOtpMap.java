package com.example.incampus.transportation.notification.notification.Service.models;

import lombok.Data;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.Serializable;

@Data
public class UserOtpMap extends JdkSerializationRedisSerializer implements Serializable {
    String username;
    String otp;

}
