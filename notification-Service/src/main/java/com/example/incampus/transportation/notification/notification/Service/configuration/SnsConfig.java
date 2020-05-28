package com.example.incampus.transportation.notification.notification.Service.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SnsConfig {

    @Value(value = "${access.key}")
    String accessKey;
    @Value(value = "${access.secret}")
    String secretKey;

    @Bean
    public BasicAWSCredentials getSnsCreds() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    public AmazonSNSClient getClient() {
        return new AmazonSNSClient(new AWSStaticCredentialsProvider(getSnsCreds()));
    }

    @Bean
    public Map<String, MessageAttributeValue> getSmsAttribute() {
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue("Transactional")
                .withDataType("String"));
        return smsAttributes;
    }
}
