package com.example.incampus.transportation.notification.notification.Service.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Repository
public class RedisDao {


    private static final String KEY = "users";
    private RedisTemplate<String, HashMap<String,String>> redisTemplate;
    private HashOperations<String, String, HashMap<String,String>> hashOps;

    @Autowired
    public RedisDao(RedisTemplate<String, HashMap<String,String>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void initializeHashOps() {
        hashOps = redisTemplate.opsForHash();
    }

    public void addUser(HashMap<String,String> user) {
        hashOps.put(KEY, user.get("username"), user);
    }
//    public void updateUser(Otp user) {
//        hashOps.put(KEY, user.getMuid(), user);
//    }
//    public Otp getUser(String muid) {
//        return hashOps.get(KEY, muid);
//    }
//    public long getNumberOfUser() {
//        return hashOps.size(KEY);
//    }
//    public Map<String, Otp> getAllUser() {
//        return hashOps.entries(KEY);
//    }
//    public long deleteUser(String... ids) {
//        return hashOps.delete(KEY, (Object)ids);
//    }
}
