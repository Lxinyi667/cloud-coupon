package top.mqxu.coupon.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.mqxu.coupon.customer.dao.entity.Coupon;

/**
 * @author moqi
 */
public interface CouponDao extends JpaRepository<Coupon, Long> {

    long countByUserIdAndTemplateId(Long userId, Long templateId);

}