package top.lxyi.coupon.template.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import top.lxyi.coupon.template.dao.converter.CouponTypeConverter;
import top.lxyi.coupon.template.api.beans.rules.TemplateRule;
import top.lxyi.coupon.template.api.enums.CouponType;
import top.lxyi.coupon.template.dao.converter.RuleConverter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description CouponTemplate
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon_template")
public class CouponTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 优惠券名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 状态是否可用
     */
    @Column(name = "available", nullable = false)
    private Boolean available;


    /**
     * 适用门店-如果为空，则为全店满减券
     */
    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 优惠券描述
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * 优惠券类型
     */
    @Column(name = "type", nullable = false)
    @Convert(converter = CouponTypeConverter.class)
    private CouponType category;

    /**
     * 创建时间，通过 @CreateDate 注解自动填值（需要配合 @JpaAuditing 注解在启动类上生效）
     */
    @CreatedDate
    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    /**
     * 优惠券核算规则，平铺成 JSON 字段
     */
    @Column(name = "rule", nullable = false)
    @Convert(converter = RuleConverter.class)
    private TemplateRule rule;

}