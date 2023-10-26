package top.mqxu.coupon.calculation.api.beans;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.mqxu.coupon.template.api.beans.CouponInfo;

import java.util.List;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description ShoppingCart
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    @NotEmpty
    private List<Product> products;

    private Long couponId;

    private Long cost;

    /**
     * 目前只支持单张优惠券,为了以后的扩展考虑，可以添加多个优惠券的计算逻辑，所以用 List
     */
    private List<CouponInfo> couponInfos;

    @NotNull
    private Long userId;

}