package top.mqxu.coupon.template.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import top.mqxu.coupon.template.dao.entity.CouponTemplate;

import java.util.List;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description CouponTemplateDao
 **/
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Long> {

    /**
     * 根据 Shop ID 查询出所有券模板
     *
     * @param shopId 店铺id
     * @return {@link List<CouponTemplate>}
     */
    List<CouponTemplate> findAllByShopId(Long shopId);

    /**
     * IN 查询 + 分页支持的语法
     *
     * @param id   优惠券id
     * @param page 分页
     * @return {@link Page<CouponTemplate>}
     */
    Page<CouponTemplate> findAllByIdIn(List<Long> id, Pageable page);

    /**
     * 根据 shop ID + 可用状态查询店铺有多少券模板
     *
     * @param shopId    店铺id
     * @param available 是否可用
     * @return {@link Integer}
     */
    Integer countByShopIdAndAvailable(Long shopId, Boolean available);

    /**
     * 将优惠券设置为不可用
     *
     * @param id 优惠券id
     * @return {@link Integer}
     */
    @Modifying
    @Query("update CouponTemplate c set c.available = false where c.id = :id")
    int makeCouponUnavailable(@Param("id") Long id);
}