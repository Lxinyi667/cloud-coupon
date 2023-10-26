package top.lxyi.coupon.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.swing.*;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"top.lxyi"})
@EnableTransactionManagement
//用于扫描Dao @repository 注解
@EnableJpaRepositories(basePackages = {"top.lxyi"})
public class CustomerApplication {
    public static void main(String[] args) {

        SpringApplication.run(CustomerApplication.class,args);
    }
}