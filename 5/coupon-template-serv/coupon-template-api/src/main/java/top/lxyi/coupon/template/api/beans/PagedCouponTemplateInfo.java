package top.lxyi.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 封装分页信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedCouponTemplateInfo {
    public List<CouponTemplateInfo> templates;

    public int page;

    public long total;
}
