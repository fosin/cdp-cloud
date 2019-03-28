package com.github.fosin.cdp.platformapi.service;


import com.github.fosin.cdp.platformapi.dto.RegisterDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpOrganizationPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpOrganizationAuthEntity;
import com.github.fosin.cdp.platformapi.entity.CdpOrganizationEntity;
import com.github.fosin.cdp.platformapi.entity.CdpOrganizationPermissionEntity;
import com.github.fosin.cdp.platformapi.service.inter.IFeignOrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@Slf4j
//@Service
public class FeignOrganizationFallbackServiceImpl implements IFeignOrganizationService {

    @Override
    public ResponseEntity<List<CdpOrganizationPermissionEntity>> permissions(Long organizId) {
        log.error("feign 远程获取组织机构权限失败:{}", organizId);
        return null;
    }

    @Override
    public ResponseEntity<Collection<CdpOrganizationPermissionEntity>> permissions(List<CdpOrganizationPermissionUpdateDto> entities, Long organizId) {
        log.error("feign 远程获取组织机构权限失败:{}", entities, organizId);
        return null;
    }

    @Override
    public ResponseEntity<List<CdpOrganizationEntity>> listChild(Long pId) {
        log.error("feign 远程获取组织机构权限失败:{}", pId);
        return null;
    }

    @Override
    public ResponseEntity<List<CdpOrganizationEntity>> listAllChild(Long pId) {
        log.error("feign 远程获取组织机构权限失败:{}", pId);
        return null;
    }

    @Override
    public ResponseEntity<List<CdpOrganizationEntity>> tree(Long topId) {
        log.error("feign 远程获取组织机构权限失败:{}", topId);
        return null;
    }

    @Override
    public ResponseEntity<Boolean> register(RegisterDto registerDto) {
        log.error("feign 远程获取组织机构权限失败:{}", registerDto);
        return null;
    }

    @Override
    public ResponseEntity<CdpOrganizationAuthEntity> getOrganizAuth(Long organizId) {
        log.error("feign 远程获取组织机构权限失败:{}", organizId);
        return null;
    }

    @Override
    public ResponseEntity<CdpOrganizationEntity> findOne(Long aLong) {
        log.error("feign 远程查询一个组织机构失败:{}", aLong);
        return null;
    }

}
