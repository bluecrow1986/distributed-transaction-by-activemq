package com.bluecrow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author BlueCrow
 * @Package com.bluecrow
 * @Decription
 * @date 2021/7/24 19:37
 */
@SpringBootApplication
@MapperScan("com.bluecrow.**.dao")
@EnableJms
@EnableScheduling
@EnableTransactionManagement
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
