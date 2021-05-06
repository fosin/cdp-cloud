package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;

/**
 * (OauthClientDetails)创建DTO
 *
 * @author fosin
 * @date 2021-05-03 19:01:56
 * @since 1.0.0
 */
@Data
@ApiModel(value = "$tableInfo.comment创建DTO", description = "表(oauth_client_details)的对应的创建DTO")
public class OauthClientDetailsCreateDto implements Serializable {
    private static final long serialVersionUID = -62271946402593345L;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String clientId;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String resourceIds;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String clientSecret;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String scope;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String authorities;

    @ApiModelProperty(value = "${column.comment}", example = "Integer")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "${column.comment}", example = "Integer")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String additionalInformation;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String autoapprove;

}
