package top.mqxu.coupon.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import top.mqxu.coupon.calculation.api.beans.ShoppingCart;
import top.mqxu.coupon.calculation.api.beans.SimulationOrder;
import top.mqxu.coupon.calculation.api.beans.SimulationResponse;

/**
 * @author mqxu
 * @date 2023/9/14
 * @description CalculationService
 **/

@FeignClient(value = "coupon-calculation-serv", path = "/calculator")
public interface CalculationService {

    /**
     * 优惠券结算
     *
     * @param settlement 优惠券结算请求对象
     * @return 优惠券结算响应对象
     */
    @PostMapping("/checkout")
    ShoppingCart checkout(ShoppingCart settlement);

    /**
     * 优惠券列表挨个试算,给客户提示每个可用券的优惠额度，帮助挑选
     *
     * @param simulator 试算请求对象
     * @return 试算响应对象
     */
    @PostMapping("/simulate")
    SimulationResponse simulate(SimulationOrder simulator);
}
