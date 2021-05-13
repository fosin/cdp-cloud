package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.QueryRule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统服务表(AnanService)查询DTO
 *
 * @author fosin
 * @date 2020-12-04 17:48:21
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统服务表查询DTO", description = "表(anan_service)的对应的查询DTO")
public class AnanServiceRetrieveDto extends QuerySortRuleDto<QueryRule, SortRule, Integer> implements Serializable {
    private static final long serialVersionUID = -95213722807513308L;

    @NotBlank(message = "服务标识" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "服务标识", example = "String")
    private String code;

    @NotBlank(message = "服务名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "服务名称", example = "String")
    private String name;

    @NotNull(message = "状态码：0：禁用 1：启用" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "状态码：0：禁用 1：启用", example = "0")
    private Integer status;

}