package top.lxyi.coupon.customer.service.impl;


import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.lxyi.coupon.calculation.api.beans.ShoppingCart;
import top.lxyi.coupon.calculation.api.beans.SimulationOrder;
import top.lxyi.coupon.calculation.api.beans.SimulationResponse;
import top.lxyi.coupon.customer.api.beans.RequestCoupon;
import top.lxyi.coupon.customer.api.beans.SearchCoupon;
import top.lxyi.coupon.customer.api.enums.CouponStatus;
import top.lxyi.coupon.customer.converter.CouponConverter;
import top.lxyi.coupon.customer.dao.CouponDao;
import top.lxyi.coupon.customer.dao.entity.Coupon;
import top.lxyi.coupon.customer.feign.CalculationService;
import top.lxyi.coupon.customer.feign.TemplateService;
import top.lxyi.coupon.customer.service.CouponCustomerService;
import top.lxyi.coupon.template.api.beans.CouponInfo;
import top.lxyi.coupon.template.api.beans.CouponTemplateInfo;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CouponCustomerServiceImpl implements CouponCustomerService {

    @Resource
    private CouponDao couponDao;

    @Resource
    private TemplateService templateService;

    @Resource
    private CalculationService calculationService;
    /**
     * 注入WebClient.Builder对象
     */
//    @Resource
//    private WebClient.Builder webClientBuilder;

//    private CouponTemplateInfo loadTemplateInfo(Long templatedId){
//        return webClientBuilder.build().get()
//                .uri("http://coupon-template-serv/template/getTemplate?id="+ templatedId)
//                .retrieve()
//                .bodyToMono(CouponTemplateInfo.class)
//                .block();
//    }
    @Override
    public SimulationResponse simulateOrderPrice(SimulationOrder order) {
        List<CouponInfo> couponInfos = Lists.newArrayList();
        // 挨个循环，把优惠券信息加载出来
        // 高并发场景下不能这么一个个循环，更好的做法是批量查询
        // 而且券模板一旦创建不会改内容，所以在创建端做数据异构放到缓存里，使用端从缓存捞template信息
        for (Long couponId : order.getCouponIDs()) {
            Coupon example = Coupon.builder()
                    .userId(order.getUserId())
                    .id(couponId)
                    .status(CouponStatus.AVAILABLE)
                    .build();
            Optional<Coupon> couponOptional = couponDao.findAll(Example.of(example))
                    .stream()
                    .findFirst();
            // 加载优惠券模板信息
            if (couponOptional.isPresent()) {
                Coupon coupon = couponOptional.get();
                CouponInfo couponInfo = CouponConverter.convertToCoupon(coupon);
                couponInfo.setTemplate(templateService.getTemplate(couponInfo.getTemplateId()));
                couponInfos.add(couponInfo);
            }
        }
        order.setCouponInfos(couponInfos);

        // 调用接口试算服务
//        return calculationService.simulateOrder(order);
        //WebClient 调用试算服务
//        return webClientBuilder.build().get()
//                .uri("http://coupon-template-serv/calculator/simulate")
//
//                .retrieve()
//                .bodyToMono(SimulationResponse.class)
//                .block();
        return calculationService.simulate(order);
     }


    /**
     * 用户查询优惠券的接口
     */
    @Override
    public List<CouponInfo> findCoupon(SearchCoupon request) {
        // 在真正的生产环境，这个接口需要做分页查询，并且查询条件要封装成一个类
        Coupon example = Coupon.builder()
                .userId(request.getUserId())
                .status(CouponStatus.convert(request.getCouponStatus()))
                .shopId(request.getShopId())
                .build();

        // 这里你可以尝试实现分页查询
        List<Coupon> coupons = couponDao.findAll(Example.of(example));
        if (coupons.isEmpty()) {
            return Lists.newArrayList();
        }

        List<Long> templateIds = coupons.stream()
                .map(Coupon::getTemplateId)
//                .collect(Collectors.toList());
                .toList();
//        Map<Long, CouponTemplateInfo> templateMap = templateService.getTemplateInfoMap(templateIds);

        //通过WebClient 调用远程服务
        Map<Long, CouponTemplateInfo> map = templateService.getTemplateInBatch(templateIds);
//        Map<Long, CouponTemplateInfo> map = webClientBuilder.build().get()
//                .uri("http://coupon-template-serv/templatee/getBatch?ids=" + templateIds)
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<Map<Long, CouponTemplateInfo>>() {
//
//                })
//                .block();

        coupons.forEach(e -> e.setTemplateInfo(map.get(e.getTemplateId())));

        return coupons.stream()
                .map(CouponConverter::convertToCoupon)
                .collect(Collectors.toList());
    }

    /**
     * 用户领取优惠券
     */
    @Override
    public Coupon requestCoupon(RequestCoupon request) {
        CouponTemplateInfo templateInfo = templateService.getTemplate(request.getCouponTemplateId());

        // 模板不存在则报错
        if (templateInfo == null) {
            log.error("invalid template id={}", request.getCouponTemplateId());
            throw new IllegalArgumentException("Invalid template id");
        }

        // 模板不能过期
        long now = Calendar.getInstance().getTimeInMillis();
        Long expTime = templateInfo.getRule().getDeadline();
        if (expTime != null && now >= expTime || BooleanUtils.isFalse(templateInfo.getAvailable())) {
            log.error("template is not available id={}", request.getCouponTemplateId());
            throw new IllegalArgumentException("template is unavailable");
        }

        // 用户领券数量超过上限
        long count = couponDao.countByUserIdAndTemplateId(request.getUserId(), request.getCouponTemplateId());
        if (count >= templateInfo.getRule().getLimitation()) {
            log.error("exceeds maximum number");
            throw new IllegalArgumentException("exceeds maximum number");
        }

        Coupon coupon = Coupon.builder()
                .templateId(request.getCouponTemplateId())
                .userId(request.getUserId())
                .shopId(templateInfo.getShopId())
                .status(CouponStatus.AVAILABLE)
                .build();
        couponDao.save(coupon);
        return coupon;
    }

    @Override
    @Transactional
    public ShoppingCart placeOrder(ShoppingCart order) {
        // 购物车为空，丢出异常
        if (CollectionUtils.isEmpty(order.getProducts())) {
            log.error("invalid check out request, order={}", order);
            throw new IllegalArgumentException("cart if empty");
        }

        Coupon coupon = null;
        if (order.getCouponId() != null) {
            // 如果有优惠券，验证是否可用，并且是当前客户的
            Coupon example = Coupon.builder()
                    .userId(order.getUserId())
                    .id(order.getCouponId())
                    .status(CouponStatus.AVAILABLE)
                    .build();
            coupon = couponDao.findAll(Example.of(example))
                    .stream()
                    .findFirst()
                    // 如果找不到券，就抛出异常
                    .orElseThrow(() -> new RuntimeException("Coupon not found"));
            //优惠卷有了，再把他的卷模板信息逃出来
            //卷模板里的Discount规则会在稍后用于订单价格计算
            CouponInfo couponInfo = CouponConverter.convertToCoupon(coupon);
            couponInfo.setTemplate(templateService.getTemplate(couponInfo.getTemplateId()));
            order.setCouponInfos(Lists.newArrayList(couponInfo));
        }

        // order清算
        ShoppingCart checkoutInfo = calculationService.checkout(order);

//        ShoppingCart checkoutInfo = webClientBuilder.build().post()
//                .uri("http://coupon-calculation-serv/calculator/checkout")
//                .bodyValue(order)
//                .retrieve()
//                .bodyToMono(ShoppingCart.class)
//                .block();

        if (coupon != null) {
            // 如果优惠券没有被结算掉，而用户传递了优惠券，报错提示该订单满足不了优惠条件
            if (CollectionUtils.isEmpty(checkoutInfo.getCouponInfos())) {
                log.error("cannot apply coupon to order, couponId={}", coupon.getId());
                throw new IllegalArgumentException("coupon is not applicable to this order");
            }

            log.info("update coupon status to used, couponId={}", coupon.getId());
            coupon.setStatus(CouponStatus.USED);
            couponDao.save(coupon);
        }

        return checkoutInfo;
    }

    /**
     * 逻辑删除优惠券
     *
     * @param userId   用户id
     * @param couponId 优惠券id
     */
    @Override
    public void deleteCoupon(Long userId, Long couponId) {
        Coupon example = Coupon.builder()
                .userId(userId)
                .id(couponId)
                .status(CouponStatus.AVAILABLE)
                .build();
        Coupon coupon = couponDao.findAll(Example.of(example))
                .stream()
                .findFirst()
                // 如果找不到券，就抛出异常
                .orElseThrow(() -> new RuntimeException("Could not find available coupon"));

        coupon.setStatus(CouponStatus.INACTIVE);
        couponDao.save(coupon);
    }
}
