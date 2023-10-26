package top.mqxu.coupon.template.service;

import top.mqxu.coupon.template.api.beans.CouponTemplateInfo;
import top.mqxu.coupon.template.api.beans.PagedCouponTemplateInfo;
import top.mqxu.coupon.template.api.beans.TemplateSearchParams;

import java.util.Collection;
import java.util.Map;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description CouponTemplateService——定义了优惠券创建、查找优惠券和修改优惠券可用状态的方法。
 **/
public interface CouponTemplateService {
    /**
     * 创建优惠券模板
     *
     * @param request 模板信息
     * @return 优惠券模板信息
     */
    CouponTemplateInfo createTemplate(CouponTemplateInfo request);

    /**
     * 通过模板ID查询优惠券模板
     *
     * @param id 模板ID
     * @return 优惠券模板信息
     */
    CouponTemplateInfo loadTemplateInfo(Long id);

    /**
     * 克隆券模板
     *
     * @param templateId 模板ID
     * @return 优惠券模板信息
     */
    CouponTemplateInfo cloneTemplate(Long templateId);


    /**
     * 模板查询（分页）
     *
     * @param request 查询参数
     * @return 优惠券模板信息
     */
    PagedCouponTemplateInfo search(TemplateSearchParams request);

    /**
     * 删除券模板
     *
     * @param id 模板ID
     */
    void deleteTemplate(Long id);

    /**
     * 批量读取模板
     *
     * @param ids 模板ID
     * @return 优惠券模板信息:key 是模板ID，value 是模板详情
     */
    Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids);

}