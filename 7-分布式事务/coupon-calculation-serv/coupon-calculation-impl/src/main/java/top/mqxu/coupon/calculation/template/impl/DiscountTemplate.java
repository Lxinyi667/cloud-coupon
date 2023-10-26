package top.mqxu.coupon.calculation.template.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.mqxu.coupon.calculation.template.AbstractRuleTemplate;
import top.mqxu.coupon.calculation.template.RuleTemplate;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description DiscountTemplate——打折优惠券
 **/
@Slf4j
@Component
public class DiscountTemplate extends AbstractRuleTemplate implements RuleTemplate {

    @Override
    protected Long calculateNewPrice(Long totalAmount, Long shopAmount, Long quota) {
        // 计算使用优惠券之后的价格
        Long newPrice = convertToDecimal(shopAmount * (quota.doubleValue() / 100));
        log.debug("original price={}, new price={}", totalAmount, newPrice);
        return newPrice;
    }
}