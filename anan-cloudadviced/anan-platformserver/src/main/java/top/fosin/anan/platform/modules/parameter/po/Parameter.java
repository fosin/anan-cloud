package top.fosin.anan.platform.modules.parameter.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.cloudresource.parameter.ParameterStatus;
import top.fosin.anan.jpa.po.IdCreateUpdatePO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;

;

/**
 * 用于存放各种分类分组的个性化参数(AnanParameter)实体类
 *
 * @author fosin
 * @date 2019-01-27 17:17:30
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_parameter")
@Schema(description = "通用参数的实体类")
public class Parameter extends IdCreateUpdatePO<Long> {
    private static final long serialVersionUID = 301081721804164443L;

    @Basic
    @Schema(description = "参数键")
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @Schema(description = "参数值")
    @Column(name = "value", length = 256)
    private String value;

    @Basic
    @Schema(description = "参数分类：具体取值于字典表anan_dictionary.id=10")
    @Column(name = "type", nullable = false)
    private Byte type;

    @Basic
    @Schema(description = "参数作用域")
    @Column(name = "scope", length = 64)
    private String scope;

    @Basic
    @Schema(description = "默认值")
    @Column(name = "default_value", length = 256)
    private String defaultValue;

    @Basic
    @Schema(description = "参数描述")
    @Column(name = "description", length = 256)
    private String description;

    @Basic
    @Schema(description = "发布时间，该值由后台维护，更改数据时前端不需要关心")
    @Column(name = "apply_time")
    private Date applyTime;

    @Basic
    @Schema(description = "该值由后台维护，更改数据时前端不需要关心，取值于anan_user.id")
    @Column(name = "apply_by")
    private Long applyBy;

    @Basic
    @Schema(description = "参数状态：0=正常状态、1=修改状态、2=删除状态")
    @Column(name = "status", nullable = false)
    private Byte status = ParameterStatus.Normal.getTypeValue();

}
