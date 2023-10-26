package top.lxyi.coupon.calculation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"top.lxyi"})
public class CalculationApplication {

    public static void main(String[] args) {

        SpringApplication.run(CalculationApplication.class, args);
    }
}