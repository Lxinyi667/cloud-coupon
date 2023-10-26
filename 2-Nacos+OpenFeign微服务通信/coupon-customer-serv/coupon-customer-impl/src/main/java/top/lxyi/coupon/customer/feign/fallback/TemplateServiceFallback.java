package top.lxyi.coupon.customer.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import top.lxyi.coupon.customer.feign.TemplateService;
import top.lxyi.coupon.template.api.beans.CouponTemplateInfo;

import java.util.Collection;
import java.util.Map;

@Slf4j
public class TemplateServiceFallback implements TemplateService {
    @Override
    public CouponTemplateInfo getTemplate(Long id) {
        log.info("根据id获取模板" );
        return null;
    }

    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInBatch(Collection<Long> ids) {
        log.info("批量获取优惠卷模板降级逻辑");
        return null;
    }
}