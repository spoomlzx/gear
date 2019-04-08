package com.spoom.gear;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GearApplicationTests {

    @Test
    public void contextLoads() {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        log.info(encoder.encode("123456"));
    }

}
