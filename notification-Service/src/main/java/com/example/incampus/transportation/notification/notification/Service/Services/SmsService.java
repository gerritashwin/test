package com.example.incampus.transportation.notification.notification.Service.Services;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class SmsService {

    AmazonSNSClient snsClient;
    Map<String, MessageAttributeValue> smsAttributes;

    @Autowired
    public SmsService(AmazonSNSClient snsClient, Map<String, MessageAttributeValue> smsAttributes) {
        this.snsClient = snsClient;
        this.smsAttributes = smsAttributes;
    }

    public void publishSMS(String phoneNumber, String message) {
        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(this.smsAttributes));
        System.out.println(result.getMessageId());

    }



}
