package top.mqxu.coupon.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mqxu.coupon.template.api.beans.CouponTemplateInfo;

import java.util.Collection;
import java.util.Map;

/**
 * @author mqxu
 * @date 2023/9/21
 * @description TemplateService
 **/
@FeignClient(value = "coupon-template-serv", path = "/template")
public interface TemplateService {
    // 读取优惠券
    @GetMapping("/getTemplate")
    CouponTemplateInfo getTemplate(@RequestParam("id") Long id);

    // 批量获取
    @GetMapping("/getBatch")
    Map<Long, CouponTemplateInfo> getTemplateInBatch(@RequestParam("ids") Collection<Long> ids);
}