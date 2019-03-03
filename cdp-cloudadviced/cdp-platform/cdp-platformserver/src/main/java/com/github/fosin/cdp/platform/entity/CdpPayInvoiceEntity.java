package com.github.fosin.cdp.platform.entity;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;
import com.github.fosin.cdp.jpa.entity.AbstractIdJpaEntity;

/**
 * 系统支付发票表(CdpPayInvoice)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:38
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_pay_invoice")
@ApiModel(value = "系统支付发票表实体类", description = "表(cdp_pay_invoice)的对应的实体类")
public class CdpPayInvoiceEntity  extends AbstractIdJpaEntity<Long> implements Serializable {
    private static final long serialVersionUID = 120666820121378212L;

    @Basic
    @ApiModelProperty(value = "支付ID", required = true)
    @Column(name = "pay_id", nullable = false)
    private Long payId;

    @Basic
    @ApiModelProperty(value = "发票号码", required = true)
    @Column(name = "invoce_no", nullable = false, length = 40)
    private String invoceNo;

    @Basic
    @ApiModelProperty(value = "出票时间", required = true)
    @Column(name = "invoce_time", nullable = false)
    private Date invoceTime;

    @Basic
    @ApiModelProperty(value = "操作人", required = true)
    @Column(name = "crreate_by", nullable = false)
    private Long crreateBy;

}