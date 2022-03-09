package com.horqian.basic;

import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;

@SpringBootTest
class BasicApplicationTests {

    @Test
    void contextLoads() {
//        String encoder = new BCryptPasswordEncoder().encode("1391228068781207554"+"horqian");
//        System.out.println(encoder);
//       int userId=1391228068781207554;
        System.out.println(Algorithm.HMAC256(1391228068781207554L + "horqian"));

    }

    @Test
    void mima(){
        String pass = "admin";
//        String pass = 1391228068781207554L + "horqian";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String passHash = encoder.encode(pass);
        System.out.println(passHash);
        //$2a$10$91fd33ajpqlr1HILp/oVJ.jjkdrUU0bFN8LTJHKBavMiOrnxr9N7y
        //true
        //$2a$10$V/tO8.KBqCY45NxWQc0VquerzQeecBTWBi9XYagGYg/Kja4yMQWlS
        //true
//        final boolean matches = encoder
//                .matches(pass,passHash);
        final boolean matches = encoder
//                .matches(pass,"$2a$10$V/tO8.KBqCY45NxWQc0VquerzQeecBTWBi9XYagGYg/Kja4yMQWlS");
                .matches(pass,"$2a$10$xPBThs3dLzCvM.oDXD1h4OXArBhHvfyuBd/VtQBTHLLET/goawv8u");

        System.out.println(matches);

    }

    public static int f(int n) {
        if(n!=1&&n!=2) {
            if(n!=3) {
                return f(n-1)+f(n-2);
            }
            return 2;
        }
        else return 1;
    }



}
