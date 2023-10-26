package top.lxyi.coupon.template.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.lxyi.coupon.template.api.beans.CouponTemplateInfo;
import top.lxyi.coupon.template.api.beans.PagedCouponTemplateInfo;
import top.lxyi.coupon.template.api.beans.TemplateSearchParams;
import top.lxyi.coupon.template.service.CouponTemplateService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description CouponTemplateController
 **/
@Slf4j
@RestController
@RequestMapping("/template")
public class CouponTemplateController {

    @Resource
    private CouponTemplateService couponTemplateService;

    /**
     * 创建优惠券
     *
     * @param request {@link CouponTemplateInfo}
     * @return {@link CouponTemplateInfo}
     */
    @PostMapping("/addTemplate")
    public CouponTemplateInfo addTemplate(@Valid @RequestBody CouponTemplateInfo request) {
        log.info("Create coupon template: data={}", request);
        return couponTemplateService.createTemplate(request);
    }

    /**
     * 克隆优惠券
     *
     * @param templateId 模板id
     * @return {@link CouponTemplateInfo}
     */
    @PostMapping("/cloneTemplate")
    public CouponTemplateInfo cloneTemplate(@RequestParam("id") Long templateId) {
        log.info("Clone coupon template: data={}", templateId);
        return couponTemplateService.cloneTemplate(templateId);
    }

    /**
     * 读取优惠券
     *
     * @param id 模板id
     * @return {@link CouponTemplateInfo}
     */
    @GetMapping("/getTemplate")
    public CouponTemplateInfo getTemplate(@RequestParam("id") Long id) {
        log.info("Load template, id={}", id);
        return couponTemplateService.loadTemplateInfo(id);
    }

    /**
     * 批量获取
     *
     * @param ids 模板id
     * @return {@link Map<Long, CouponTemplateInfo>}
     */
    @GetMapping("/getBatch")
    @SentinelResource(value = "getTemplateInBatch", blockHandler = "getTemplateInBatchBlock")
    public Map<Long, CouponTemplateInfo> getTemplateInBatch(@RequestParam("ids") Collection<Long> ids) {
        log.info("getTemplateInBatch: {}", JSON.toJSONString(ids));
        log.info("getTemplateInBatch 被调用！！！");
        if(ids.size() == 3){
            throw new RuntimeException();
        }
        return couponTemplateService.getTemplateInfoMap(ids);
    }

    public Map<Long,CouponTemplateInfo> getTemplateInBatchBlock(Collection<Long> ids, BlockException exception){
        log.info("接口被限流");
        CouponTemplateInfo couponTemplateInfo = CouponTemplateInfo.builder()
                .name("限流后返回的优惠券")
                .desc("限流后返回的优惠券")
                .type("1")
                .rule(null)
                .build();
        Map<Long , CouponTemplateInfo> map = new HashMap<>();
        map.put(1L,couponTemplateInfo);

        return map;
    }
    /**
     * 搜索模板
     *
     * @param request 搜索条件
     * @return {@link PagedCouponTemplateInfo}
     */
    @PostMapping("/search")
    public PagedCouponTemplateInfo search(@Valid @RequestBody TemplateSearchParams request) {
        log.info("search templates, payload={}", request);
        return couponTemplateService.search(request);
    }

    /**
     * 删除优惠券
     *
     * @param id 模板id
     */
    @DeleteMapping("/deleteTemplate")
    public void deleteTemplate(@RequestParam("id") Long id) {
        log.info("Load template, id={}", id);
        couponTemplateService.deleteTemplate(id);
    }
}