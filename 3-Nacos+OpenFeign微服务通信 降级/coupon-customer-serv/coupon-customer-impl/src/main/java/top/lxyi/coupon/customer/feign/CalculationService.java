package top.lxyi.coupon.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import top.lxyi.coupon.calculation.api.beans.ShoppingCart;
import top.lxyi.coupon.calculation.api.beans.SimulationOrder;
import top.lxyi.coupon.calculation.api.beans.SimulationResponse;

@FeignClient(value = "coupon-calculation-serv",path= "/calculator")
public interface CalculationService {
    /**
     * 优惠卷结算
     */
    @PostMapping("/checkout")
    ShoppingCart checkout(ShoppingCart settlement);

    /**
     * 优惠卷列表挨个试算，给客户提示每个可用卷的优惠额度，帮助挑选
     */
    @PostMapping("/simulate")
    SimulationResponse simulate(SimulationOrder simulator);
}
