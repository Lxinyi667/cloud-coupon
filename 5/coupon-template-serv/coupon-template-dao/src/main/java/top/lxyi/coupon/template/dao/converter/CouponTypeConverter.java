package top.lxyi.coupon.template.dao.converter;


import top.lxyi.coupon.template.api.enums.CouponType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
    public class CouponTypeConverter implements AttributeConverter<CouponType, String> {
        @Override
        public String convertToDatabaseColumn(CouponType couponCategory){
            return couponCategory.getCode();
        }

        @Override
        public CouponType convertToEntityAttribute(String code) {
            return CouponType.convert(code);
        }
    }

