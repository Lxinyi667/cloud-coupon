package top.lxyi.coupon.customer.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import top.lxyi.coupon.customer.feign.TemplateService;
import top.lxyi.coupon.template.api.beans.CouponTemplateInfo;

import java.util.Collection;
import java.util.Map;

@Slf4j
@Component
public class TemplateServiceFallbackFactory implements FallbackFactory<TemplateService> {

    @Override
    public TemplateService create(Throwable cause) {
        return new TemplateService(){
            @Override
            public CouponTemplateInfo getTemplate(Long id) {
                log.info("fallback factory method:"+cause);
                return null;
            }

            @Override
            public Map<Long, CouponTemplateInfo> getTemplateInBatch(Collection<Long> ids) {
                log.info("fallback factory method:"+cause);
                return null;
            }
        };
    }
}
