package com.spring;
import com.spring.DAO.SelectMusic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private SelectMusic selectMusic;

    @Test
    void contextLoads() {
        System.out.println(selectMusic.getById(1));
        System.out.println("fadsfasd");
    }
}
