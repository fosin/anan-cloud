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
 * 国际化语言字符集实体类
 *
 * @author fosin
 * @date 2020-12-09 10:34:33
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_international_charset")
@ApiModel(value = "国际化语言字符集实体类", description = "国际化语言字符集实体类")
public class AnanInternationalCharsetEntity extends CreateUpdateEntity<Long> implements Serializable,
        StatusProp<Integer> {
    private static final long serialVersionUID = -47422702414130736L;

    @Basic
    @Column(name = "international_id")
    @ApiModelProperty(value = "国际化语言ID")
    private Long internationalId;

    @Basic
    @Column(name = "service_id")
    @ApiModelProperty(value = "服务ID")
    private Long serviceId;

    @Basic
    @Column(name = "charset")
    @ApiModelProperty(value = "自定义字符集")
    private String charset;

    @Basic
    @Column(name = "status")
    @ApiModelProperty(value = "状态：0=启用，1=禁用")
    private Integer status;

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
