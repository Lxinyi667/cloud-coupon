package top.lxyi.coupon.customer.api.beans;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCoupon {
    /**
     * 用户 id
     */
    @NotNull
    private Long userId;
    /**
     * 卷模板 id
     */
    @NotNull
    private Long couponTemplateId;
}
