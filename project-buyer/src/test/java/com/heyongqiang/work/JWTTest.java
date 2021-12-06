package com.heyongqiang.work;

import org.apache.commons.codec.digest.DigestUtils;

public class JWTTest {
    
    
    public static void main(String[] args) {
        String str = "1";
        String s = DigestUtils.md5Hex(1 + "123hyq!@dsfas");
        System.out.println(s);
    }
}
