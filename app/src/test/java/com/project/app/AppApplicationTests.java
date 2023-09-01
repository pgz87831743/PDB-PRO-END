package com.project.app;

import com.project.app.basic.entity.TbUser;
import com.project.app.basic.service.TbUserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest(classes = {AppApplication.class})
public class AppApplicationTests {


    @Resource
    TbUserService tbUserServiceImpl;

    @Test
    void contextLoads() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("admin");
        tbUser.setPassword("12312312");
        tbUserServiceImpl.save(tbUser);
    }

}
