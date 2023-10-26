package top.mqxu.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description PagedCouponTemplateInfo——封装分页信息
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedCouponTemplateInfo {

    public List<CouponTemplateInfo> templates;

    public Integer page;

    public Long total;

}