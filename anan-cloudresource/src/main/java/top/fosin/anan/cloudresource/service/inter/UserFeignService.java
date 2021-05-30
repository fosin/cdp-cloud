package top.fosin.anan.cloudresource.service.inter;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.AnanUserRespDto;
import top.fosin.anan.cloudresource.service.UserFeignFallbackServiceImpl;
import top.fosin.anan.model.constant.PathConstant;
import top.fosin.anan.model.dto.TreeDto;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.USER, fallback = UserFeignFallbackServiceImpl.class)
public interface UserFeignService {
    @PostMapping({PathConstant.PATH_ID})
    @ApiOperation("根据主键ID查询一条数据")
    ResponseEntity<AnanUserRespDto> findOne(@PathVariable(TreeDto.ID_NAME) Long id);

    @PostMapping({PathConstant.PATH_IDS})
    @ApiOperation("根据id查询多条数据")
    ResponseEntity<List<AnanUserRespDto>> findAllByIds(@RequestBody List<Long> ids);

    @PostMapping("/usercode/{usercode}")
    ResponseEntity<AnanUserRespDto> getByUsercode(@PathVariable("usercode") String usercode);

    @PostMapping("/childList/organizId/{organizId}/{status}")
    ResponseEntity<List<AnanUserRespDto>> findAllUserByOrganizId(@PathVariable("organizId") Long organizId, @PathVariable("status") Integer status);

}

