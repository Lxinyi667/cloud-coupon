package top.mqxu.coupon.template.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description CouponType-表示优惠券类型
 **/
@Getter
@AllArgsConstructor
public enum CouponType {
    UNKNOWN("unknown", "0"),
    MONEY_OFF("满减券", "1"),
    DISCOUNT("打折", "2"),
    RANDOM_DISCOUNT("随机减", "3"),
    NIGHT_MONEY_OFF("晚间双倍优惠券", "4");

    private final String description;
    /**
     * 存在数据库里的最终code
     */
    private final String code;

    public static CouponType convert(String code) {
        return Stream.of(values())
                .filter(bean -> bean.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
