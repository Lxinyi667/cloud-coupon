package top.mqxu.coupon.calculation.api.beans;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description SimulationResponse——试算优惠返回结果
 **/
@Data
@NoArgsConstructor
public class SimulationResponse {

    /**
     * 最省钱的 coupon id
     */
    private Long bestCouponId;

    /**
     * 每一个coupon对应的order价格
     */
    private Map<Long, Long> couponToOrderPrice = Maps.newHashMap();

}

