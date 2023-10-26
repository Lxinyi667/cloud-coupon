package top.lxyi.coupon.customer.api.beans;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCoupon {
    @NotNull
    private Long userId;

    private Long shopId;
    private Integer couponStatus;
}
