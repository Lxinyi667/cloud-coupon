package top.lxyi.coupon.calculation.template.impl;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.lxyi.coupon.calculation.api.beans.ShoppingCart;
import top.lxyi.coupon.calculation.template.AbstractRuleTemplate;
import top.lxyi.coupon.calculation.template.RuleTemplate;

@Slf4j
@Component
public class DummyTemplate extends AbstractRuleTemplate implements RuleTemplate {
    @Override
    public ShoppingCart calculate (ShoppingCart order){
        //获取订单总价
        long orderTotalAmount = getTotalPrice(order.getProducts());
        order.setCost(orderTotalAmount);
        return order;
    }

    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota){
        return orderTotalAmount;
    }
}
