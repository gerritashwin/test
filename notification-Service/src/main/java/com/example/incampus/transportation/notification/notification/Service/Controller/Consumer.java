package com.example.incampus.transportation.notification.notification.Service.Controller;

import com.example.incampus.transportation.notification.notification.Service.Services.DriverRegistration;
import com.example.incampus.transportation.notification.notification.Service.Services.Registration;
import com.example.incampus.transportation.notification.notification.Service.Services.UserRegistration;
import com.example.incampus.transportation.notification.notification.Service.Repository.RedisDao;
import com.example.incampus.transportation.notification.notification.Service.Services.SmsService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
    RedisDao redisDao;
    SmsService smsService;
    UserRegistration userRegistration;
    DriverRegistration driverRegistration;

    @Autowired
    public Consumer(RedisDao redisDao, SmsService smsService, UserRegistration userRegistration, DriverRegistration driverRegistration) {
        this.redisDao = redisDao;
        this.smsService = smsService;
        this.userRegistration = userRegistration;
        this.driverRegistration = driverRegistration;
    }





    @KafkaListener(topics = "otpgen", groupId = "group_json",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeJson(String message) {
        System.out.println("Consumed JSON Message: " + message);
        Registration registration= userRegistration;
        registration.saveRegistration(message);
    }
    @KafkaListener(topics = "driverReg", groupId = "notificationService",containerFactory = "kafkaListenerContainerFactory")
    public void consumeDriverJson(String message){
        Registration registration=driverRegistration;
        registration.saveRegistration(message);
    }
}
