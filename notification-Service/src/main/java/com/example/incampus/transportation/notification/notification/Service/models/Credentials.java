package com.example.incampus.transportation.notification.notification.Service.models;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

@Data
public class Credentials {
    private String username;
    private String password;
}
