package top.lxyi.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

@Configuration
public class RoutesConfiguration {
    @Bean
    public RouteLocator declare(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route
                        .after(ZonedDateTime.parse("2022-12-12T14:33:33+08:00"))
                        .and()
                        .path("/gateway/coupon-customer/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://coupon-customer-serv")
                ).route(route -> route
                        .path("/gateway/template/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://coupon-template-serv")
                ).route(route -> route
                        .path("/gateway/calculator/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://coupon-calculation-serv")
                )
                .build();
    }
}
