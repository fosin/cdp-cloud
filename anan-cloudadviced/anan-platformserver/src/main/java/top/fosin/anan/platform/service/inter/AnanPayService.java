package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platform.dto.request.AnanPayCreateDto;
import top.fosin.anan.platform.dto.request.AnanPayRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanPayUpdateDto;
import top.fosin.anan.platform.entity.AnanPayEntity;
import top.fosin.anan.jpa.service.ISimpleJpaService;

/**
 * 系统支付表(anan_pay)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanPayService extends ISimpleJpaService<AnanPayEntity, Long, AnanPayCreateDto, AnanPayRetrieveDto, AnanPayUpdateDto> {
}