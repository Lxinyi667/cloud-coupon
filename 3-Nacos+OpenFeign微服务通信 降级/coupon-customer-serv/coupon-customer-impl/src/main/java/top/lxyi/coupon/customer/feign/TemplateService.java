package top.lxyi.coupon.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.lxyi.coupon.customer.feign.fallback.TemplateServiceFallback;
import top.lxyi.coupon.customer.feign.fallback.TemplateServiceFallbackFactory;
import top.lxyi.coupon.template.api.beans.CouponTemplateInfo;

import java.util.Collection;
import java.util.Map;

@FeignClient(value = "coupon-template-serv", path = "/template",fallback = TemplateServiceFallback.class)
public interface TemplateService {
    //读取优惠卷
    @GetMapping("/getTemplate")
    CouponTemplateInfo getTemplate(@RequestParam("id") Long id);

    //批量获取
    @GetMapping("/getBatch")
    Map<Long,CouponTemplateInfo> getTemplateInBatch(@RequestParam("ids")Collection<Long> ids);
}
