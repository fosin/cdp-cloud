package top.fosin.anan.platform.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

import java.io.Serializable;

/**
 * 系统版本权限表(AnanVersionPermission)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "版本权限表响应DTO", description = "版本权限的响应DTO")
public class AnanVersionPermissionRespDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = 632049633274326678L;
    @ApiModelProperty(value = "版本ID", example = "Long")
    private Long versionId;

    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;

}
