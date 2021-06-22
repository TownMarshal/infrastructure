package com.horqian.basic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasicApplicationTests {

    @Test
    void contextLoads() {

        for(int i =1 ; i < 25 ; i++){
            System.out.println(f(i));
        }

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
