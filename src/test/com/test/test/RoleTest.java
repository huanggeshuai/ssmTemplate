package com.test.test;

import com.alibaba.fastjson.JSON;
import com.huang.service.FunctionService;
import com.test.base.BaseTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleTest extends BaseTestService {
    @Autowired
    private FunctionService functionService;

    @Test
    void test(){
        logger.info(JSON.toJSONString(functionService.treeMenu(1)));
    }
}
