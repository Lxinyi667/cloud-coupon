package top.mqxu.coupon.calculation.template;

import top.mqxu.coupon.calculation.api.beans.ShoppingCart;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description RuleTemplate——模版规则接口
 **/
public interface RuleTemplate {
    /**
     * 计算优惠券
     *
     * @param settlement 传入的参数
     * @return 返回的结果
     */
    ShoppingCart calculate(ShoppingCart settlement);
}
