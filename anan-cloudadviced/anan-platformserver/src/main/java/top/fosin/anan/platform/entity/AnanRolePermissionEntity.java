package top.fosin.anan.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统角色权限表(AnanRolePermission)实体类
 *
 * @author fosin
 * @date 2019-01-27 19:35:04
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_role_permission")
@ApiModel(value = "角色权限表实体类", description = "角色权限的实体类")
public class AnanRolePermissionEntity extends PermissionEntity<Long> {
    private static final long serialVersionUID = 646285760537969078L;

    @Basic
    @ApiModelProperty(value = "角色序号", required = true)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

}
