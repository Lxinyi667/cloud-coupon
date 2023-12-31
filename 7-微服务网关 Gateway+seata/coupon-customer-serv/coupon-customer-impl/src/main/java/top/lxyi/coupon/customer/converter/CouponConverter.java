package top.lxyi.coupon.customer.converter;

import top.lxyi.coupon.customer.dao.entity.Coupon;
import top.lxyi.coupon.template.api.beans.CouponInfo;


/**
 * @author mqxu
 * @date 2023/9/8
 * @description CouponConverter
 **/
public class CouponConverter {

    public static CouponInfo convertToCoupon(Coupon coupon) {

        return CouponInfo.builder()
                .id(coupon.getId())
                .status(coupon.getStatus().getCode())
                .templateId(coupon.getTemplateId())
                .shopId(coupon.getShopId())
                .userId(coupon.getUserId())
                .template(coupon.getTemplateInfo())
                .build();
    }
}