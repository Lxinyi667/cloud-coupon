package top.lxyi.coupon.template.dao.converter;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import top.lxyi.coupon.template.api.enums.CouponType;

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

