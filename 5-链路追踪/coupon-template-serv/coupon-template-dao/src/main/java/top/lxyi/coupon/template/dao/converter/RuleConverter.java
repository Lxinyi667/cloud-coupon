package top.lxyi.coupon.template.dao.converter;

import com.alibaba.fastjson.JSON;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import top.lxyi.coupon.template.api.beans.rules.TemplateRule;

@Converter
public class RuleConverter implements AttributeConverter<TemplateRule, String> {
    @Override
    public String convertToDatabaseColumn(TemplateRule rule){
        return JSON.toJSONString(rule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String rule) {
        return JSON.parseObject(rule,TemplateRule.class);
    }
}
