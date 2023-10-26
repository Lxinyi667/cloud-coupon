package top.mqxu.coupon.template.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mqxu.coupon.template.api.beans.CouponTemplateInfo;
import top.mqxu.coupon.template.api.beans.PagedCouponTemplateInfo;
import top.mqxu.coupon.template.api.beans.TemplateSearchParams;
import top.mqxu.coupon.template.api.enums.CouponType;
import top.mqxu.coupon.template.converter.CouponTemplateConverter;
import top.mqxu.coupon.template.dao.CouponTemplateDao;
import top.mqxu.coupon.template.dao.entity.CouponTemplate;
import top.mqxu.coupon.template.service.CouponTemplateService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author mqxu
 * @date 2023/9/8
 * @description CouponTemplateServiceImpl
 **/
@Slf4j
@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

    @Resource
    private CouponTemplateDao templateDao;


    /**
     * 创建优惠券模板
     *
     * @param request 模板信息
     * @return CouponTemplateInfo
     */
    @Override
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        // 单个门店最多可以创建 100 张优惠券模板
        if (request.getShopId() != null) {
            Integer count = templateDao.countByShopIdAndAvailable(request.getShopId(), true);
            if (count >= 100) {
                log.error("the totals of coupon template exceeds maximum number");
                throw new UnsupportedOperationException("exceeded the maximum of coupon templates that you can create");
            }
        }

        // 创建优惠券
        CouponTemplate template = CouponTemplate.builder().name(request.getName()).description(request.getDesc()).category(CouponType.convert(request.getType())).available(true).shopId(request.getShopId()).rule(request.getRule()).build();
        template = templateDao.save(template);

        return CouponTemplateConverter.convertToTemplateInfo(template);
    }

    /**
     * 通过ID查询优惠券模板
     *
     * @param id 模板ID
     * @return CouponTemplateInfo
     */
    @Override
    public CouponTemplateInfo loadTemplateInfo(Long id) {
        Optional<CouponTemplate> template = templateDao.findById(id);
        return template.map(CouponTemplateConverter::convertToTemplateInfo).orElse(null);
    }

    /**
     * 克隆优惠券
     *
     * @param templateId 模板ID
     * @return CouponTemplateInfo
     */
    @Override
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        log.info("cloning template id {}", templateId);
        CouponTemplate source = templateDao.findById(templateId).orElseThrow(() -> new IllegalArgumentException("invalid template ID"));

        CouponTemplate target = new CouponTemplate();
        BeanUtils.copyProperties(source, target);

        target.setAvailable(false);
        target.setId(null);

        templateDao.save(target);
        return CouponTemplateConverter.convertToTemplateInfo(target);
    }


    @Override
    public PagedCouponTemplateInfo search(TemplateSearchParams request) {
        CouponTemplate example = CouponTemplate.builder().shopId(request.getShopId()).category(CouponType.convert(request.getType())).available(request.getAvailable()).name(request.getName()).build();

        Pageable page = PageRequest.of(request.getPage(), request.getPageSize());
        Page<CouponTemplate> result = templateDao.findAll(Example.of(example), page);
        List<CouponTemplateInfo> couponTemplateInfos = result.stream().map(CouponTemplateConverter::convertToTemplateInfo).collect(Collectors.toList());

        return PagedCouponTemplateInfo.builder().templates(couponTemplateInfos).page(request.getPage()).total(result.getTotalElements()).build();
    }

    public List<CouponTemplateInfo> searchTemplate(CouponTemplateInfo request) {
        CouponTemplate example = CouponTemplate.builder().shopId(request.getShopId()).category(CouponType.convert(request.getType())).available(request.getAvailable()).name(request.getName()).build();
        // 可以用下面的方式做分页查询
        //  Pageable page = PageRequest.of(0, 100);
        //  templateDao.findAll(Example.of(example), page);
        List<CouponTemplate> result = templateDao.findAll(Example.of(example));
        return result.stream().map(CouponTemplateConverter::convertToTemplateInfo).collect(Collectors.toList());
    }


    /**
     * 删除优惠券
     *
     * @param id 模板ID
     */
    @Override
    @Transactional
    public void deleteTemplate(Long id) {
        int rows = templateDao.makeCouponUnavailable(id);
        if (rows == 0) {
            throw new IllegalArgumentException("Template Not Found: " + id);
        }
    }

    /**
     * 批量读取模板
     *
     * @param ids 模板ID集合
     * @return Map<模板ID, CouponTemplateInfo>
     */
    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        List<CouponTemplate> templates = templateDao.findAllById(ids);
        return templates.stream().map(CouponTemplateConverter::convertToTemplateInfo).collect(Collectors.toMap(CouponTemplateInfo::getId, Function.identity()));
    }
}