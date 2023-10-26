package top.mqxu.coupon.calculation.service;

import org.springframework.web.bind.annotation.RequestBody;
import top.mqxu.coupon.calculation.api.beans.ShoppingCart;
import top.mqxu.coupon.calculation.api.beans.SimulationOrder;
import top.mqxu.coupon.calculation.api.beans.SimulationResponse;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description CouponCalculationService
 **/
public interface CouponCalculationService {
    /**
     * 订单价格计算
     *
     * @param cart 购物车
     * @return 计算结果
     */
    ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart cart);

    /**
     * 订单价格试算
     *
     * @param cart 购物车
     * @return 试算结果
     */
    SimulationResponse simulateOrder(@RequestBody SimulationOrder cart);
}
