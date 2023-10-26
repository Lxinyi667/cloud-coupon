package top.lxyi.coupon.calculation.api.beans;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lxyi.coupon.template.api.beans.CouponInfo;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @NotEmpty
    private List<Product> products;
    private Long couponId;
    private long cost;
    /**
     * 目前支支持单张优惠卷，为了以后的扩展考虑，
     * 可以添加多个优惠卷的计算逻辑，所以用 List
     */
    private List<CouponInfo> couponInfos;

    @NotNull
    private Long userId;

}
