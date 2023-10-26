package top.mqxu.coupon.template.dao.converter;

import com.alibaba.fastjson.JSON;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import top.mqxu.coupon.template.api.beans.rules.TemplateRule;


/**
 * @author mqxu
 * @date 2023/9/8
 * @description RuleConverter
 **/
@Converter
public class RuleConverter implements AttributeConverter<TemplateRule, String> {

    @Override
    public String convertToDatabaseColumn(TemplateRule rule) {
        return JSON.toJSONString(rule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String rule) {
        return JSON.parseObject(rule, TemplateRule.class);
    }
}