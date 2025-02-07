package com.zxb.backEnd;

import com.zxb.backEnd.pojos.User;
import com.zxb.backEnd.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BackEndApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void testUserContext() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                User user = new User();
                user.setId((int) Thread.currentThread().getId());
                UserContext.setUser(user);
                log.info("当前线程用户为: {}", UserContext.getUser());
                UserContext.clearUser();
            }).start();
        }
    }

}
