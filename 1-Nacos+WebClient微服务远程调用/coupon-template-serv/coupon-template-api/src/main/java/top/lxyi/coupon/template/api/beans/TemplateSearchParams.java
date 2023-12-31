package top.lxyi.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装查询信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateSearchParams {

    private Long id;
    private String name;
    private String type;
    private Long shopId;
    private Boolean available;
    private int page;
    private int pageSize;
}
