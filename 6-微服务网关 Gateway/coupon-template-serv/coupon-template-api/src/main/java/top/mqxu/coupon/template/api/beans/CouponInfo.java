package top.mqxu.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description CouponInfo——封装优惠券信息
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponInfo {

    private Long id;

    private Long templateId;

    private Long userId;

    private Long shopId;

    private Integer status;

    private CouponTemplateInfo template;

}