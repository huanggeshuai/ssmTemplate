package com.test.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by huang on 2019/2/13.
 */
//引入spring配置文件
@ContextConfiguration("classpath:spring/spring-config.xml")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class BaseTestService {
    public Logger logger= LogManager.getLogger(this.getClass());



}
