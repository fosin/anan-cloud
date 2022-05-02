package top.fosin.anan.platform.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
public class OrganizIdPermissionEntity<ID extends Serializable> extends PermissionEntity<ID> {
    private static final long serialVersionUID = 117455991817648863L;

    @Column(name = "organiz_id", nullable = false)
    @Basic
    @ApiModelProperty(value = "机构序号")
    private ID organizId;

}
