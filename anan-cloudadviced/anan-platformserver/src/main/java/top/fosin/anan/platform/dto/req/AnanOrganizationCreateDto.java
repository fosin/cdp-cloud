package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.fosin.anan.core.util.RegexUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 系统机构表(AnanOrganization)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@Data
@ApiModel(value = "机构表创建DTO", description = "机构的创建DTO")
public class AnanOrganizationCreateDto implements Serializable {
    private static final long serialVersionUID = 389815217019211695L;

    @NotNull(message = "父机构编号" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "父机构编号，取值于id，表示当前数据所属的父类机构", required = true)
    private Long pid;

    @NotNull(message = "顶级机构编号" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "顶级机构编号：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id", required = true)
    private Long topId;

    @NotBlank(message = "机构编码" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀", required = true)
    @Pattern(regexp = "[\\w]{1,64}", message = "机构编码只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    private String code;

    @NotBlank(message = "机构名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "机构名称", required = true)
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @NotNull(message = "深度" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "深度", required = true)
    private Integer level;

    @ApiModelProperty(value = "机构全名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String fullname;

    @ApiModelProperty(value = "机构地址")
    private String address;

    @ApiModelProperty(value = "机构电话")
    private String telphone;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true)
    private Integer status;

}
