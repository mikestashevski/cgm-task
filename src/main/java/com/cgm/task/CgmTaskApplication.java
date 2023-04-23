package com.cgm.task;

import com.cgm.task.config.DBConfig;
import com.nosaiii.sjorm.SJORM;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CgmTaskApplication {

    public static void main(String[] args) {
        SJORM.register(DBConfig.MYSQLHOST, DBConfig.MYSQLPORT, DBConfig.MYSQLDB, DBConfig.MYSQLUSERNAME, DBConfig.MYSQLPASSWORD);
        SpringApplication.run(CgmTaskApplication.class, args);
    }
}
