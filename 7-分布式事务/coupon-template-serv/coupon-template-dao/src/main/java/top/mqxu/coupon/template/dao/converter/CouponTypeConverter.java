package top.mqxu.coupon.template.dao.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import top.mqxu.coupon.template.api.enums.CouponType;


/**
 * @author mqxu
 * @date 2023/9/8
 * @description CouponTypeConverter
 **/
@Converter
public class CouponTypeConverter implements AttributeConverter<CouponType, String> {

    @Override
    public String convertToDatabaseColumn(CouponType couponCategory) {
        return couponCategory.getCode();
    }

    @Override
    public CouponType convertToEntityAttribute(String code) {
        return CouponType.convert(code);
    }
}
