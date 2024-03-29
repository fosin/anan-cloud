package top.fosin.anan.platform.modules.pub.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.jpa.po.IdCreatePO;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 权限实体类
 *
 * @author fosin
 * @date 2021-10-15 12:50:38
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@MappedSuperclass
public class PermissionId<ID extends Serializable>
        extends IdCreatePO<ID> {
    private static final long serialVersionUID = 117455991817648863L;

    @Basic
    @ApiModelProperty(value = "权限序号", required = true)
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;
}
