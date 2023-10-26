package top.mqxu.coupon.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.mqxu.coupon.customer.dao.entity.Coupon;

public class CouponDao extends JpaRepository<Coupon,Long> {
    long countByUserIdAndTempl
}
