package top.fosin.anan.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.entity.CreateUpdateEntity;
import top.fosin.anan.model.prop.StatusProp;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 国际化语言集(AnanInternational)实体类
 *
 * @author fosin
 * @date 2020-12-08 20:54:10
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "anan_international")
@ApiModel(value = "国际化语言的实体类", description = "国际化语言的实体类")
public class AnanInternationalEntity extends CreateUpdateEntity<Long> implements Serializable,
        StatusProp<Integer> {
    private static final long serialVersionUID = 717249075172389735L;

    @Basic
    @Column(name = "code")
    @ApiModelProperty(value = "标识")
    private String code;

    @Basic
    @Column(name = "name")
    @ApiModelProperty(value = "名称")
    private String name;

    @Basic
    @Column(name = "icon")
    @ApiModelProperty(value = "图标")
    private String icon;

    @Basic
    @Column(name = "status")
    @ApiModelProperty(value = "状态：0=启用，1=禁用")
    private Integer status;

    @Basic
    @Column(name = "default_flag")
    @ApiModelProperty(value = "默认标志")
    private Integer defaultFlag;

    @Override
    @Transient
    public Integer getStatusValue() {
        return status;
    }

    @Override
    @Transient
    public void setStatusValue(Integer integer) {
        this.status = integer;
    }

    @Override
    @Transient
    public String getStatusName() {
        return "status";
    }

}
