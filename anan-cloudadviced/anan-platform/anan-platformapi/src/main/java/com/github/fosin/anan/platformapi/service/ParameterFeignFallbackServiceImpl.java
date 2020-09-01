package com.github.fosin.anan.platformapi.service;


import com.github.fosin.anan.cloudresource.dto.request.AnanParameterCreateDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanParameterRetrieveDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanParameterUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanParameterEntity;
import com.github.fosin.anan.platformapi.service.inter.ParameterFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@Slf4j
@Service
public class ParameterFeignFallbackServiceImpl implements ParameterFeignService {

    @Override
    public ResponseEntity<AnanParameterEntity> create(AnanParameterCreateDto ananParameterCreateDto) {
        log.error("feign 远程创建通用系统参数失败:{}", ananParameterCreateDto);
        return null;
    }

    @Override
    public ResponseEntity<AnanParameterEntity> update(AnanParameterUpdateDto ananParameterUpdateDto) {
        log.error("feign 远程更新通用系统参数失败:{}", ananParameterUpdateDto);
        return null;
    }

    @Override
    public ResponseEntity<AnanParameterEntity> getParameter(Integer type, String scope, String name) {
        log.error("feign 远程查询通用系统参数失败:{}{}{}", type, scope, name);
        return null;
    }

    @Override
    public ResponseEntity<AnanParameterEntity> getNearestParameter(Integer type, String scope, String name) {
        log.error("feign 远程查询通用系统参数失败:{}{}{}", type, scope, name);
        return null;
    }

    @Override
    public ResponseEntity<String> getOrCreateParameter(AnanParameterRetrieveDto retrieveDto) {
        log.error("feign 远程查询通用系统参数失败:{}", retrieveDto);
        return null;
    }

    @Override
    public ResponseEntity<Boolean> apply(Long id) {
        log.error("feign 远程应用系统参数失败:{}", id);
        return null;
    }

    @Override
    public ResponseEntity<Boolean> applys() {
        log.error("feign 远程应用所有系统参数失败");
        return null;
    }
}
