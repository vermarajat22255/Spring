package com.authentication.userauthentication;

import com.authentication.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class JwtUtilTest {
    JwtUtil jwtUtil = new JwtUtil();
    @Test
    public void getToken(){
        UserDetails userDetails = new User("abc", "123@xyz",new ArrayList<>());
        String token = jwtUtil.generateToken(userDetails);
        System.out.println(token);
        System.out.println(jwtUtil.getUserNameFromToken(token));
    }

}
