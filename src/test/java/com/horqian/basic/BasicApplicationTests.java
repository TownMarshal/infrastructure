package com.horqian.basic;

import java.util.ArrayList;
import java.util.List;

import com.auth0.jwt.algorithms.Algorithm;
import com.horqian.basic.deque.LinkedListDeque;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

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
    void mima() {
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
                .matches(pass, "$2a$10$xPBThs3dLzCvM.oDXD1h4OXArBhHvfyuBd/VtQBTHLLET/goawv8u");

        System.out.println(matches);

    }

    public static int f(int n) {
        if (n != 1 && n != 2) {
            if (n != 3) {
                return f(n - 1) + f(n - 2);
            }
            return 2;
        } else return 1;
    }

    @Test

    public void offer() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>(5);
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        deque.offerLast(5);
        assertFalse(deque.offerLast(6));
        ArrayList<Object> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(4);
        list.add(5);
        assertIterableEquals(list, deque);
    }

    @Test
    public void poll() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>(5);
        deque.offerLast(1);
        deque.offerLast(2);
        deque.offerLast(3);
        deque.offerLast(4);
        deque.offerLast(5);

        assertEquals(1,deque.pollFirst());
        assertEquals(2,deque.pollFirst());
        assertEquals(5,deque.pollLast());
        assertEquals(4,deque.pollLast());
        assertEquals(3,deque.pollLast());
        assertNull(deque.pollLast());
        assertTrue(deque.isEmpty());
    }


}
