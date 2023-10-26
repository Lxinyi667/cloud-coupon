package top.lxyi.coupon.template.api.beans;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lxyi.coupon.template.api.beans.rules.TemplateRule;

/**
 * 用于创建优惠卷模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponTemplateInfo {

    private Long id;

    @NotNull
    private String name;

    /**
     * 优惠券描述
     */
    @NotNull
    private String desc;

    /**
     * 优惠券类型
     */
    @NotNull
    private String type;

    /**
     * 适用门店 - 若无则为全店通用券
     */
    private Long shopId;

    /**
     * 优惠券规则
     */
    @NotNull
    private TemplateRule rule;

    /**
     * 当前模板是否为可用状态
     */
    private Boolean available;

}
