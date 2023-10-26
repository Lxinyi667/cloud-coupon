package top.lxyi.coupon.calculation.template.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.lxyi.coupon.calculation.template.AbstractRuleTemplate;
import top.lxyi.coupon.calculation.template.RuleTemplate;

@Slf4j
@Component
public class DiscountTemplate extends AbstractRuleTemplate implements RuleTemplate {
    @Override
    protected Long calculateNewPrice(Long totalAmount, Long shopAmount, Long quota) {
        // 计算使用优惠卷之后的价格
        Long newPrice = convertToDecimal(shopAmount *(quota.doubleValue() / 100));
        log.debug("original price={} , new price={}",totalAmount,newPrice);
        return newPrice;
    }
}
