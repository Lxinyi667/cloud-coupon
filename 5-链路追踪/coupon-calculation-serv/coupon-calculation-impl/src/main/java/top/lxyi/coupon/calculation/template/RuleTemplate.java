package top.lxyi.coupon.calculation.template;

import top.lxyi.coupon.calculation.api.beans.ShoppingCart;

/**
 * RuleTemplate ——模板规则接口
 */
public interface RuleTemplate {
    /**
     * 计算优惠卷
     */
    ShoppingCart calculate(ShoppingCart settlement);
}
