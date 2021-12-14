package com.heyongqiang.work;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class JWTTest {
    


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        String s = stringRedisTemplate.opsForValue().get("TOKEN_eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDAzODY3OTQsInVzZXJJZCI6MSwiaWF0IjoxNjM5NDk3NzYyfQ.0gW-78RvZV33aNAGKtqgXVuMFnLVEUTL1bh-xYLNkmo");
        System.out.println(s);
    }
}
