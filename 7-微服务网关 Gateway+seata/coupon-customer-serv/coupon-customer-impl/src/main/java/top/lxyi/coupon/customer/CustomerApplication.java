package top.lxyi.coupon.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author moqi
 */
@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"top.lxyi"})
@EnableTransactionManagement
//用于扫描Dao @Repository 注解
@EnableJpaRepositories(basePackages = {"top.lxyi"})
//用于扫描JPA实体类 @Entity，默认扫本包当下路径
@EntityScan(basePackages = {"top.lxyi"})
@EnableFeignClients(basePackages = {"top.lxyi"})
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}