package com.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Config.class)
class ApplicationTests {

    @Test
    void a() {
      System.out.println(2);
    }

}
