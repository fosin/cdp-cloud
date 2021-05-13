package top.fosin.anan.platform.repository;

import top.fosin.anan.platform.entity.AnanPayEntity;
import top.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;
/**
 * 系统支付表(anan_pay)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface AnanPayRepository extends IJpaRepository<AnanPayEntity, Long>{
}