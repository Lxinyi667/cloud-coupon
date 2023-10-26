package top.lxyi.coupon.customer;
import feign.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;




@org.springframework.context.annotation.Configuration
public class Configuration {
    /**
     * 注册一个WebClient的的实例用来远程调用
     * @return
     */
    @Bean
    Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

}
