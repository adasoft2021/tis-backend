package com.adasoft.tis;

import com.adasoft.tis.controllers.impl.ReviewRestControllerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TisBackendApplicationTests {
    @Autowired
    private ReviewRestControllerImpl controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

}
